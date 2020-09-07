package hvd.edu.graph

import hvd.edu.graph.al.ALNodeWithSetBasedEdges
import hvd.edu.graph.csr.CSRNode

trait NodeMaker[N<: Node] {
  def make(id: Int, value: Int): N
}

object NodeMaker{

  implicit object CSRNodeMaker extends NodeMaker[CSRNode] {
    override def make(id: Int, value: Int): CSRNode = CSRNode(id, value)
  }

  implicit object ALNodeWithSetBasedEdgesMaker extends NodeMaker[ALNodeWithSetBasedEdges] {
    override def make(id: Int, value: Int) = ALNodeWithSetBasedEdges(id, value)
  }

  def apply[N <: Node](id: Int, value: Int)(implicit nodeMaker : NodeMaker[N]): N = {
      nodeMaker.make(id, value)
  }

}


