package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

class HashMapALContainer(numVertex: Int) extends GraphContainer[DefaultALNode] {

  private val adjacencyListMap = mutable.Map[DefaultALNode, List[DefaultALNode]]()

  override def addEdge(vertex: DefaultALNode, edge: DefaultALNode): Unit = {
    val findVertex = adjacencyListMap.get(vertex)
    findVertex.fold {
      // throw new RuntimeException("Cannot find the vertex to add edge too")}
      adjacencyListMap(vertex) = List(edge)
    } { existingList =>
      val newLL = existingList.+:(edge)
      adjacencyListMap(vertex) = newLL
    }
  }

  override def allVertices: List[DefaultALNode] =
    adjacencyListMap.keys.toList

  def vertex_?(vertex: DefaultALNode): Option[DefaultALNode] =
    adjacencyListMap.keys.find(_ == vertex)

  def hasVertex(vertex: DefaultALNode): Boolean = adjacencyListMap.contains(vertex)

  override def edgeLength: Int =
    adjacencyListMap.values.flatten.size

  override def vertexLength: Int = adjacencyListMap.keySet.size

  override def edgesForVertex(v: DefaultALNode): List[DefaultALNode] =
    adjacencyListMap.get(v).getOrElse(List.empty[DefaultALNode])

  override def edgesForVertexId(vid: Int): List[DefaultALNode] = {
    val mayBeALNode = adjacencyListMap.keySet.find { v =>
      v.id == vid
    }

    mayBeALNode.fold(List.empty[DefaultALNode])(edgesForVertex(_))
  }

  override def nonEmptyVertexList: List[DefaultALNode] = allVertices

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???

  override def addVertex(vertex: DefaultALNode): Unit =
    adjacencyListMap(vertex) = List.empty[DefaultALNode]
}
