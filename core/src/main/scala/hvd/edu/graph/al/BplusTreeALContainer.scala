package hvd.edu.graph.al

import hvd.edu.collection.mutable.BPlusTreeImpl
import hvd.edu.graph.GraphContainer
import hvd.edu.graph.al.ALNodeUtils._

/**
 * We store the vertex as the key of Leaf node and the edges as the values of the leafNode of a BplusTree
 * @param numVertex
 * @param fanout
 */
case class BplusTreeALContainer(numVertex: Long, fanout: Option[Int]) extends GraphContainer[DefaultALNode] {

  val defaultFanout = 5

  private val container = new BPlusTreeImpl[DefaultALNode, List[DefaultALNode]](
    fanout.getOrElse(defaultFanout)
  )

  override def addEdge(vertex: DefaultALNode, edge: DefaultALNode): Unit = {
    val mayBeExistingEdges: Option[List[DefaultALNode]] = container.find(vertex)

    mayBeExistingEdges.fold(container.add(vertex, List(edge))) { eList =>
      val updatedEdges = eList.:+(edge)
      container.update(vertex, updatedEdges)
    }
  }

  override def addVertex(vertex: DefaultALNode): Unit =
    container.add(vertex, Nil)

  override def allVertices: List[DefaultALNode] = container.getLeaves()

  override def edgeLength: Int = container.getAllValues().map(_.size).sum

  override def vertexLength: Int = allVertices.size

  override def edgesForVertex(v: DefaultALNode): List[DefaultALNode] =
    container.find(v).getOrElse(Nil)

  override def edgesForVertexId(vid: Long): List[DefaultALNode] = {
    val nodeToFind = DefaultALNode(vid, vid)
    edgesForVertex(nodeToFind)
  }

  override def nonEmptyVertexList: List[DefaultALNode] = allVertices

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
