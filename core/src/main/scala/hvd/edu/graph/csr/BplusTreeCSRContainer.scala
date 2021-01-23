package hvd.edu.graph.csr

import hvd.edu.collection.mutable.BPlusTreeImpl
import hvd.edu.graph.GraphContainer

import scala.collection.mutable

case class BplusTreeCSRContainer(numVertex: Int, fanout: Option[Int]) extends GraphContainer[CSRNode] {

  val defaultFanout = 5

  type EdgeIndex = Int
  private val vertexContainer = new BPlusTreeImpl[Int, EdgeIndex](fanout.getOrElse(defaultFanout))
  // TODO !! ArrayBuffer causing performance issues ?
  private val edgeContainer = mutable.ArrayBuffer[List[Int]]()
  private val vertexNoEdgesEdgeId = -1

  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = {

    val mayBeExistingEdgeIndex: Option[EdgeIndex] = vertexContainer.find(vertex.id)

    mayBeExistingEdgeIndex.fold {
      vertexContainer.add(vertex.id, edgeContainer.size)
      // insert into edgeContainer
      edgeContainer += List(edge.id)
    } { edgeIndex =>
      val existingEdges = edgeContainer(edgeIndex)
      val newEdges = (edge.id :: existingEdges)
      edgeContainer(edgeIndex) = newEdges
      edgeContainer
    }
  }

  override def addVertex(vertex: CSRNode): Unit = {
    // does not check for existence of value - so can add duplciates...
    vertexContainer.add(vertex.id, vertexNoEdgesEdgeId)
  }

  override def allVertices: List[CSRNode] = {
    val allVids = vertexContainer.getLeaves()
    allVids.map(v => CSRNode(v, v))
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

    //ll.sorted
    ll
  }

  override def nonEmptyVertexList: List[CSRNode] = {
    val allKV = vertexContainer.getAllKeyValues()
    val nonEmptyKV = allKV.filterNot {
      case (k1, v1) =>
        v1 == vertexNoEdgesEdgeId
    }
    nonEmptyKV.map(v => CSRNode(v._1, v._1))
  }

  override def range(vid1: EdgeIndex, vid2: EdgeIndex): List[Int] = {
    val allEdgedIndexInRanges = vertexContainer.range(vid1, vid2)
    val allEdges = allEdgedIndexInRanges.flatMap(edgesForVertexId(_))
    allEdges
  }
}
