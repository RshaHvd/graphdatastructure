package hvd.edu

trait Node {

  def id: Int

}

trait Graph[n <: Node] {

  def vertexList: List[n]

  def edgesForVertex(vertex: n): List[n]

  def edgesForVertexId(vid: Int): List[n]

  def addEdge(vertex: n, edge: n): Unit

  def addVertex(vertex: n): Unit

  def printGraph(mayBeNumberOfVertex: Option[Int]): Unit

  def vertexLength: Int

  def edgeLength: Int

}

//// this is not scalable....
//trait ImmutableGraph[Node] extends Graph[Node]{
//
//  def addEdge(u: Node, v: Node, g: Graph[Node]): Graph[Node]
//
//  def addVertex(v: Node,  g: Graph[Node]): Graph[Node]
//}
