package hvd.edu.graph.csr

import scala.collection.mutable

class HashMapBasedCSRContainer(val numVertex: Int, val numEdges: Int) extends CSRContainers {

  private val vertexContainer = mutable.Map[CSRNode, String]()
  private val edgeContainer = mutable.Map[String, List[CSRNode]]()

  override def add(vertex: CSRNode, edge: CSRNode): Unit = {
    val mayBeAlreadyAdded = vertexContainer.get(vertex)
    val edgesKey = mayBeAlreadyAdded.fold {
      val edgeId = s"V${vertex.id}_E${edge.id}"
      vertexContainer(vertex) = edgeId
      edgeId
    } { existingIndex => existingIndex }

    val edges = edgeContainer.get(edgesKey).toList.flatten
    val updatedEdges =  edges.:+(edge)
    edgeContainer(edgesKey) = updatedEdges
  }

  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = {
    val edgesKey = vertexContainer(vertex)
    val edges = edgeContainer(edgesKey)
    val updatedEdges = edges.:+(edge)
    edgeContainer(edgesKey) = updatedEdges
  }

  override def vertex_?(vertex: CSRNode): CSRNode = {
   vertexContainer.keys.find(_ == vertex).getOrElse(EmptyCSRNode)
  }

  override def allVertices: List[CSRNode] = vertexContainer.keys.toList

  private def allEdges: List[CSRNode] = edgeContainer.values.toList.flatten

  override def vertexLength: Int = nonEmptyVertexList.length

  override def edgeLength: Int = allEdges.size

  override def edgesForVertex(v: CSRNode): List[CSRNode] = {
    val edgeList = vertexContainer.get(v).flatMap{
      edgeId => edgeContainer.get(edgeId)
    }.getOrElse(List.empty[CSRNode])

    edgeList.filterNot(_ == EmptyCSRNode)
  }

  override def edgesForVertexId(vid: Int): List[CSRNode] = {
    edgesForVertex(CSRNode(vid, vid))
  }

  override def nonEmptyVertexList: List[CSRNode] = {
    allVertices
  }

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
