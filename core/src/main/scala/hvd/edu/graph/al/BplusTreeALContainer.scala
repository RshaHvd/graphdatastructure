package hvd.edu.graph.al

import hvd.edu.collection.mutable.BPlusTreeImpl
import hvd.edu.graph.GraphContainer
import hvd.edu.graph.al.ALNodeUtils._

import scala.collection.mutable

/**
 * We store the vertex as the key of Leaf node and the edges as the values of the leafNode of a BplusTree
 * @param numVertex
 * @param fanout
 */
case class BplusTreeALContainer(numVertex: Long, fanout: Option[Int]) extends GraphContainer[DefaultALNode] {

  val defaultFanout = 5

  private val container = new BPlusTreeImpl[DefaultALNode, mutable.ListBuffer[DefaultALNode]](
    fanout.getOrElse(defaultFanout)
  )

  override def addEdge(vertex: DefaultALNode, edge: DefaultALNode): Unit = {
    val mayBeExistingEdges = container.find(vertex)

    mayBeExistingEdges.fold(container.add(vertex, mutable.ListBuffer(edge))) { eList =>
      eList += edge
      // you dont need update as the data of leaf nodes of this tree are
      // ListBuffers- so data can be updated and it has not size restrictions..
    }
  }

  override def addVertex(vertex: DefaultALNode): Unit =
    container.add(vertex, mutable.ListBuffer[DefaultALNode]())

  override def allVertices: List[DefaultALNode] = container.getLeaves()

  override def edgeLength: Int = container.getAllValues().map(_.size).sum

  override def vertexLength: Int = allVertices.size

  override def edgesForVertex(v: DefaultALNode): List[DefaultALNode] =
    container.find(v).fold(List.empty[DefaultALNode])(_.toList)

  override def edgesForVertexId(vid: Long): List[DefaultALNode] = {
    val nodeToFind = DefaultALNode(vid, vid)
    edgesForVertex(nodeToFind)
  }

  override def nonEmptyVertexList: List[DefaultALNode] = allVertices

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
