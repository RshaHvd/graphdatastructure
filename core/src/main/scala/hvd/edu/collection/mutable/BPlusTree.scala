package hvd.edu.collection.mutable

import com.typesafe.scalalogging.LazyLogging

import scala.Ordering.Implicits._
import scala.collection.{ immutable, mutable }
import scala.collection.mutable.ListBuffer

trait TreeNode[T] {

  def listOfNodes: mutable.ListBuffer[T]

  def add(t: T): Unit
}

trait LeafNode[T, D] extends TreeNode[T] {

  def nextNode: Option[LeafNode[T, D]]

  def add(key: T, data: D): Unit

  override def add(t: T): Unit =
    throw new RuntimeException(
      s"Leaf Nodes cannot be added without data. Invalid call."
    )

  def getDataForKeys(findKeys: T*): mutable.ListBuffer[(T, D)]

  def update(t: T, d: D): Unit

  def allValues(): List[D]

  def allKeyValues(): List[(T, D)]

}

abstract class IndexNode[T: Ordering] extends TreeNode[T] with LazyLogging {

  def children: mutable.ListBuffer[TreeNode[T]]

  def setChildren(newChildren: mutable.ListBuffer[TreeNode[T]]): Unit

  def findFirstChildIndexLessThan(t: T): Option[Int] = {
    var found = false
    var i = 0
    for (tdIx <- this.listOfNodes; if !found) {
      if (t < tdIx) {
        found = true
      }
      else {
        i += 1
      }
    }
    if (!found) {
      None
    }
    else {
      Option(i)
    }

  }
}

class DefaultIndexNode[T: Ordering](inputNodes: ListBuffer[T] = mutable.ListBuffer[T](), childNode: mutable.ListBuffer[TreeNode[T]] = ListBuffer.empty[TreeNode[T]])
  extends IndexNode[T] {

  private var _children = childNode

  val nodes = inputNodes

  def listOfNodes = nodes

  def children = _children

  override def add(t: T): Unit =
    nodes.append(t)

  override def setChildren(newChildren: mutable.ListBuffer[TreeNode[T]]) =
    _children = newChildren

}

class DefaultLeafNode[T: Ordering, D](inputNodes: List[T] = Nil, dataForNodes: Seq[(T, D)] = Seq.empty[(T, D)]) extends LeafNode[T, D] {

  val nodes = mutable.ListBuffer[T](inputNodes: _*)

  val dataByKey = mutable.Map[T, D](dataForNodes: _*)

  def listOfNodes = nodes.sorted

  var nextNode: Option[LeafNode[T, D]] = None

  override def add(t: T, d: D): Unit = {
    nodes.append(t)
    dataByKey(t) = d
  }

  override def getDataForKeys(findKeys: T*): mutable.ListBuffer[(T, D)] = {
    //dataByKey.filterKeys(_k => findKeys.contains(_k)).toMap

    val lb = mutable.ListBuffer[(T, D)]()
    findKeys.foreach {
      k =>
        val found = dataByKey.get(k)
        if (found.nonEmpty) {
          lb += ((k, found.get))
        }
    }
    lb
  }

  override def update(t: T, d: D) =
    if (nodes.contains(t) && dataByKey.contains(t))
      dataByKey(t) = d
    else
      throw new RuntimeException(
        s"Trying to update ket : ${t} which does not exists in the Leaf"
      )

  override def allValues(): List[D] = dataByKey.values.toList

  override def allKeyValues(): List[(T, D)] = dataByKey.toList

}

trait BPlusTree[T, D] {

  def fanout: Int

  def add(t: T, d: D): Unit

  def find(t: T): Option[D]

  def getImmediateIndexNodes(): List[TreeNode[T]]

  def getLeaves(): List[T]

  def getNodes(): List[T]

  def treeHeight(): Int

  def update(t: T, d: D): Unit

  def getAllValues(): List[D]

  def getAllKeyValues(): List[(T, D)]
}

class BPlusTreeImpl[T: Ordering, D](override val fanout: Int) extends BPlusTree[T, D] {

  private var _tree: TreeNode[T] = new DefaultLeafNode[T, D]()
  private val nodeElementsSize = fanout - 1
  private val splitNodesMidPt: Int = nodeElementsSize / 2

  override def add(t: T, d: D) = {

      def addToThisTree(_thisTree: TreeNode[T]): TreeNode[T] =
        _thisTree match {

          case x: LeafNode[T, D] =>
            //1. can be added to leaf node - no split
            if (x.listOfNodes.size < nodeElementsSize) {
              x.add(t, d)
              _thisTree
            }
            else {
              //split the leaf node - converts to an index node.
              val (k, n1, n2) =
                TreeOps.addAndSplitLeafNodes(t, d, x, splitNodesMidPt)
              val newRoot = new DefaultIndexNode(
                mutable.ListBuffer(k),
                mutable.ListBuffer(List(n1, n2): _*)
              )

              newRoot
            }

          case y: IndexNode[T] =>
            //1. Find the leaf node and add - no split
            //2. Find leaf node - add - overflow leaf- split - no split to the index node
            //3. Find the leaf node - add - overflow leaf - split - overflow index - split

            val mayBeChildIndexLessThan = y.findFirstChildIndexLessThan(t)

            val (childNode, childNodeIndex): (TreeNode[_], Int) =
              if (mayBeChildIndexLessThan.isDefined)
                (
                  y.children(mayBeChildIndexLessThan.get),
                  mayBeChildIndexLessThan.get
                )
              else
                (y.children.last, y.children.size - 1)

            childNode match {

              case x1: LeafNode[T, D] =>
                if (x1.listOfNodes.size < nodeElementsSize) {
                  x1.add(t, d)
                  _thisTree
                }
                else {
                  //split the leaf node - converts to an index node.
                  val (k, n1, n2) =
                    TreeOps.addAndSplitLeafNodes(t, d, x1, splitNodesMidPt)
                  // if y can accomodate the new value m - add that and its children n1 and n2
                  if (y.listOfNodes.size < nodeElementsSize) {
                    y.add(k)
                    val childrenBefore = y.children.slice(0, childNodeIndex)
                    val childrenAfter =
                      y.children.slice(childNodeIndex + 1, y.children.size)
                    val childrenReplacingIndex = List(n1, n2)
                    val newChildrenInOrder = childrenBefore
                    newChildrenInOrder.appendAll(childrenReplacingIndex)
                    newChildrenInOrder.appendAll(childrenAfter)
                    y.setChildren(newChildrenInOrder)
                    _thisTree
                  }
                  else {
                    // split the index mode adding , m and its child nodes n1 and n2
                    val (nodeKey, leftIndexNode, rightIndexNode) =
                      TreeOps.addAndSplitIndexNodes(
                        k,
                        n1,
                        n2,
                        childNodeIndex,
                        y,
                        splitNodesMidPt
                      )
                    // join this to the parent y
                    val newYRoot = new DefaultIndexNode(
                      mutable.ListBuffer(nodeKey),
                      mutable.ListBuffer(List(leftIndexNode, rightIndexNode): _*)
                    )
                    newYRoot
                  }
                }
              case y1: IndexNode[T] =>
                val updatedTree: TreeNode[T] = addToThisTree(y1)
                val heightOfOriginalTree = TreeOps.treeHeight(y1)
                val heightOfUpdateTree = TreeOps.treeHeight(updatedTree)
                if (heightOfOriginalTree == heightOfUpdateTree)
                  _thisTree //this tree was modified
                else {
                  // need to promote - might result in new root node
                  // there should only ever be a change of 1 in height at immediate tree level
                  if (heightOfUpdateTree - heightOfOriginalTree > 1)
                    throw new RuntimeException(
                      s"Immediate level resulted in height increase > 1"
                    )

                  val (allN, allC) =
                    TreeOps.mergeTrees(y, updatedTree, childNodeIndex)

                  if (allN.size <= nodeElementsSize) {
                    updatedTree.listOfNodes.foreach(y.add(_))
                    y.setChildren(allC)
                    _thisTree
                  }
                  else {
                    val (key, leftNode, rightNode) =
                      TreeOps.createRootAndIndexNode(allN, allC, splitNodesMidPt)
                    // join this to the parent y
                    val updatedR =
                      new DefaultIndexNode(
                        mutable.ListBuffer(key),
                        mutable.ListBuffer(List(leftNode, rightNode): _*)
                      )

                    updatedR
                  }
                }
            }

        }

    val retTree = addToThisTree(_tree)
    _tree = retTree
  }

  override def find(t: T): Option[D] = {

      def findInThisTree(theTree: TreeNode[T]): Option[D] =
        theTree match {
          case t1: IndexNode[T] =>
            val mayBeChildIndexLessThan = t1.findFirstChildIndexLessThan(t)
            val searchInThisTree =
              mayBeChildIndexLessThan.fold(t1.children.last)(idx =>
                t1.children(idx))
            findInThisTree(searchInThisTree)
          case t2: LeafNode[T, D] => t2.getDataForKeys(t).headOption.map(_._2)
        }

    findInThisTree(_tree)
  }

  override def update(t: T, d: D): Unit = {

      def findAndUpdate(theTree: TreeNode[T]): Unit =
        theTree match {
          case t1: IndexNode[T] => {
            val mayBeChildIndexLessThan = t1.findFirstChildIndexLessThan(t)
            val searchInThisTree = mayBeChildIndexLessThan.fold(t1.children.last)(idx => t1.children(idx))
            findAndUpdate(searchInThisTree)
          }
          case t2: LeafNode[T, D] => t2.update(t, d)
        }

    findAndUpdate(_tree)
  }

  override def getNodes() =
    _tree match {
      case t1: LeafNode[T, D] => t1.listOfNodes.toList
      case t2: IndexNode[T]   => t2.listOfNodes.toList
    }

  override def getImmediateIndexNodes() = TreeOps.immediateIndexNodes(_tree)

  override def getLeaves(): List[T] = {

      def internalLeaves(_theTree: TreeNode[T]): ListBuffer[T] =
        _theTree match {
          case t1: LeafNode[T, D] => t1.listOfNodes
          case t2: IndexNode[T] =>
            val ll = t2.children.flatMap {
              internalLeaves(_)
            }
            ll
        }

    internalLeaves(_tree).toList

  }

  override def getAllValues(): List[D] = {

      def walkTreeForValues(_theTree: TreeNode[T]): List[D] =
        _theTree match {
          case t1: LeafNode[T, D] => t1.allValues
          case t2: IndexNode[T] =>
            val valuesForAllChildren = t2.children.flatMap {
              walkTreeForValues(_)
            }
            valuesForAllChildren.toList
        }

    walkTreeForValues(_tree)

  }

  override def getAllKeyValues(): List[(T, D)] = {

      def walkTreeKeyValues(_theTree: TreeNode[T]): List[(T, D)] =
        _theTree match {

          case t1: LeafNode[T, D] => t1.allKeyValues()

          case t2: IndexNode[T] =>
            val keyValueAllChildren = t2.children.flatMap {
              walkTreeKeyValues(_)
            }
            keyValueAllChildren.toList
        }

    walkTreeKeyValues(_tree)
  }

  override def treeHeight(): Int = TreeOps.treeHeight(_tree)

}

object TreeOps {

  def treeHeight[T: Ordering, D](tree: TreeNode[T]): Int = {

      def internalHeight(thisTree: TreeNode[T], heightSoFar: Int): Int =
        thisTree match {

          case t1: LeafNode[T, _] => heightSoFar + 0

          case t2: IndexNode[T] =>
            internalHeight(t2.children.head, heightSoFar + 1)
        }

    internalHeight(tree, 0)

  }

  def immediateIndexNodes[T: Ordering, D](tree: TreeNode[T]) =
    tree match {
      case t1: LeafNode[T, _] => Nil
      case t2: IndexNode[T]   => t2.children.toList
    }

  def mergeTrees[T: Ordering, D](
    parentTree:                   IndexNode[T],
    treeToMerge:                  TreeNode[T],
    positionInParentTreeChildren: Int
  ) = {

    val childrenAtPosition = treeToMerge match {
      case ty1: IndexNode[T] => ty1.children
      case _                 => ListBuffer.empty
    }

    createAllNodesAndChildren(
      treeToMerge.listOfNodes.toList,
      childrenAtPosition.toList,
      positionInParentTreeChildren,
      parentTree
    )

  }

  def createAllNodesAndChildren[T: Ordering, D](
    keys:               List[T],
    childrenAtPosition: List[TreeNode[T]],
    childPosition:      Int,
    indexNodeTree:      IndexNode[T]
  ) = {

    val allNodes: List[T] = (keys ::: indexNodeTree.listOfNodes.toList).sorted
    // remove the child at the childPosition as it will be replaced with lChild and rChild
    val childrenBefore = indexNodeTree.children.slice(0, childPosition)
    val childrenAfter = indexNodeTree.children.slice(
      childPosition + 1,
      indexNodeTree.children.size
    )
    val childrenReplacingIndex = childrenAtPosition

    // add new child nodes - lChild, rChild - and create a list of all children
    val allChildrenInOrder = childrenBefore
    allChildrenInOrder.appendAll(childrenReplacingIndex)
    allChildrenInOrder.appendAll(childrenAfter)

    (allNodes, allChildrenInOrder)
  }

  def addAndSplitIndexNodes[T: Ordering, D](key: T, lChild: TreeNode[T], rChild: TreeNode[T],
                                            childPosition: Int, indexNodeTree: IndexNode[T], numNodes: Int) = {
    val (allNodes, allChildren) =
      createAllNodesAndChildren(
        List(key),
        List(lChild, rChild),
        childPosition,
        indexNodeTree
      )

    createRootAndIndexNode(allNodes, allChildren, numNodes)
  }

  def createRootAndIndexNode[T: Ordering, D](
    allNodesSorted: List[T],
    allChildren:    ListBuffer[TreeNode[T]], numNode: Int) = {
    val midNode: T = allNodesSorted(numNode)
    // split Nodes
    val n1Nodes: List[T] = allNodesSorted.slice(0, numNode)
    val n2Nodes: List[T] =
      allNodesSorted.slice(numNode + 1, allNodesSorted.size)

    // split these final children based on the nodes
    val leftNodeChilren = n1Nodes.size + 1
    val node1Children = allChildren.slice(0, leftNodeChilren)
    val node2Children = allChildren.slice(leftNodeChilren, allChildren.size)

    // create the new index nodes.
    val leftIndexNode: TreeNode[T] =
      new DefaultIndexNode(
        mutable.ListBuffer(n1Nodes: _*),
        mutable.ListBuffer(node1Children: _*)
      )

    val rightIndexNode: TreeNode[T] =
      new DefaultIndexNode(
        mutable.ListBuffer(n2Nodes: _*),
        mutable.ListBuffer(node2Children: _*)
      )

    (midNode, leftIndexNode, rightIndexNode)
  }

  def addAndSplitLeafNodes[T: Ordering, D](t: T, d: D, leafNodeTree: LeafNode[T, D], numNode: Int) = {
    leafNodeTree.add(t, d)
    val sortedNodes: List[T] = leafNodeTree.listOfNodes.toList
    val midNode: T = sortedNodes(numNode)
    val leftN = sortedNodes.slice(0, numNode)
    val rightN = sortedNodes.slice(numNode, sortedNodes.size)
    val node1: TreeNode[T] = new DefaultLeafNode(
      leftN,
      leafNodeTree.getDataForKeys(leftN: _*)
    )
    val node2: TreeNode[T] = new DefaultLeafNode(
      rightN,
      leafNodeTree.getDataForKeys(rightN: _*)
    )
    (midNode, node1, node2)
  }

}
