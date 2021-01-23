package hvd.edu.graph.csr

import hvd.edu.graph.GraphContainer

case class ArrayCSRContainer(val numVertex: Int, val numEdges: Int) extends GraphContainer[CSRNode] {
  private var vertexContainer = Array.ofDim[CSRNode](numVertex.toInt)
  private var edgeContainer = Array.ofDim[Int](numEdges.toInt)
  private var vertexSize = 0
  private var lastEdgePointer: Int = -1
  private var currentVid: Option[Int] = None

  private def add(vertex: CSRNode, edge: CSRNode): Unit = {
    resizeVertexContainer(vertex)
    val nextEdgePointer = lastEdgePointer + 1
    val vertexWithFirstIndex = vertex.copy(firstEdgeIndex = nextEdgePointer)
    vertexContainer(vertex.id.toInt) = vertexWithFirstIndex
    // set the nextVertex of prevvertex
    val currVertex = currentVid.foreach { cvid =>
      val cVertex = vertexContainer(cvid)
      cVertex.setNextId(vertex.id)
    }
    vertexSize += 1
    currentVid = Option(vertex.id)
    addToEdgeContainer(edge, nextEdgePointer)
  }

  def addEdge(vertex: CSRNode, edgeNode: CSRNode) =
    if (vertex_?(vertex).nonEmpty) {
      val nextEdgePointer = lastEdgePointer + 1
      addToEdgeContainer(edgeNode, nextEdgePointer)
    }
    else {
      add(vertex, edgeNode)
    }

  def addVertex(vertex: CSRNode) = {
    resizeVertexContainer(vertex)
    vertexContainer(vertex.id) = vertex
  }

  private def addToEdgeContainer(edgeNode: CSRNode, nextEdgePointer: Int) = {
    resizeEdgeContainer(nextEdgePointer)
    edgeContainer(nextEdgePointer) = edgeNode.id
    lastEdgePointer = nextEdgePointer
  }

  def vertex_?(vertex: CSRNode) = {
    if (vertex.id >= vertexContainer.size)
      None
    else {
      val mayBeVId = vertexContainer(vertex.id)
      if (mayBeVId == null) {
        None
      }
      else {
        Option(mayBeVId)
      }
    }
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
      val newArray = Array.ofDim[Int](doubleLen)
      System.arraycopy(edgeContainer, 0, newArray, 0, lastEdgePointer)
      edgeContainer = newArray
    }
  }

  private def verticesWithEdges = {
    nonEmptyVertexList.filter(c => c.firstEdgeIndex != -1)
  }

  def lastVertexOrAllFollowingVertexHaveNoEdges(v: CSRNode): Boolean = {
    (v.id == vertexLength) || verticesWithEdges.filter(_.id > v.id).isEmpty
  }

  override def edgesForVertex(v: CSRNode): List[Int] = {
    val readNextNode = v.getNextId()
    val nextNodeForThisNode = if (readNextNode == -1) None else Option(vertexContainer(readNextNode))
    val ret: List[Int] = nextNodeForThisNode.fold {
      if (nextNodeForThisNode.isEmpty && v.firstEdgeIndexAsInt != -1) // penultimate node
        allEdges.slice(v.firstEdgeIndexAsInt, edgeLength)
      else
        List.empty[Int] // last node
    } { nn =>
      (v.firstEdgeIndexAsInt, nn.firstEdgeIndexAsInt) match {
        case (-1, _) => List.empty[Int]
        case (x, -1) if lastVertexOrAllFollowingVertexHaveNoEdges(v) => edgeContainer.slice(x, edgeLength).toList
        case (x, -1) => List(edgeContainer(x))
        case (x, y) => edgeContainer.slice(x, y).toList
      }
    }
    ret
  }

  override def edgesForVertexId(vid: Int): List[Int] = {
    val mayBeVertex = vertexContainer(vid)
    if (mayBeVertex == null) {
      Nil
    }
    else {
      edgesForVertex(mayBeVertex)
    }

  }

  override def nonEmptyVertexList: List[CSRNode] = {
    allVertices.filterNot(p => p == null)
    //collect { case p: CSRNode => p }
  }

  override def range(vid1: Int, vid2: Int): List[Int] = {
    val allRangeEdges = for (v <- vid1 to vid2) yield {
      edgesForVertexId(v)
    }
    allRangeEdges.flatten.toList

  }
}
