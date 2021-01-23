package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class ArrayALContainer(numVertex: Int) extends GraphContainer[ALNode] {

  private var arrayLen = numVertex.toInt
  private var vertexContainer = Array.ofDim[mutable.ListBuffer[Int]](numVertex.toInt)

  override def addEdge(vertex: ALNode, edge: ALNode): Unit = {
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
    nonEmptyVertexList
  }

  def resizeContainer(n: ALNode) =
    if (n.id >= vertexContainer.size) {
      // println("resizing array .....")
      val doubleLen = arrayLen * 2
      val newLen = if (n.id < doubleLen) doubleLen else (n.id * 2)
      val newArray = Array.ofDim[mutable.ListBuffer[Int]](newLen.toInt)
      System.arraycopy(vertexContainer, 0, newArray, 0, arrayLen)
      vertexContainer = newArray
      arrayLen = newLen.toInt
      //println(s"done resize")
    }

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
    edgesForVertexId(v.id)
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    val mayBeVertex = vertexContainer(vid.toInt)
    if (mayBeVertex != null) { mayBeVertex.toList } else { Nil }
  }

  //  override def print(mayBeNumberOfVertex: Option[Int]): Unit = {
  //    val vlist: List[ALNode] = this.allVertices
  //    val vlenToUse = mayBeNumberOfVertex.getOrElse(vertexLength - 1)
  //
  //    for (i <- 0 to vlenToUse) {
  //      val n1 = vlist(i)
  //      val outGoingEdges = vertexContainer(n1.id)
  //      println(
  //        s"[ ${n1.id} -> ( ${outGoingEdges.toList.sorted.mkString(",")} ) ]"
  //      )
  //    }
  //  }

  override def range(vid1: Int, vid2: Int): List[Int] = {
    val allRangeEdges = for (v <- vid1 to vid2) yield {
      edgesForVertexId(v)
    }
    allRangeEdges.flatten.toList
  }
}
