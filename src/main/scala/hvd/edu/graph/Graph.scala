package hvd.edu.graph

trait Node {
  def id: Int
  def value: Int
}


trait GraphContainer[n <: Node] {

  def add(vertex: n, edge: n): Unit

  def addEdge(vertex: n, edge: n): Unit

  def addVertex(vertex: n): Unit

  def allVertices: List[n]

  def vertex_?(vertex: n): Option[n]

  def edgeLength: Int

  def vertexLength: Int

  def edgesForVertex(v: n): List[n]

  def edgesForVertexId(vid: Int): List[n]

  def nonEmptyVertexList: List[n]

  def print(mayBeNumberOfVertex: Option[Int])
}


class Graph[N <: Node, C <: GraphContainer[N]](numVertex: Int, numEdges: Int)(implicit ev: ContainerMaker[N, C]) {

  private val graphContainer = ContainerMaker.apply[N, C](numVertex, numEdges)

  def addEdge(vertex: N, edgeNode: N): Unit = {
    // if vertex was already added
    val mayBeNode = graphContainer.vertex_?(vertex)
    mayBeNode match {
      case None => graphContainer.add(vertex, edgeNode)
      case Some(c) => graphContainer.addEdge(vertex, edgeNode)
    }
  }

  def addVertex(vertex: N): Unit = {
    graphContainer.addVertex(vertex)
  }

  def printGraph(mayBeVertexLen: Option[Int]): Unit = {
    println(s"************* Printing CSR Graph *************")
    graphContainer.print(mayBeVertexLen)
    println(s"************* Finished Printing CSR Graph *************")
  }

  def vertexList: List[N] = graphContainer.nonEmptyVertexList

  def vertexLength: Int = graphContainer.vertexLength

  def edgeLength: Int = graphContainer.edgeLength

  def edgesForVertex(v: N): List[N] = graphContainer.edgesForVertex(v)

  def edgesForVertexId(vid: Int): List[N] = graphContainer.edgesForVertexId(vid)

}

//// this is not scalable....
//trait ImmutableGraph[Node] extends Graph[Node]{
//
//  def addEdge(u: Node, v: Node, g: Graph[Node]): Graph[Node]
//
//  def addVertex(v: Node,  g: Graph[Node]): Graph[Node]
//}
