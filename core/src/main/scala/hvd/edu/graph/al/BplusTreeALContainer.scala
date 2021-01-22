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
case class BplusTreeALContainer(numVertex: Int, fanout: Option[Int]) extends GraphContainer[ALNode] {

  val defaultFanout = 5

  private val container = new BPlusTreeImpl[ALNode, mutable.ListBuffer[Int]](fanout.getOrElse(defaultFanout))

  override def addEdge(vertex: ALNode, edge: ALNode): Unit = {
    val mayBeExistingEdges = container.find(vertex)

    mayBeExistingEdges.fold(container.add(vertex, mutable.ListBuffer(edge.id))) { eList =>
      eList += edge.id
      // you dont need update as the data of leaf nodes of this tree are
      // ListBuffers- so data can be updated and it has not size restrictions..
    }
  }

  override def addVertex(vertex: ALNode): Unit =
    container.add(vertex, mutable.ListBuffer[Int]())

  override def allVertices: List[ALNode] = container.getLeaves()

  override def edgeLength: Int = container.getAllValues().map(_.size).sum

  override def vertexLength: Int = allVertices.size

  override def edgesForVertex(v: ALNode): List[Int] =
    container.find(v).fold(List.empty[Int])(_.toList)

  override def edgesForVertexId(vid: Int): List[Int] = {
    val nodeToFind = ALNode(vid, vid)
    edgesForVertex(nodeToFind)
  }

  override def nonEmptyVertexList: List[ALNode] = allVertices

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
