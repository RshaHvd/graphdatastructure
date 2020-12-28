package hvd.edu.utils

import java.util

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

  def bfsIterative[N <: Node, GC <: GraphContainer[N]](fromNode: N, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }
    val retLB = interativeBFS(foundVertex, graph)
    retLB.toList
  }

  def dfsIterative[N <: Node, GC <: GraphContainer[N]](fromNode: N, graph: Graph[N, GC]): List[N] = {

    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
      (thisNode.id == fromNode.id)
    }
    val retLB = interativeDFS(ListBuffer(foundVertex: _*), graph)
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
                                                              accum: ListBuffer[N], alreadyVisited: mutable.Set[Long]): ListBuffer[N] = {
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
  }

  private def internalBFS[N <: Node, GC <: GraphContainer[N]](fromNodes: List[N], graph: Graph[N, GC],
                                                              accum: ListBuffer[N], alreadyVisited: mutable.Set[Long]): List[N] = {
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

  private def interativeDFS[N <: Node, GC <: GraphContainer[N]](fromVertices: mutable.ListBuffer[N], graph: Graph[N, GC]): ListBuffer[N] = {
    val accum = mutable.ListBuffer[N]()
    val alreadyVisited = mutable.Set[Long]()
    val visitNextBuffer = mutable.Stack(fromVertices: _*)
    while (visitNextBuffer.nonEmpty) {
      val n = visitNextBuffer.pop()
      if (!alreadyVisited.contains(n.id)) {
        val edgesFromN: List[N] = graph.edgesForVertexId(n.id)
        accum += n
        alreadyVisited += n.id
        edgesFromN.foreach { toVisit =>
          if (!alreadyVisited.contains(toVisit.id))
            visitNextBuffer.push(toVisit)
        }
      }
    }
    accum
  }

  private def interativeBFS[N <: Node, GC <: GraphContainer[N]](fromNodes: List[N], graph: Graph[N, GC]): ListBuffer[N] = {
    val accum = mutable.ListBuffer[N]()
    val alreadyVisited = mutable.Set[Long]()
    val visitNextBufferQueue = mutable.Queue[N](fromNodes: _*)
    while (visitNextBufferQueue.nonEmpty) {
      val thisNode = visitNextBufferQueue.dequeue()
      //visitNextBufferQueue.foreach { thisNode =>
      if (!alreadyVisited.contains(thisNode.id)) {
        accum += thisNode
        alreadyVisited += thisNode.id // ok, as this is a set.
      }
      val edgesFromN: List[N] = graph.edgesForVertexId(thisNode.id)
      edgesFromN.foreach { toVisit =>
        if (!alreadyVisited.contains(toVisit.id))
          visitNextBufferQueue.enqueue(toVisit)
      }
    }
    //internalBFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
    accum
  }
}
