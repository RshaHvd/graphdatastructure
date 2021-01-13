package hvd.edu.graph.csr

import hvd.edu.collection.mutable.BPlusTreeImpl
import hvd.edu.graph.GraphContainer
import CSRNodeUtils._

import scala.collection.mutable

case class BplusTreeCSRContainer(numVertex: Int, fanout: Option[Int]) extends GraphContainer[CSRNode] {

  val defaultFanout = 5

  type EdgeIndex = Int
  private val vertexContainer = new BPlusTreeImpl[CSRNode, EdgeIndex](fanout.getOrElse(defaultFanout))
  // TODO !! ArrayBuffer causing performance issues ?
  private val edgeContainer = mutable.ArrayBuffer[List[Int]]()
  private val vertexNoEdgesEdgeId = -1

  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = {

    val mayBeExistingEdgeIndex: Option[EdgeIndex] = vertexContainer.find(vertex)

    mayBeExistingEdgeIndex.fold {
      vertexContainer.add(vertex, edgeContainer.size)
      // insert into edgeContainer
      edgeContainer += List(edge.id)
    } { edgeIndex =>
      val existingEdges = edgeContainer(edgeIndex)
      val newEdges = (edge.id :: existingEdges)
      edgeContainer(edgeIndex) = newEdges
      edgeContainer
    }
  }

  override def addVertex(vertex: CSRNode): Unit =
    // does not check for existence of value - so can add duplciates...
    vertexContainer.add(vertex, vertexNoEdgesEdgeId)

  override def allVertices: List[CSRNode] = vertexContainer.getLeaves()

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
    val foundEdge = vertexContainer.find(v).filterNot(eI => eI == vertexNoEdgesEdgeId)
    val ll = foundEdge.map { edgeIndex =>
      edgeContainer(edgeIndex)
    }.getOrElse(Nil)

    ll.sorted
  }

  override def edgesForVertexId(vid: EdgeIndex): List[Int] = {
    val nodeToFind = CSRNode(vid, vid)
    edgesForVertex(nodeToFind)
  }

  override def nonEmptyVertexList: List[CSRNode] = {
    val allKV = vertexContainer.getAllKeyValues()
    val nonEmptyKV = allKV.filterNot {
      case (k1, v1) =>
        v1 == vertexNoEdgesEdgeId
    }
    nonEmptyKV.map(_._1)
  }

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
