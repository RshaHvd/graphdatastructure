package hvd.edu.graph.al

import hvd.edu.graph.Node

import scala.collection.mutable

/**
 * [ [0, 2], [1, 3], [2, 3], [2, 4], [3, 5], [4, 5] ]
 * [0 -> 2]
 * [1 -> 3]
 * [2 -> (3,4)]
 * [3 -> 5]
 * [4 -> 5]
 */

//final case class ALNode(override val id: Int, override val value: Int) extends Node {
//
//  private val outgoingEdgeSet = mutable.Set[ALNode]()
//
//  def outgoingEdges() = outgoingEdgeSet.toSet
//
//  def addEdge(e: ALNode) = outgoingEdgeSet.add(e)
//}

//final case class ALNode(override val id: Int, override val value: Int) extends Node {
//
//  private val outgoingEdgeSet = mutable.ListBuffer[Int]()
//
//  def outgoingEdges() = outgoingEdgeSet
//
//  def addEdge(e: ALNode) = outgoingEdgeSet.append(e.id)
//}

final case class ALNode(override val id: Int, override val value: Int) extends Node

object ALNodeUtils {

  implicit val defaultALNodeOrdering = new Ordering[ALNode] {
    override def compare(x: ALNode, y: ALNode): Int = {
      x.id.compareTo(y.id)
    }
  }

}
