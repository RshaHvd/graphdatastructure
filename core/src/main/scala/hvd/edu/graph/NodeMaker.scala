package hvd.edu.graph

import hvd.edu.graph.al.{ DefaultALNode, SetBasedALNode }
import hvd.edu.graph.csr.CSRNode

trait NodeMaker[N <: Node] {
  def make(id: Long, value: Long): N
}

object NodeMaker {

  implicit object CSRNodeMaker extends NodeMaker[CSRNode] {
    override def make(id: Long, value: Long): CSRNode = CSRNode(id, value)
  }

  implicit object ALNodeWithSetBasedEdgesMaker extends NodeMaker[SetBasedALNode] {
    override def make(id: Long, value: Long) = SetBasedALNode(id, value)
  }

  implicit object DefaultALNodeMaker extends NodeMaker[DefaultALNode] {
    override def make(id: Long, value: Long) = DefaultALNode(id, value)
  }

  def apply[N <: Node](id: Long, value: Long)(implicit nodeMaker: NodeMaker[N]): N =
    nodeMaker.make(id, value)

}
