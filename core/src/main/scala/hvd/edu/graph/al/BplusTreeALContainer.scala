package hvd.edu.graph.al

import hvd.edu.collection.mutable.BPlusTreeImpl
import hvd.edu.graph.GraphContainer
import hvd.edu.graph.al.ALNodeUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * We store the vertex as the key of Leaf node and the edges as the values of the leafNode of a BplusTree
 * @param numVertex
 * @param fanout
 */
case class BplusTreeALContainer(numVertex: Int, fanout: Option[Int]) extends GraphContainer[ALNode] {

  val defaultFanout = 5

  private val container = new BPlusTreeImpl[Int, mutable.ListBuffer[Int]](fanout.getOrElse(defaultFanout))

  override def addEdge(vertex: ALNode, edge: ALNode): Unit = {
    val mayBeExistingEdges = container.find(vertex.id)

    mayBeExistingEdges.fold(container.add(vertex.id, mutable.ListBuffer(edge.id))) { eList =>
      eList += edge.id
      // you dont need update as the data of leaf nodes of this tree are
      // ListBuffers- so data can be updated and it has no size restrictions..
    }
  }

  override def addVertex(vertex: ALNode): Unit =
    container.add(vertex.id, mutable.ListBuffer[Int]())

  override def allVertices: List[ALNode] = {
    val vids = container.getLeaves()
    vids.map(_vid => ALNode(_vid, _vid))
  }

  override def edgeLength: Int = container.getAllValues().map(_.size).sum

  override def vertexLength: Int = allVertices.size

  override def edgesForVertex(v: ALNode): List[Int] = {
    //container.find(v.id).fold(List.empty[Int])(_.toList)
    edgesForVertexId(v.id)
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    // val nodeToFind = ALNode(vid, vid)
    // edgesForVertex(nodeToFind)
    container.find(vid).fold(List.empty[Int])(_.toList)
  }

  override def nonEmptyVertexList: List[ALNode] = allVertices

  override def range(vid1: Int, vid2: Int): List[Int] = {
    val allEdges: List[ListBuffer[Int]] = container.range(vid1, vid2)
    allEdges.flatMap(_.toList)
  }
}
