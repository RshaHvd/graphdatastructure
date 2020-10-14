package hvd.edu.graph

import hvd.edu.graph.al.{ArrayALContainer, BplusTreeALContainer, DefaultALNode, HashMapALContainer, SetBasedALNode}
import hvd.edu.graph.csr.{ArrayCSRContainer, CSRNode, HashMapCSRContainer}

trait ContainerMaker[N<: Node, C <: GraphContainer[N]] {
  def make(numVertex: Int, numEdges: Int)(implicit fanout: Option[Int]): C
}

object ContainerMaker {

  implicit object ArrayBasedCSRContainerFactory extends ContainerMaker[CSRNode, ArrayCSRContainer] {
    override def make(numVertex: Int, numEdges: Int)(implicit fanout: Option[Int]) = {
      new ArrayCSRContainer(numVertex, numEdges)
    }
  }

   implicit object HashMapBasedCSRContainerFactory extends ContainerMaker[CSRNode, HashMapCSRContainer] {
    override def make(numVertex: Int, numEdges: Int)(implicit fanout: Option[Int]) = {
      new HashMapCSRContainer(numVertex, numEdges)
    }
  }

  implicit object ArrayBasedALContainerFactory extends ContainerMaker[SetBasedALNode, ArrayALContainer] {
    override def make(numVertex: Int, numEdges: Int)(implicit fanout: Option[Int]) = {
      new ArrayALContainer(numVertex)
    }
  }

  implicit object HashMapBasedALContainerFactory extends ContainerMaker[DefaultALNode, HashMapALContainer] {
    override def make(numVertex: Int, numEdges: Int)(implicit fanout: Option[Int]) = {
      new HashMapALContainer(numVertex)
    }
  }

  implicit object BplusTreeBasedALContainerFactory extends ContainerMaker[DefaultALNode, BplusTreeALContainer]{
    override def make(numVertex: Int, numEdges: Int)(implicit fanout: Option[Int]): BplusTreeALContainer = {
      new BplusTreeALContainer(numVertex, fanout)
    }
  }

  def apply[N<: Node, C <: GraphContainer[N]](numVertex: Int, numEdges: Int)(implicit ev: ContainerMaker[N, C],
                                                                             fanout: Option[Int]): C = {
    ev.make(numVertex, numEdges)
  }

}

