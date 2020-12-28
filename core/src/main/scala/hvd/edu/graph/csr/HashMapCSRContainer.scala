package hvd.edu.graph.csr

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class HashMapCSRContainer(val numVertex: Int, val numEdges: Int) extends GraphContainer[CSRNode] {

  private val vertexContainer = mutable.Map[CSRNode, String]()
  private val edgeContainer = mutable.Map[String, mutable.ListBuffer[CSRNode]]()
  private val vertexNoEdgesEdgeId = "-1"

  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = {
    val mayBeAlreadyAdded = vertexContainer.get(vertex)
    val edgesKey = mayBeAlreadyAdded.fold {
      val edgeId = s"V${vertex.id}_E${edge.id}"
      vertexContainer(vertex) = edgeId
      edgeId
    } { existingIndex => existingIndex }

    edgeContainer.get(edgesKey).fold {
      edgeContainer(edgesKey) = mutable.ListBuffer(edge)
    } { existingEdges =>
      existingEdges += edge
    }
  }

  def vertex_?(vertex: CSRNode): Option[CSRNode] =
    vertexContainer.keys.find(_ == vertex)

  override def addVertex(vertex: CSRNode): Unit = {
    val mayBeAlreadyAdded = vertexContainer.get(vertex)
    if (mayBeAlreadyAdded.isEmpty)
      vertexContainer(vertex) = vertexNoEdgesEdgeId
  }

  override def allVertices: List[CSRNode] = vertexContainer.keys.toList

  private def allEdges: List[CSRNode] = edgeContainer.values.toList.flatten

  override def vertexLength: Int = nonEmptyVertexList.length

  override def edgeLength: Int = allEdges.size

  override def edgesForVertex(v: CSRNode): List[CSRNode] = {
    val edgeList = vertexContainer.get(v).flatMap { edgeId =>
      edgeContainer.get(edgeId)
    }

    edgeList.fold(List.empty[CSRNode])(lb => lb.toList)
  }

  override def edgesForVertexId(vid: Int): List[CSRNode] = edgesForVertex(CSRNode(vid, vid))

  override def nonEmptyVertexList: List[CSRNode] = allVertices

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
