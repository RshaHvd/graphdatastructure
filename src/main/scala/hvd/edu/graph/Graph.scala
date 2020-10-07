package hvd.edu.graph

trait Node {
  def id: Int
  def value: Int
}


trait GraphContainer[n <: Node] {

  def addEdge(vertex: n, edge: n): Unit

  def addVertex(vertex: n): Unit

  def allVertices: List[n]

  def edgeLength: Int

  def vertexLength: Int

  def edgesForVertex(v: n): List[n]

  def edgesForVertexId(vid: Int): List[n]

  def nonEmptyVertexList: List[n]

  def print(mayBeNumberOfVertex: Option[Int]) : Unit
}


class Graph[N <: Node, C <: GraphContainer[N]](numVertex: Int, numEdges: Int)(implicit ev: ContainerMaker[N, C]) {

  private val graphContainer = ContainerMaker.apply[N, C](numVertex, numEdges)

  def addEdge(vertex: N, edgeNode: N): Unit = {
    // if vertex was already added
    graphContainer.addEdge(vertex, edgeNode)
//    val mayBeNode = graphContainer.vertex_?(vertex)
//    mayBeNode match {
//      case None => graphContainer.add(vertex, edgeNode)
//      case Some(c) => graphContainer.addEdge(vertex, edgeNode)
//    }
  }

  def addVertex(vertex: N): Unit = {
    graphContainer.addVertex(vertex)
  }

  def printGraph(mayBeVertexLen: Option[Int]): Unit = {
    println(s"************* Started Printing Graph *************")
    graphContainer.print(mayBeVertexLen)
    println(s"************* Finished Printing Graph *************")
  }

  def vertexList: List[N] = graphContainer.nonEmptyVertexList

  def vertexLength: Int = graphContainer.vertexLength

  def edgeLength: Int = graphContainer.edgeLength

  def edgesForVertex(v: N): List[N] = graphContainer.edgesForVertex(v)

  def edgesForVertexId(vid: Int): List[N] = graphContainer.edgesForVertexId(vid)

  def findVertexById(i: Int): Option[N] = graphContainer.nonEmptyVertexList.find(v => v.id == i)

}
