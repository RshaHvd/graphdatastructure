package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

class HashMapBasedALContainer(numVertex: Int) extends GraphContainer[AdjacencyListNode] {

  override def add(vertex: AdjacencyListNode, edge: AdjacencyListNode): Unit = ???

  override def addEdge(vertex: AdjacencyListNode, edge: AdjacencyListNode): Unit = ???

  override def allVertices: List[AdjacencyListNode] = ???

  override def vertex_?(vertex: AdjacencyListNode): Option[AdjacencyListNode] = ???

  override def edgeLength: Int = ???

  override def vertexLength: Int = ???

  override def edgesForVertex(v: AdjacencyListNode): List[AdjacencyListNode] = ???

  override def edgesForVertexId(vid: Int): List[AdjacencyListNode] = ???

  override def nonEmptyVertexList: List[AdjacencyListNode] = ???

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???

  override def addVertex(vertex: AdjacencyListNode): Unit = ???
}
