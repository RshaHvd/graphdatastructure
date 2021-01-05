package hvd.edu.graph

import hvd.edu.graph.al.{ DefaultALNode, SetBasedALNode }
import hvd.edu.graph.csr.CSRNode

trait NodeMaker[N <: Node] {
  def make(id: Int, value: Int): N
}

object CSRNodeMaker extends NodeMaker[CSRNode] {
  override def make(id: Int, value: Int): CSRNode = CSRNode(id, value)
}

object SetBasedALNodeMaker extends NodeMaker[SetBasedALNode] {
  override def make(id: Int, value: Int) = SetBasedALNode(id, value)
}

object DefaultALNodeMaker extends NodeMaker[DefaultALNode] {
  override def make(id: Int, value: Int) = DefaultALNode(id, value)
}
