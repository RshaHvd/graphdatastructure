package hvd.edu.utils

import hvd.edu.graph.Node
import hvd.edu.graph.al.{ArrayBasedALContainer, DefaultALNode, HashMapBasedALContainer, SetBasedALNode}
import hvd.edu.graph.csr.{ArrayBasedCSRContainer, CSRNode, HashMapBasedCSRContainer}

object TestGraphUtil {

  def makeAllCSRGraphContainer(inputStr: String, delimiter1: String, delimiter2: String) = {
    List(
      GraphBuilder.buildFromString[CSRNode, ArrayBasedCSRContainer](inputStr, delimiter1, delimiter2),
      GraphBuilder.buildFromString[CSRNode, HashMapBasedCSRContainer](inputStr, delimiter1, delimiter2)
    )
  }

  def makeAllALGraphContainer[N<:Node](inputStr: String, delimiter1: String, delimiter2: String) = {
    val g1 = GraphBuilder.buildFromString[SetBasedALNode, ArrayBasedALContainer](inputStr, delimiter1, delimiter2)
    val g2 = GraphBuilder.buildFromString[DefaultALNode, HashMapBasedALContainer](inputStr, delimiter1, delimiter2)

    List(g1,g2)
  }

}
