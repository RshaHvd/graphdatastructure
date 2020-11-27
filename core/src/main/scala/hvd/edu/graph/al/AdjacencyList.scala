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

case class SetBasedALNode(override val id: Long, override val value: Long) extends Node {

  private var outgoingEdgeSet = mutable.Set[SetBasedALNode]()

  def outgoingEdges() = outgoingEdgeSet.toSet

  def addEdge(e: SetBasedALNode) = outgoingEdgeSet.add(e)
}

case class DefaultALNode(override val id: Long, override val value: Long) extends Node

case class SpecializedDefaultALNode[@specialized(Long) A](idS: A, valueS: A)

object ALNodeUtils {

  implicit def converter(d: DefaultALNode) = {
    SpecializedDefaultALNode(d.id, d.value)
  }

  implicit val defaultALNodeOrdering = new Ordering[DefaultALNode] {
    override def compare(x: DefaultALNode, y: DefaultALNode): Int = {
      val xs = converter(x)
      val ys = converter(y)
      xs.idS.compareTo(ys.idS)
    }
  }

}
