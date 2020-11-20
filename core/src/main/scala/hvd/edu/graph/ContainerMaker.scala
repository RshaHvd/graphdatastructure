package hvd.edu.graph

import hvd.edu.graph.al.{ ArrayALContainer, BplusTreeALContainer, DefaultALNode, HashMapALContainer, SetBasedALNode }
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }

trait ContainerMaker[N <: Node, C <: GraphContainer[N]] {
  def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]): C
}

object ContainerMaker {

  implicit object ArrayBasedCSRContainerFactory
    extends ContainerMaker[CSRNode, ArrayCSRContainer] {
    override def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]) =
      ArrayCSRContainer(numVertex, numEdges)
  }

  implicit object HashMapBasedCSRContainerFactory
    extends ContainerMaker[CSRNode, HashMapCSRContainer] {
    override def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]) =
      HashMapCSRContainer(numVertex, numEdges)
  }

  implicit object BplusTreeBasedCSRContainerFactory
    extends ContainerMaker[CSRNode, BplusTreeCSRContainer] {
    override def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]): BplusTreeCSRContainer =
      BplusTreeCSRContainer(numVertex, fanout)
  }

  implicit object ArrayBasedALContainerFactory
    extends ContainerMaker[SetBasedALNode, ArrayALContainer] {
    override def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]) =
      ArrayALContainer(numVertex)
  }

  implicit object HashMapBasedALContainerFactory
    extends ContainerMaker[DefaultALNode, HashMapALContainer] {
    override def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]) =
      HashMapALContainer(numVertex)
  }

  implicit object BplusTreeBasedALContainerFactory
    extends ContainerMaker[DefaultALNode, BplusTreeALContainer] {
    override def make(numVertex: Long, numEdges: Long)(implicit fanout: Option[Int]): BplusTreeALContainer =
      BplusTreeALContainer(numVertex, fanout)
  }

  def apply[N <: Node, C <: GraphContainer[N]](numVertex: Long, numEdges: Long)(
    implicit
    ev: ContainerMaker[N, C], fanout: Option[Int]): C = {
    ev.make(numVertex, numEdges)
  }

}
