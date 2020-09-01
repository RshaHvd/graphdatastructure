package hvd.edu.utils

import hvd.edu.{Graph, Node}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GraphAlgos {

  def bfs[N <: Node, G <: Graph[N]](fromNode: N, graph: G): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }

    internalBFS(foundVertex, graph, mutable.ListBuffer[N](), mutable.Set[Int]())

  }

  def dfs[N <: Node, G <: Graph[N]](fromNode: N, graph: G): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }

    internalDFS(foundVertex, graph, mutable.ListBuffer[N](), mutable.Set[Int]())
  }

  private def internalDFS[N <: Node, G <: Graph[N]](
    fromVertices: List[N],
    graph: G,
    accum: ListBuffer[N],
    alreadyVisited: mutable.Set[Int]
  ): List[N] = {
    if (fromVertices.isEmpty) {
      accum.toList
    } else {
      fromVertices.foreach { n =>
        if (!alreadyVisited.contains(n.id)) {
          val edgesFromN: List[N] = graph.edgesForVertexId(n.id)
          // val verticesForThisEdgeNodes = graph.verticesByNodeIds(edgesFromN)
          accum += n
          alreadyVisited += n.id
          val visitNextBuffer = mutable.ListBuffer[N]()
          edgesFromN.foreach { toVisit =>
            if (!alreadyVisited.contains(toVisit.id)) {
              visitNextBuffer += toVisit
            }
          }
          internalDFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
        } else {
          Nil
        }
      }
      accum.toList
    }

  }

  private def internalBFS[N <: Node, G <: Graph[N]](
    fromNodes: List[N],
    graph: G,
    accum: ListBuffer[N],
    alreadyVisited: mutable.Set[Int]
  ): List[N] = {
    if (fromNodes.isEmpty) {
      accum.toList
    } else {
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
          if (!alreadyVisited.contains(toVisit.id)) {
            visitNextBuffer += toVisit
          }
        }
      }
      internalBFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
    }

  }

}
