package hvd.edu.graph

import hvd.edu.graph.al.ALNode
import hvd.edu.graph.csr.CSRNode

trait NodeMaker[N <: Node] {
  def make(id: Int): N
}

object CSRNodeMaker extends NodeMaker[CSRNode] {
  override def make(id: Int): CSRNode = CSRNode(id)
}

object DefaultALNodeMaker extends NodeMaker[ALNode] {
  override def make(id: Int) = ALNode(id)
}
