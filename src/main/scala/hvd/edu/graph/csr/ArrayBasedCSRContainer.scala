package hvd.edu.graph.csr

class ArrayBasedCSRContainer(val numVertex: Int, val numEdges: Int) extends CSRContainers {
  private var vertexContainer = Array.fill[CSRNode](numVertex)(EmptyCSRNode)
  private var edgeContainer = Array.fill[CSRNode](numEdges)(EmptyCSRNode)
  private var vertexSize = 0
  private var lastEdgePointer = -1

  def add(vertex: CSRNode, edge: CSRNode): Unit = {
    resizeVertexContainer(vertex)
    val nextEdgePointer = lastEdgePointer + 1
    val vertextWithFirstIndex = vertex.copy(firstEdgeIndex = nextEdgePointer)
    vertexContainer(vertex.id) = vertextWithFirstIndex
    vertexSize += 1
    addToEdgeContainer(edge, nextEdgePointer)
  }

  def addEdge(vertex: CSRNode, edgeNode: CSRNode) = {
    val nextEdgePointer = lastEdgePointer + 1
    addToEdgeContainer(edgeNode, nextEdgePointer)
  }

  private def addToEdgeContainer(edgeNode: CSRNode, nextEdgePointer: Int) = {
    resizeEdgeContainer(nextEdgePointer)
    edgeContainer(nextEdgePointer) = edgeNode
    lastEdgePointer = nextEdgePointer
  }

  def vertex_?(vertex: CSRNode) =
    if (vertex.id >= vertexContainer.size) {
      EmptyCSRNode
    } else {
      vertexContainer(vertex.id)
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
      val newArray = Array.fill[CSRNode](newLen)(EmptyCSRNode)
      System.arraycopy(vertexContainer, 0, newArray, 0, currLen)
      vertexContainer = newArray
      vertexSize = newLen
    }
  }

  private def resizeEdgeContainer(lastEdgePointer: Int) = {
    if (lastEdgePointer >= edgeContainer.size) {
      val currLen = edgeContainer.size
      val doubleLen = currLen * 2
      val newArray = Array.fill[CSRNode](doubleLen)(EmptyCSRNode)
      System.arraycopy(edgeContainer, 0, newArray, 0, lastEdgePointer)
      edgeContainer = newArray
    }
  }

  override def edgesForVertex(v: CSRNode): List[CSRNode] = {
    val thisNodeId = v.id
    if (thisNodeId == vertexLength - 1) {
      /* Is last node ? */
      val edgesForLast: List[CSRNode] = allEdges.slice(
        v.firstEdgeIndex, allEdges.length).filterNot(
        p => p == EmptyCSRNode)
      edgesForLast
    } else {
      val nextNodeForThisNode = nonEmptyVertexList.find { v1 =>
        v1 != EmptyCSRNode && v1.id > thisNodeId
      }
      // if nothing is found - check is this is last node
      val ret: List[CSRNode] = nextNodeForThisNode.fold(List.empty[CSRNode]) {
        nn =>
          val endEdge = nn.firstEdgeIndex
          val edgeForNList: List[CSRNode] = allEdges.slice(
            v.firstEdgeIndex, endEdge).filterNot(
            p => p == EmptyCSRNode)
          edgeForNList
      }

      ret
    }
  }

  override def edgesForVertexId(vid: Int): List[CSRNode] = {
    nonEmptyVertexList.find(_.id == vid).fold(List.empty[CSRNode]) {
      edgesForVertex(_)
    }
  }

  override def nonEmptyVertexList: List[CSRNode] = {
    allVertices.filterNot { n => n == EmptyCSRNode }
  }

  override def print(mayBeVertexLen: Option[Int]) = {
    val unVlist = this.nonEmptyVertexList
    val vlist = unVlist/*.filterNot(_ == EmptyCSRNode)*/
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
        if (n2 != EmptyCSRNode) {
          val edgeListForN1 = for (i <- edgeStartIndex until edgeEndIndex)
            yield {
              if (i != -1) edgeList(i).id else -1
            }

          println(s"[ ${n1.id} -> ( ${edgeListForN1.mkString(",")} ) ]")
        }
    }

    // print the last vertex now
    val lastNode: CSRNode = vlist(this.vertexLength - 1)
    val edgesForLast =
      (lastNode.firstEdgeIndex until edgeList.size).iterator.takeWhile { j =>
        edgeList(j) != EmptyCSRNode
      }

    println(
      s"[ ${lastNode.id} -> ( ${edgesForLast.map(edgeList(_).id).mkString(",")} ) ]"
    )
  }
}
