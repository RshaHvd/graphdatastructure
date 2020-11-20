package hvd.edu.graph.csr

import hvd.edu.graph.{ ContainerMaker, Graph, GraphContainer, Node }

/**
 * [ [0, 2], [1, 3], [2, 4], [3, 5], [4, 5], [2, 3]]
 * [0 -> 2]
 * [1 -> 3]
 * [2 -> (3,4)]
 * [3 -> 5]
 * [4 -> 5]
 */

trait CSRNodeDef extends Node {
  def firstEdgeIndex: Long
}

case class CSRNode(override val id: Long, override val value: Long,
                   firstEdgeIndex: Long = -1) extends CSRNodeDef {

  private var nextEdgeId = -1
  def setNextId(nid: Int): Unit = {
    nextEdgeId = nid
  }
  def idAsInt = id.toInt
  def firstEdgeIndexAsInt = firstEdgeIndex.toInt
  def getNextId() = nextEdgeId
}

object CSRNodeUtils {

  implicit val defaultCSRNodeOrdering = new Ordering[CSRNode] {
    override def compare(x: CSRNode, y: CSRNode): Int =
      x.id.compare(y.id)
  }

}
