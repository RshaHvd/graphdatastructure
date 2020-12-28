package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

case class ArrayALContainer(numVertex: Int) extends GraphContainer[SetBasedALNode] {

  private var arrayLen = numVertex.toInt
  private var vertexContainer = Array.ofDim[SetBasedALNode](numVertex.toInt)

  def add(vertex: SetBasedALNode, edge: SetBasedALNode): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id.toInt) = vertex
    addEdge(vertex, edge)

  }

  override def addEdge(vertex: SetBasedALNode, edge: SetBasedALNode): Unit = {
    val mayBeVertex = vertex_?(vertex)
    mayBeVertex match {
      case None => add(vertex, edge)
      case Some(c) =>
        resizeContainer(vertex)
        vertexContainer(vertex.id.toInt).addEdge(edge)
    }
  }

  override def addVertex(vertex: SetBasedALNode): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id.toInt) = vertex
  }

  override def allVertices: List[SetBasedALNode] = vertexContainer.toList

  def resizeContainer(n: SetBasedALNode) =
    if (n.id >= vertexContainer.size) {
      // println("resizing array .....")
      val doubleLen = arrayLen * 2
      val newLen = if (n.id < doubleLen) doubleLen else (n.id * 2)
      val newArray = Array.ofDim[SetBasedALNode](newLen.toInt)
      System.arraycopy(vertexContainer, 0, newArray, 0, arrayLen)
      //println(s"new array created with ${newLen} and previous array had size ${arrayLen} .....")
      vertexContainer = newArray
      arrayLen = newLen.toInt
      //println(s"done resize")
    }

  def vertex_?(vertex: SetBasedALNode): Option[SetBasedALNode] = {
    if (vertex.id >= vertexContainer.size) None
    else {
      val v1 = vertexContainer(vertex.id.toInt)
      if (v1 == null) None else Option(v1)
    }

  }

  override def edgeLength: Int = {
    val allEdges =
      for (i <- 0 to vertexLength - 1)
        yield nonEmptyVertexList(i).outgoingEdges().toList.size
    allEdges.sum
  }

  override def vertexLength: Int = nonEmptyVertexList.length

  override def nonEmptyVertexList = {
    val nonEmpties = vertexContainer.toList.filterNot(p => p == null)
    nonEmpties
  }

  override def edgesForVertex(v: SetBasedALNode): List[SetBasedALNode] =
    v.outgoingEdges().toList.sortBy(_.id)

  override def edgesForVertexId(vid: Int): List[SetBasedALNode] = {
    val mayBeVertex = vertexContainer(vid.toInt)
    if (mayBeVertex != null) { edgesForVertex(mayBeVertex) }
    else { Nil }
  }

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = {
    val vlist: List[SetBasedALNode] = this.allVertices
    val vlenToUse = mayBeNumberOfVertex.getOrElse(vertexLength - 1)

    for (i <- 0 to vlenToUse) {
      val n1 = vlist(i)
      val outGoingEdges = n1.outgoingEdges()
      println(
        s"[ ${n1.id} -> ( ${outGoingEdges.toList.map(_.id).sorted.mkString(",")} ) ]"
      )
    }
  }
}
