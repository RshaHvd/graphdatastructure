package hvd.edu.utils

import hvd.edu.graph.{Graph, GraphContainer, Node}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GraphAlgos {

  def bfs[N <: Node, GC <: GraphContainer[N]](fromNode: N, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }

    internalBFS(foundVertex, graph, mutable.ListBuffer[N](), mutable.Set[Int]())

  }

  def dfs[N <: Node, GC <: GraphContainer[N]](fromNode: N, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }

    internalDFS(foundVertex, graph, mutable.ListBuffer[N](), mutable.Set[Int]())
  }

  def dfsFromNodeId[N <: Node, GC <: GraphContainer[N]](nodeId: Int, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == nodeId)
    }

    internalDFS(foundVertex, graph, mutable.ListBuffer[N](), mutable.Set[Int]())
  }

  private def internalDFS[N <: Node, GC <: GraphContainer[N]](
    fromVertices: List[N],
    graph: Graph[N, GC],
    accum: ListBuffer[N],
    alreadyVisited: mutable.Set[Int]
  ): List[N] =
    if (fromVertices.isEmpty)
      accum.toList
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
          internalDFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
        } else
          Nil
      }
      accum.toList
    }

  private def internalBFS[N <: Node, GC <: GraphContainer[N]](
    fromNodes: List[N],
    graph: Graph[N, GC],
    accum: ListBuffer[N],
    alreadyVisited: mutable.Set[Int]
  ): List[N] =
    if (fromNodes.isEmpty)
      accum.toList
    else {
      // add all these nodes first
      fromNodes.foreach { thisNode =>
        if (!alreadyVisited.contains(thisNode.id))
          accum += thisNode
        alreadyVisited += thisNode.id
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
