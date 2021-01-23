package hvd.edu.graph.al

import hvd.edu.graph.Node

final case class ALNode(override val id: Int, override val value: Int) extends Node

object ALNodeUtils {

  implicit val defaultALNodeOrdering = new Ordering[ALNode] {
    override def compare(x: ALNode, y: ALNode): Int = {
      x.id.compareTo(y.id)
    }
  }

}
