package hvd.edu.graph.csr

import hvd.edu.graph.GraphContainer

import scala.collection.mutable

class BPlusTreeBasedCSRContainer(val numVertex: Int, val numEdges: Int) extends GraphContainer[CSRNode] {

//  private val vertexContainer = mutable.Map[CSRNode, String]()
//  private val edgeContainer = mutable.Map[String, List[CSRNode]]()
//  private val vertexNoEdgesEdgeId = "-1"

  override def addEdge(vertex: CSRNode, edge: CSRNode): Unit = ???

  override def addVertex(vertex: CSRNode): Unit = ???

  override def allVertices: List[CSRNode] = ???

  override def edgeLength: Int = ???

  override def vertexLength: Int = ???

  override def edgesForVertex(v: CSRNode): List[CSRNode] = ???

  override def edgesForVertexId(vid: Int): List[CSRNode] = ???

  override def nonEmptyVertexList: List[CSRNode] = ???

  override def print(mayBeNumberOfVertex: Option[Int]): Unit = ???
}
