package hvd.edu.graph.csr

import hvd.edu.graph.GraphContainer

class ArrayBasedCSRContainer(val numVertex: Int, val numEdges: Int) extends GraphContainer[CSRNode] {
  private var vertexContainer = Array.ofDim[CSRNode](numVertex)
  private var edgeContainer = Array.ofDim[CSRNode](numEdges)
  private var vertexSize = 0
  private var lastEdgePointer = -1

  private def add(vertex: CSRNode, edge: CSRNode): Unit = {
    resizeVertexContainer(vertex)
    val nextEdgePointer = lastEdgePointer + 1
    val vertextWithFirstIndex = vertex.copy(firstEdgeIndex = nextEdgePointer)
    vertexContainer(vertex.id) = vertextWithFirstIndex
    vertexSize += 1
    addToEdgeContainer(edge, nextEdgePointer)
  }

  def addEdge(vertex: CSRNode, edgeNode: CSRNode) = {
    if(vertex_?(vertex).nonEmpty) {
      val nextEdgePointer = lastEdgePointer + 1
      addToEdgeContainer(edgeNode, nextEdgePointer)
    }else{
      add(vertex, edgeNode)
    }
  }

  def addVertex(vertex: CSRNode) = {
    resizeVertexContainer(vertex)
    vertexContainer(vertex.id) = vertex
  }

  private def addToEdgeContainer(edgeNode: CSRNode, nextEdgePointer: Int) = {
    resizeEdgeContainer(nextEdgePointer)
    edgeContainer(nextEdgePointer) = edgeNode
    lastEdgePointer = nextEdgePointer
  }

  def vertex_?(vertex: CSRNode) =
    if (vertex.id >= vertexContainer.size) {
      None
    } else {
      Option(vertexContainer(vertex.id))
    }

  def allVertices = vertexContainer.toList

  def allEdges = edgeContainer.toList

  def vertexLength = nonEmptyVertexList.length

  def edgeLength = lastEdgePointer + 1 // as it starts at 0

  private def resizeVertexContainer(n: CSRNode) = {
    if (n.id >= vertexContainer.size) {
      val currLen = vertexContainer.size
      val doubleLen = currLen * 2
      val newLen = if (n.id < doubleLen) doubleLen else (n.id * 2)
      val newArray = Array.ofDim[CSRNode](newLen)
      System.arraycopy(vertexContainer, 0, newArray, 0, currLen)
      vertexContainer = newArray
      vertexSize = newLen
    }
  }

  private def resizeEdgeContainer(lastEdgePointer: Int) = {
    if (lastEdgePointer >= edgeContainer.size) {
      val currLen = edgeContainer.size
      val doubleLen = currLen * 2
      val newArray = Array.ofDim[CSRNode](doubleLen)
      System.arraycopy(edgeContainer, 0, newArray, 0, lastEdgePointer)
      edgeContainer = newArray
    }
  }

  private def verticesWithEdges = {
    nonEmptyVertexList.filter{ c => c.firstEdgeIndex != -1}
  }

  def lastVertexOrAllFollowingVertexHaveNoEdges(v: CSRNode): Boolean = {
    (v.id == vertexLength) || verticesWithEdges.filter(_.id > v.id).isEmpty
  }

  override def edgesForVertex(v: CSRNode): List[CSRNode] = {
    val thisNodeId = v.id
      val nextNodeForThisNodeWithEdge = verticesWithEdges.collectFirst {
        case v1 if v1.id > thisNodeId => v1
      }
       val nextNodeForThisNode = if(nextNodeForThisNodeWithEdge.isEmpty){
         nonEmptyVertexList.collectFirst{ case v1 if v1.id > thisNodeId => v1}
       }else{nextNodeForThisNodeWithEdge}

      val ret: List[CSRNode] = nextNodeForThisNode.fold{
        if(v.id == vertexLength && v.firstEdgeIndex != -1){
          allEdges.slice(v.firstEdgeIndex, edgeLength)
        }
        else {
          List.empty[CSRNode]
        }
      } {
        nn =>
          (v.firstEdgeIndex, nn.firstEdgeIndex) match {
            case (-1, _) => List.empty[CSRNode]
            case (x, -1) if lastVertexOrAllFollowingVertexHaveNoEdges(v) => allEdges.slice(x, edgeLength)
            case (x, -1) => List(allEdges(x))
            case (x, y) => allEdges.slice(x, y)
          }
      }
      ret
    }

  override def edgesForVertexId(vid: Int): List[CSRNode] = {
    nonEmptyVertexList.find(_.id == vid).fold(List.empty[CSRNode]) {
      edgesForVertex(_)
    }
  }

  override def nonEmptyVertexList: List[CSRNode] = {
    allVertices.collect { case p: CSRNode => p }
  }

  override def print(mayBeVertexLen: Option[Int]) = {
    val unVlist = this.nonEmptyVertexList
    val vlist = unVlist
    println(s"Total vertex len: ${vlist.size}")
    val edgeList = this.allEdges
    println(s"Total Edges : ${edgeList.size}")

    val zippedVertices = vlist.zip(vlist.tail)

    val vListPair = mayBeVertexLen.fold(zippedVertices) { x =>
      zippedVertices.take(x + 1)
    }

    vListPair.foreach {
      case (n1, n2) =>
        val edgeStartIndex = n1.firstEdgeIndex
        val edgeEndIndex = n2.firstEdgeIndex
        //if (n2 != EmptyCSRNode) {
        val edgeListForN1 = for (i <- edgeStartIndex until edgeEndIndex)
          yield {
            if (i != -1) edgeList(i).id else -1
          }

        println(s"[ ${n1.id} -> ( ${edgeListForN1.mkString(",")} ) ]")
    }

    // print the last vertex now
    val lastNode: CSRNode = vlist(this.vertexLength - 1)
    val edgesForLast =
      (lastNode.firstEdgeIndex until edgeList.size).iterator.takeWhile { j =>
        edgeList(j).isInstanceOf[CSRNode] // TODO: Fix this !!!
      }

    println(
      s"[ ${lastNode.id} -> ( ${edgesForLast.map(edgeList(_).id).mkString(",")} ) ]"
    )
  }
}
