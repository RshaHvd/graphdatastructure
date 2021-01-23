package hvd.edu.graph

trait Node {
  def id(): Int
  def value(): Int
}

trait GraphContainer[n <: Node] {

  def addEdge(vertex: n, edge: n): Unit

  def addVertex(vertex: n): Unit

  def allVertices: List[n]

  def edgeLength: Int

  def vertexLength: Int

  def edgesForVertex(v: n): List[Int]

  def edgesForVertexId(vid: Int): List[Int]

  def nonEmptyVertexList: List[n]

  def range(vid1: Int, vid2: Int): List[Int]

  //def print(mayBeNumberOfVertex: Option[Int]): Unit
}

class Graph[N <: Node](graphContainer: GraphContainer[N]) {

  def addEdge(vertex: N, edgeNode: N): Unit = graphContainer.addEdge(vertex, edgeNode)

  def addVertex(vertex: N): Unit = graphContainer.addVertex(vertex)

  //  def printGraph(mayBeVertexLen: Option[Int]): Unit = {
  //    println(s"************* Started Printing Graph *************")
  //    graphContainer.print(mayBeVertexLen)
  //    println(s"************* Finished Printing Graph *************")
  //  }

  def vertexList: List[N] = graphContainer.nonEmptyVertexList

  def vertexLength: Int = graphContainer.vertexLength

  def edgeLength: Int = graphContainer.edgeLength

  def edgesForVertex(v: N): List[Int] = graphContainer.edgesForVertex(v)

  def edgesForVertexId(vid: Int): List[Int] = graphContainer.edgesForVertexId(vid)

  def rangeEdges(vid1: Int, vid2: Int): List[Int] = graphContainer.range(vid1, vid2)

  // why did I implement it this way ?
  def findVertexById(i: Int): Option[N] = graphContainer.nonEmptyVertexList.find(v => v.id == i)

}
