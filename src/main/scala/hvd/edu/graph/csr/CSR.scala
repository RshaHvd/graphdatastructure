package hvd.edu.graph.csr

import hvd.edu.graph.{ContainerMaker, Graph, GraphContainer, Node}

/**
 * [ [0, 2], [1, 3], [2, 4], [3, 5], [4, 5], [2, 3]]
 * [0 -> 2]
 * [1 -> 3]
 * [2 -> (3,4)]
 * [3 -> 5]
 * [4 -> 5]
 */

trait CSRNodeDef extends Node {
  def firstEdgeIndex: Int
}

case class CSRNode(override val id: Int, override val value: Int, firstEdgeIndex: Int = -1)
  extends CSRNodeDef

