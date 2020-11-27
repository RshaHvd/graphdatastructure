package hvd.edu.utils

import hvd.edu.graph.{ Graph, GraphContainer, Node }

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GraphAlgos {

  def bfs[N <: Node, GC <: GraphContainer[N]](fromNode: N, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }
    internalBFS(foundVertex, graph, mutable.ListBuffer[N](), mutable.Set[Long]())
  }

  def dfs[N <: Node, GC <: GraphContainer[N]](fromNode: N, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }
    val retLB = internalDFS(ListBuffer(foundVertex: _*), graph, mutable.ListBuffer[N](), mutable.Set[Long]())
    retLB.toList
  }

  def dfsFromNodeId[N <: Node, GC <: GraphContainer[N]](nodeId: Int, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == nodeId)
    }
    val retLB = internalDFS(ListBuffer(foundVertex: _*), graph, mutable.ListBuffer[N](), mutable.Set[Long]())
    retLB.toList
  }

  private def internalDFS[N <: Node, GC <: GraphContainer[N]](fromVertices: mutable.ListBuffer[N], graph: Graph[N, GC],
                                                              accum: ListBuffer[N], alreadyVisited: mutable.Set[Long]): ListBuffer[N] =
    if (fromVertices.isEmpty)
      accum
    else {
      fromVertices.foreach { n =>
        if (!alreadyVisited.contains(n.id)) {
          val edgesFromN: List[N] = graph.edgesForVertexId(n.id)
          accum += n
          alreadyVisited += n.id
          val visitNextBuffer = mutable.ListBuffer[N]()
          edgesFromN.foreach { toVisit =>
            if (!alreadyVisited.contains(toVisit.id))
              visitNextBuffer += toVisit
          }
          internalDFS(visitNextBuffer, graph, accum, alreadyVisited)
        }
      }
      internalDFS(ListBuffer.empty, graph, accum, alreadyVisited)
    }

  private def internalBFS[N <: Node, GC <: GraphContainer[N]](fromNodes: List[N], graph: Graph[N, GC],
                                                              accum: ListBuffer[N], alreadyVisited: mutable.Set[Long]): List[N] =
    if (fromNodes.isEmpty)
      accum.toList
    else {
      // add all these nodes first
      fromNodes.foreach { thisNode =>
        if (!alreadyVisited.contains(thisNode.id))
          accum += thisNode
        alreadyVisited += thisNode.id // ok, as this is a set.
      }
      val visitNextBuffer = mutable.ListBuffer[N]()
      fromNodes.foreach { n =>
        val edgesFromN: List[N] = graph.edgesForVertexId(n.id)
        edgesFromN.foreach { toVisit =>
          if (!alreadyVisited.contains(toVisit.id))
            visitNextBuffer += toVisit
        }
      }
      internalBFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
    }

}
