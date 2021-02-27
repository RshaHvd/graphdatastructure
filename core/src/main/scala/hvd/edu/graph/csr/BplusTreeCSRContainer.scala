package hvd.edu.graph.csr

import hvd.edu.collection.mutable.BPlusTreeImpl
import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class BplusTreeCSRContainer(numVertex: Int, fanout: Option[Int]) extends GraphContainer[CSRNode] {

  val defaultFanout = 5

  type EdgeIndex = Int
  private val vertexContainer = new BPlusTreeImpl[Int, EdgeIndex](fanout.getOrElse(defaultFanout))
  // TODO !! ArrayBuffer causing performance issues ?
  private var edgeContainer = Array.ofDim[List[Int]](numVertex.toInt)
  private var lastEdgePointer = -1
  private val vertexNoEdgesEdgeId = -1

  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = {

    // just use the first edgeId as the value in the BplusTree to look up data in edgeContainer.
    val mayBeExistingEdgeIndex: Option[EdgeIndex] = vertexContainer.find(vertex.id)

    mayBeExistingEdgeIndex.fold {
      incrementEdgePointer()
      vertexContainer.add(vertex.id, lastEdgePointer)
      // insert into edgeContainer - resize edgeContainer if needed.
      edgeContainer(lastEdgePointer) = List(edge.id)
    } { edgeIndex =>
      val existingEdges = edgeContainer(edgeIndex)
      val newEdges = (edge.id :: existingEdges)
      edgeContainer(edgeIndex) = newEdges
    }
  }

  def incrementEdgePointer() = {
    lastEdgePointer += 1
  }

  override def addVertex(vertex: CSRNode): Unit = {
    // does not check for existence of value - so can add duplciates...
    incrementEdgePointer()
    vertexContainer.add(vertex.id, lastEdgePointer)
    edgeContainer(lastEdgePointer) = List.empty[Int]
  }

  override def allVertices: List[CSRNode] = {
    val allVids = vertexContainer.getLeaves()
    allVids.map(v => CSRNode(v))
  }

  override def edgeLength: Int = {
    val lengthOfEachEdge = vertexContainer.getAllValues().map { edgeIndex =>
      if (edgeIndex == vertexNoEdgesEdgeId)
        0
      else
        edgeContainer(edgeIndex).size
    }
    lengthOfEachEdge.sum
  }

  override def vertexLength: Int = allVertices.size

  override def edgesForVertex(v: CSRNode): List[Int] = {
    edgesForVertexId(v.id)
  }

  override def edgesForVertexId(vid: EdgeIndex): List[Int] = {
    val foundEdge = vertexContainer.find(vid).filterNot(eI => eI == vertexNoEdgesEdgeId)
    val ll = foundEdge.map { edgeIndex =>
      edgeContainer(edgeIndex)
    }.getOrElse(Nil)

    ll
  }

  override def nonEmptyVertexList: List[CSRNode] = {
    val allKV = vertexContainer.getAllKeyValues()
    val nonEmptyKV = allKV.filterNot {
      case (k1, v1) => v1 == vertexNoEdgesEdgeId
    }
    nonEmptyKV.map(v => CSRNode(v._1))
  }

  override def range(vid1: EdgeIndex, vid2: EdgeIndex): List[Int] = {
    val allEdgedIndexInRanges = vertexContainer.range(vid1, vid2)
    val allEdges = allEdgedIndexInRanges.map {
      (edgeContainer(_))
    }
    //allEdgedIndexInRanges.flatMap(edgesForVertexId(_))
    allEdges.flatMap(_.toList)
  }
}
