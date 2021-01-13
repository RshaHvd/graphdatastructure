package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class ArrayALContainer(numVertex: Int) extends GraphContainer[ALNode] {

  private var arrayLen = numVertex.toInt
  private var vertexContainer = Array.ofDim[mutable.ListBuffer[Int]](numVertex.toInt)

  //  def add(vertex: ALNode, edge: ALNode): Unit = {
  //    resizeContainer(vertex)
  //   // vertexContainer(vertex.id) = vertex
  //    addEdge(vertex, edge)
  //
  //  }

  override def addEdge(vertex: ALNode, edge: ALNode): Unit = {
    //    val mayBeVertex = vertex_?(vertex)
    //    mayBeVertex match {
    //      case None => add(vertex, edge)
    //      case Some(c) =>
    //        resizeContainer(vertex)
    //        vertexContainer(vertex.id).addEdge(edge)
    //    }
    resizeContainer(vertex)
    val v1 = vertexContainer(vertex.id)
    if (v1 == null) {
      val edgesList = mutable.ListBuffer[Int](edge.id)
      vertexContainer(vertex.id) = edgesList
    }
    else {
      v1.append(edge.id)
    }
  }

  override def addVertex(vertex: ALNode): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id) = mutable.ListBuffer()
  }

  override def allVertices: List[ALNode] = {
    //    val ids = vertexContainer.toList.zipWithIndex{
    //      case (idx, _) => if(idx == null) None else Option(idx)
    //    }
    // ids.map(ALNode(_, _))
    nonEmptyVertexList

  }

  def resizeContainer(n: ALNode) =
    if (n.id >= vertexContainer.size) {
      // println("resizing array .....")
      val doubleLen = arrayLen * 2
      val newLen = if (n.id < doubleLen) doubleLen else (n.id * 2)
      val newArray = Array.ofDim[mutable.ListBuffer[Int]](newLen.toInt)
      System.arraycopy(vertexContainer, 0, newArray, 0, arrayLen)
      //println(s"new array created with ${newLen} and previous array had size ${arrayLen} .....")
      vertexContainer = newArray
      arrayLen = newLen.toInt
      //println(s"done resize")
    }

  //  def vertex_?(vertex: ALNode): Option[ALNode] = {
  //    if (vertex.id >= vertexContainer.size) None
  //    else {
  //      val v1 = vertexContainer(vertex.id)
  //      if (v1 == null) None else Option(v1)
  //    }
  //
  //  }

  override def edgeLength: Int = {
    val allEdges = for (i <- 0 to vertexContainer.size - 1; if vertexContainer(i) != null) yield {
      vertexContainer(i).toList
    }
    allEdges.flatten.toList.size
  }

  override def vertexLength: Int = nonEmptyVertexList.length

  override def nonEmptyVertexList = {
    val nodes = for (i <- 0 to vertexContainer.size - 1; if vertexContainer(i) != null) yield {
      ALNode(i, i)
    }
    nodes.toList
  }

  override def edgesForVertex(v: ALNode): List[Int] = {
    //  val e = vertexContainer(v.id)
    // if(e != null) e.toList else List.empty[Int]
    edgesForVertexId(v.id)
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    val mayBeVertex = vertexContainer(vid.toInt)
    if (mayBeVertex != null) { mayBeVertex.toList } else { Nil }
  }

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = {
    val vlist: List[ALNode] = this.allVertices
    val vlenToUse = mayBeNumberOfVertex.getOrElse(vertexLength - 1)

    for (i <- 0 to vlenToUse) {
      val n1 = vlist(i)
      val outGoingEdges = vertexContainer(n1.id)
      println(
        s"[ ${n1.id} -> ( ${outGoingEdges.toList.sorted.mkString(",")} ) ]"
      )
    }
  }
}
