package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class HashMapALContainer(numVertex: Int) extends GraphContainer[ALNode] {

  private val adjacencyListMap = mutable.Map[Int, mutable.ListBuffer[Int]]()

  override def addEdge(vertex: ALNode, edge: ALNode): Unit = {
    val findVertex = adjacencyListMap.get(vertex.id)
    findVertex.fold { adjacencyListMap(vertex.id) = mutable.ListBuffer(edge.id) } {
      existingList => existingList += edge.id
    }
  }

  override def allVertices: List[ALNode] = {
    val vids = adjacencyListMap.keys
    vids.map(v => ALNode(v, v)).toList
  }

  def hasVertex(vertex: ALNode): Boolean = {
    adjacencyListMap.contains(vertex.id)
  }

  override def edgeLength: Int = adjacencyListMap.values.flatten.size

  override def vertexLength: Int = adjacencyListMap.keySet.size

  override def edgesForVertex(v: ALNode): List[Int] = {
    edgesForVertexId(v.id)
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    adjacencyListMap.get(vid).fold(List.empty[Int])(_.toList)
  }

  override def nonEmptyVertexList: List[ALNode] = allVertices

  override def addVertex(vertex: ALNode): Unit = {
    adjacencyListMap(vertex.id) = mutable.ListBuffer[Int]()
  }

  override def range(vid1: Int, vid2: Int): List[Int] = {
    val allRangeEdges = for (v <- vid1 to vid2) yield {
      edgesForVertexId(v)
    }
    allRangeEdges.flatten.toList
  }
}
