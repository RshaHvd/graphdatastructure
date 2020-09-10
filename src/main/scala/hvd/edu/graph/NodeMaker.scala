package hvd.edu.graph

import hvd.edu.graph.al.{DefaultALNode, SetBasedALNode}

trait NodeMaker[N<: Node] {
  def make(id: Int, value: Int): N
}

object NodeMaker{

  implicit object CSRNodeMaker extends NodeMaker[CSRNode] {
    override def make(id: Int, value: Int): CSRNode = CSRNode(id, value)
  }

  implicit object ALNodeWithSetBasedEdgesMaker extends NodeMaker[SetBasedALNode] {
    override def make(id: Int, value: Int) = SetBasedALNode(id, value)
  }

  implicit object DefaultALNodeMaker extends NodeMaker[DefaultALNode] {
    override def make(id: Int, value: Int) = DefaultALNode(id, value)
  }

  def apply[N <: Node](id: Int, value: Int)(implicit nodeMaker : NodeMaker[N]): N = {
      nodeMaker.make(id, value)
  }

}


