package hvd.edu.utils

import hvd.edu.graph.{ Graph, Node }

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GraphAlgos {

  def bfs[N <: Node](fromNode: N, graph: Graph[N]): List[Int] = {

    //    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
    //      (thisNode.id == fromNode.id)
    //    }
    internalBFS(List(fromNode.id), graph, mutable.ListBuffer[Int](), mutable.Set[Int]())
  }

  def dfs[N <: Node](fromNode: N, graph: Graph[N]): List[Int] = {

    //    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
    //      (thisNode.id == fromNode.id)
    //    }
    val retLB = internalDFS(ListBuffer(fromNode.id), graph, mutable.ListBuffer[Int](), mutable.Set[Int]())
    retLB.toList
  }

  def bfsIterative[N <: Node](fromNode: N, graph: Graph[N]): List[Int] = {

    //    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
    //      (thisNode.id == fromNode.id)
    //    }
    val retLB = interativeBFS(List(fromNode.id), graph)
    retLB.toList
  }

  def dfsIterative[N <: Node](fromNode: N, graph: Graph[N]): List[Int] = {
    //
    //    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
    //      (thisNode.id == fromNode.id)
    //    }
    val retLB = interativeDFS(ListBuffer(fromNode.id), graph)
    retLB.toList
  }

  def dfsFromNodeId[N <: Node](nodeId: Int, graph: Graph[N]): List[Int] = {

    //    val foundVertex: List[N] = graph.vertexList.filter { thisNode =>
    //      (thisNode.id == nodeId)
    //    }
    val retLB = internalDFS(ListBuffer(nodeId), graph, mutable.ListBuffer[Int](), mutable.Set[Int]())
    retLB.toList
  }

  private def internalDFS[N <: Node](fromVertices: mutable.ListBuffer[Int], graph: Graph[N],
                                     accum: ListBuffer[Int], alreadyVisited: mutable.Set[Int]): ListBuffer[Int] = {
    if (fromVertices.isEmpty)
      accum
    else {
      fromVertices.foreach { n =>
        if (!alreadyVisited.contains(n)) {
          val edgesFromN: List[Int] = graph.edgesForVertexId(n)
          accum += n
          alreadyVisited += n
          val visitNextBuffer = mutable.ListBuffer[Int]()
          edgesFromN.foreach { toVisit =>
            if (!alreadyVisited.contains(toVisit))
              visitNextBuffer += toVisit
          }
          internalDFS(visitNextBuffer, graph, accum, alreadyVisited)
        }
      }
      internalDFS(ListBuffer.empty, graph, accum, alreadyVisited)
    }
  }

  private def internalBFS[N <: Node](fromNodes: List[Int], graph: Graph[N],
                                     accum: ListBuffer[Int], alreadyVisited: mutable.Set[Int]): List[Int] = {
    if (fromNodes.isEmpty)
      accum.toList
    else {
      // add all these nodes first
      fromNodes.foreach { thisNode =>
        if (!alreadyVisited.contains(thisNode))
          accum += thisNode
        alreadyVisited += thisNode // ok, as this is a set.
      }
      val visitNextBuffer = mutable.ListBuffer[Int]()
      fromNodes.foreach { n =>
        val edgesFromN: List[Int] = graph.edgesForVertexId(n)
        edgesFromN.foreach { toVisit =>
          if (!alreadyVisited.contains(toVisit))
            visitNextBuffer += toVisit
        }
      }
      internalBFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
    }
  }

  private def interativeDFS[N <: Node](fromVertices: mutable.ListBuffer[Int], graph: Graph[N]): ListBuffer[Int] = {
    val accum = mutable.ListBuffer[Int]()
    val alreadyVisited = mutable.Set[Int]()
    val visitNextBuffer = mutable.Stack(fromVertices: _*)
    while (visitNextBuffer.nonEmpty) {
      val n = visitNextBuffer.pop()
      if (!alreadyVisited.contains(n)) {
        val edgesFromN: List[Int] = graph.edgesForVertexId(n)
        accum += n
        alreadyVisited += n
        edgesFromN.foreach { toVisit =>
          if (!alreadyVisited.contains(toVisit))
            visitNextBuffer.push(toVisit)
        }
      }
    }
    accum
  }

  private def interativeBFS[N <: Node](fromNodes: List[Int], graph: Graph[N]): ListBuffer[Int] = {
    val accum = mutable.ListBuffer[Int]()
    val alreadyVisited = mutable.Set[Int]()
    val visitNextBufferQueue = mutable.Queue[Int](fromNodes: _*)
    while (visitNextBufferQueue.nonEmpty) {
      val thisNode = visitNextBufferQueue.dequeue()
      //visitNextBufferQueue.foreach { thisNode =>
      if (!alreadyVisited.contains(thisNode)) {
        accum += thisNode
        alreadyVisited += thisNode // ok, as this is a set.
      }
      val edgesFromN: List[Int] = graph.edgesForVertexId(thisNode)
      edgesFromN.foreach { toVisit =>
        if (!alreadyVisited.contains(toVisit))
          visitNextBufferQueue.enqueue(toVisit)
      }
    }
    //internalBFS(visitNextBuffer.toList, graph, accum, alreadyVisited)
    accum
  }
}
