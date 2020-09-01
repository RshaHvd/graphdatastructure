package hvd.edu.graph.csr

trait CSRContainers {

  def add(vertex: CSRNode, edge: CSRNode): Unit

  def addEdge(vertex: CSRNode, edge: CSRNode): Unit

  def allVertices: List[CSRNode]

  def vertex_?(vertex: CSRNode): CSRNode

  def edgeLength: Int

  def vertexLength: Int

  def edgesForVertex(v: CSRNode): List[CSRNode]

  def edgesForVertexId(vid: Int): List[CSRNode]

  def nonEmptyVertexList : List[CSRNode]

  def print(mayBeNumberOfVertex: Option[Int])

}

trait CSRContainerMaker[C <: CSRContainers] {
  def make(numVertex: Int, numEdges: Int): C
}

object CSRContainerMaker {

  implicit object ArrayBasedCSRContainerFactory extends CSRContainerMaker[ArrayBasedCSRContainer] {
    override def make(numVertex: Int, numEdges: Int) = {
      new ArrayBasedCSRContainer(numVertex, numEdges)
    }
  }

   implicit object HashMapBasedContainerFactory extends CSRContainerMaker[HashMapBasedCSRContainer] {
    override def make(numVertex: Int, numEdges: Int) = {
      new HashMapBasedCSRContainer(numVertex, numEdges)
    }
  }

  def apply[C <: CSRContainers](numVertex: Int, numEdges: Int)(implicit ev: CSRContainerMaker[C]): C = {
    ev.make(numVertex, numEdges)
  }

}

