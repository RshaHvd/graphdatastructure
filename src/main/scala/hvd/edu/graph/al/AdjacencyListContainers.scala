package hvd.edu.graph.al

import hvd.edu.graph.csr.CSRNode

trait AdjacencyListContainers {

  def add(vertex: AdjacencyListNode, edge: AdjacencyListNode): Unit

  def addEdge(vertex: AdjacencyListNode, edge: AdjacencyListNode): Unit

  def allVertices : List[AdjacencyListNode]

  def vertex_?(vertex: AdjacencyListNode) : AdjacencyListNode

  def edgeLength : Int

  def vertexLength: Int

  def edgesForVertex(v: AdjacencyListNode): List[AdjacencyListNode]

  def edgesForVertexId(vid: Int): List[AdjacencyListNode]

  def nonEmptyVertexList : List[AdjacencyListNode]

  def print(mayBeNumberOfVertex: Option[Int])

}


class ArrayBasedALContainer(numVertex: Int) extends AdjacencyListContainers{

  private var arrayLen = numVertex
  private var vertexContainer = Array.fill[AdjacencyListNode](numVertex)(EmptyAdjacencyNode)

  override def add(vertex: AdjacencyListNode, edge: AdjacencyListNode): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id) = vertex
    if(edge != EmptyAdjacencyNode) {
      vertex.addEdge(edge)
    }
  }

  override def addEdge(vertex: AdjacencyListNode, edge: AdjacencyListNode): Unit = {
    vertexContainer(vertex.id).addEdge(edge)
  }

  override def allVertices: List[AdjacencyListNode] = vertexContainer.toList

  def resizeContainer(n: AdjacencyListNode) = {
    if (n.id >= vertexContainer.size) {
      // println("resizing array .....")
      val doubleLen = arrayLen * 2
      val newLen = if (n.id < doubleLen) doubleLen else (n.id * 2)
      val newArray = Array.fill[AdjacencyListNode](newLen)(EmptyAdjacencyNode)
      System.arraycopy(vertexContainer, 0, newArray, 0, arrayLen)
      //println(s"new array created with ${newLen} and previous array had size ${arrayLen} .....")
      vertexContainer = newArray
      arrayLen = newLen
      //println(s"done resize")
    }
  }

  override def vertex_?(vertex: AdjacencyListNode): AdjacencyListNode = {
    if(vertex.id >= vertexContainer.size) EmptyAdjacencyNode else vertexContainer(vertex.id)
  }

  override def edgeLength: Int = {
      val allEdges = for (i <- 0 to vertexLength - 1) yield {
        nonEmptyVertexList(i).outgoingEdges.toList.size
      }
      allEdges.sum
  }

  override def vertexLength: Int = nonEmptyVertexList.length

  override def nonEmptyVertexList = {
    val nonEmpties = vertexContainer.filterNot { v =>
      v == EmptyAdjacencyNode
    }

    nonEmpties.toList
  }

  override def edgesForVertex(v: AdjacencyListNode): List[AdjacencyListNode] = {
    v.outgoingEdges.toList.sortBy(_.id)
  }

  override def edgesForVertexId(vid: Int): List[AdjacencyListNode] = {
    vertexContainer.find(_.id == vid).fold(List.empty[AdjacencyListNode]) {
      edgesForVertex(_)
    }
  }

  override def print(mayBeNumberOfVertex: Option[Int]): Unit ={
    val vlist: List[AdjacencyListNode] = this.allVertices
    val vlenToUse = mayBeNumberOfVertex.getOrElse(vertexLength - 1)

    for (i <- 0 to vlenToUse) {
      val n1 = vlist(i)
      val outGoingEdges = n1.outgoingEdges
      println(
        s"[ ${n1.id} -> ( ${outGoingEdges.toList.map(_.id).sorted.mkString(",")} ) ]"
      )
    }
  }
}
