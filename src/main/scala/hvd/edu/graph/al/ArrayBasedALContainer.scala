package hvd.edu.graph.al

import hvd.edu.graph.GraphContainer

class ArrayBasedALContainer(numVertex: Int) extends GraphContainer[ALNodeWithSetBasedEdges]{

  private var arrayLen = numVertex
  private var vertexContainer = Array.ofDim[ALNodeWithSetBasedEdges](numVertex)

  override def add(vertex: ALNodeWithSetBasedEdges, edge: ALNodeWithSetBasedEdges): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id) = vertex
    addEdge(vertex, edge)

  }

  override def addEdge(vertex: ALNodeWithSetBasedEdges, edge: ALNodeWithSetBasedEdges): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id).addEdge(edge)
  }

  override def addVertex(vertex: ALNodeWithSetBasedEdges): Unit = {
    resizeContainer(vertex)
    vertexContainer(vertex.id) = vertex
  }

  override def allVertices: List[ALNodeWithSetBasedEdges] = vertexContainer.toList

  def resizeContainer(n: ALNodeWithSetBasedEdges) = {
    if (n.id >= vertexContainer.size) {
      // println("resizing array .....")
      val doubleLen = arrayLen * 2
      val newLen = if (n.id < doubleLen) doubleLen else (n.id * 2)
      val newArray = Array.ofDim[ALNodeWithSetBasedEdges](newLen)
      System.arraycopy(vertexContainer, 0, newArray, 0, arrayLen)
      //println(s"new array created with ${newLen} and previous array had size ${arrayLen} .....")
      vertexContainer = newArray
      arrayLen = newLen
      //println(s"done resize")
    }
  }

  override def vertex_?(vertex: ALNodeWithSetBasedEdges): Option[ALNodeWithSetBasedEdges] = {
    if(vertex.id >= vertexContainer.size) None
    else vertexContainer.collectFirst{
      case v: ALNodeWithSetBasedEdges if v.id == vertex.id => v
    }
  }

  override def edgeLength: Int = {
      val allEdges = for (i <- 0 to vertexLength - 1) yield {
        nonEmptyVertexList(i).outgoingEdges.toList.size
      }
      allEdges.sum
  }

  override def vertexLength: Int = nonEmptyVertexList.length

  override def nonEmptyVertexList = {
    val nonEmpties = vertexContainer.collect{ case v: ALNodeWithSetBasedEdges=> v}
    nonEmpties.toList
  }

  override def edgesForVertex(v: ALNodeWithSetBasedEdges): List[ALNodeWithSetBasedEdges] = {
    v.outgoingEdges.toList.sortBy(_.id)
  }

  override def edgesForVertexId(vid: Int): List[ALNodeWithSetBasedEdges] = {
    vertexContainer.collectFirst{ case v: ALNodeWithSetBasedEdges if v.id == vid => v
    }.fold(List.empty[ALNodeWithSetBasedEdges]) {
      edgesForVertex(_)
    }
  }

  override def print(mayBeNumberOfVertex: Option[Int]): Unit ={
    val vlist: List[ALNodeWithSetBasedEdges] = this.allVertices
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
