package hvd.edu.graph.csr

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class HashMapCSRContainer(val numVertex: Int, val numEdges: Int) extends GraphContainer[CSRNode] {

  private val vertexContainer = mutable.Map[Int, String]()
  private val edgeContainer = mutable.Map[String, mutable.ListBuffer[Int]]()
  private val vertexNoEdgesEdgeId = "-1"

  // we store the first edge of vertex and the value of vertex key
  // next time when edge is added to a vertext - we
  // get key with vertex id and add rest of the edged.
  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = {
    val mayBeAlreadyAdded = vertexContainer.get(vertex.id)
    val edgesKey = mayBeAlreadyAdded.fold {
      val edgeId = s"V${vertex.id}_E${edge.id}"
      vertexContainer(vertex.id) = edgeId
      edgeId
    } { existingIndex => existingIndex }

    edgeContainer.get(edgesKey).fold {
      edgeContainer(edgesKey) = mutable.ListBuffer(edge.id)
    } { existingEdges => existingEdges += edge.id }
  }

  override def addVertex(vertex: CSRNode): Unit = {
    vertexContainer(vertex.id) = vertexNoEdgesEdgeId
  }

  override def allVertices: List[CSRNode] = {
    val allVids = vertexContainer.keys.toList
    allVids.map { v => CSRNode(v, v) }
  }

  private def allEdges: List[Int] = edgeContainer.values.toList.flatten

  override def vertexLength: Int = nonEmptyVertexList.length

  override def edgeLength: Int = allEdges.size

  override def edgesForVertex(v: CSRNode): List[Int] = {
    edgesForVertexId(v.id)
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    val edgeList = vertexContainer.get(vid).flatMap { edgeId =>
      edgeContainer.get(edgeId)
    }
    edgeList.fold(List.empty[Int])(lb => lb.toList)
  }

  override def nonEmptyVertexList: List[CSRNode] = allVertices

  override def range(vid1: Int, vid2: Int): List[Int] = {
    val allRangeEdges = for (v <- vid1 to vid2) yield {
      edgesForVertexId(v)
    }
    allRangeEdges.flatten.toList

  }
}
