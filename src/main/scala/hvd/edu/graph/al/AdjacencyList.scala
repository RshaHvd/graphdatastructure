package hvd.edu.graph.al

import hvd.edu.graph.{ContainerMaker, Graph, GraphContainer, Node, NodeMaker}

import scala.collection.mutable

/**
 * [ [0, 2], [1, 3], [2, 3], [2, 4], [3, 5], [4, 5] ]
 * [0 -> 2]
 * [1 -> 3]
 * [2 -> (3,4)]
 * [3 -> 5]
 * [4 -> 5]
 */

trait AdjacencyListNode extends Node{
//
// // def v : Int
//
////  def addEdge[ALN <: AdjacencyListNode](e: ALN) : Unit
////
////  def outgoingEdges[ALN <: AdjacencyListNode] : Set[ALN]
}

//object EmptyAdjacencyNode extends ALNodeWithSetBasedEdges(-1, -1)


case class ALNodeWithSetBasedEdges(override val id: Int, override val value: Int) extends AdjacencyListNode {

  private var outgoingEdgeSet = mutable.Set[ALNodeWithSetBasedEdges]()

  def outgoingEdges() = outgoingEdgeSet.toSet

  def addEdge(e: ALNodeWithSetBasedEdges) = outgoingEdgeSet.add(e)
}
