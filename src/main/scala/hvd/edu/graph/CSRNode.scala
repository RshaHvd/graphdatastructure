package hvd.edu.graph

import hvd.edu.graph.csr.CSRNodeDef

case class CSRNode(override val id: Int, override val value: Int, firstEdgeIndex: Int = -1)
  extends CSRNodeDef
