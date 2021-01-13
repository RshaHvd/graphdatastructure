package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class HashMapALContainer(numVertex: Int) extends GraphContainer[ALNode] {

  private val adjacencyListMap = mutable.Map[ALNode, mutable.ListBuffer[Int]]()

  override def addEdge(vertex: ALNode, edge: ALNode): Unit = {
    val findVertex = adjacencyListMap.get(vertex)
    findVertex.fold {
      // throw new RuntimeException("Cannot find the vertex to add edge too")}
      adjacencyListMap(vertex) = mutable.ListBuffer(edge.id)
    } { existingList =>
      existingList += edge.id
    }
  }

  override def allVertices: List[ALNode] =
    adjacencyListMap.keys.toList

  def vertex_?(vertex: ALNode): Option[ALNode] =
    adjacencyListMap.keys.find(_ == vertex)

  def hasVertex(vertex: ALNode): Boolean =
    adjacencyListMap.contains(vertex)

  override def edgeLength: Int =
    adjacencyListMap.values.flatten.size

  override def vertexLength: Int = adjacencyListMap.keySet.size

  override def edgesForVertex(v: ALNode): List[Int] = {
    adjacencyListMap.get(v).fold(List.empty[Int])(_.toList)
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    edgesForVertex(ALNode(vid, vid))
  }

  override def nonEmptyVertexList: List[ALNode] = allVertices

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???

  override def addVertex(vertex: ALNode): Unit =
    adjacencyListMap(vertex) = mutable.ListBuffer[Int]()
}
