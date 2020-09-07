package hvd.edu.graph

import hvd.edu.graph.al.{ALNodeWithSetBasedEdges, ArrayBasedALContainer}
import hvd.edu.graph.csr.{ArrayBasedCSRContainer, CSRNode, HashMapBasedCSRContainer}

trait ContainerMaker[N<: Node, C <: GraphContainer[N]] {
  def make(numVertex: Int, numEdges: Int): C
}

object ContainerMaker {

  implicit object ArrayBasedCSRContainerFactory extends ContainerMaker[CSRNode, ArrayBasedCSRContainer] {
    override def make(numVertex: Int, numEdges: Int) = {
      new ArrayBasedCSRContainer(numVertex, numEdges)
    }
  }

   implicit object HashMapBasedCSRContainerFactory extends ContainerMaker[CSRNode, HashMapBasedCSRContainer] {
    override def make(numVertex: Int, numEdges: Int) = {
      new HashMapBasedCSRContainer(numVertex, numEdges)
    }
  }

  implicit object ArrayBasedALContainerFactory extends ContainerMaker[ALNodeWithSetBasedEdges, ArrayBasedALContainer] {
    override def make(numVertex: Int, numEdges: Int) = {
      new ArrayBasedALContainer(numVertex)
    }
  }

  def apply[N<: Node, C <: GraphContainer[N]](numVertex: Int, numEdges: Int)(implicit ev: ContainerMaker[N, C]): C = {
    ev.make(numVertex, numEdges)
  }

}

