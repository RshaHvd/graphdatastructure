package hvd.edu.utils

import hvd.edu.graph.Node
import hvd.edu.graph.al.{ArrayALContainer, DefaultALNode, HashMapALContainer, SetBasedALNode}
import hvd.edu.graph.csr.{ArrayCSRContainer, CSRNode, HashMapCSRContainer}

object TestGraphUtil {

  def makeAllCSRGraphContainer(inputStr: String, delimiter1: String, delimiter2: String) = {
    List(
      GraphBuilder.buildFromString[CSRNode, ArrayCSRContainer](inputStr, delimiter1, delimiter2),
      GraphBuilder.buildFromString[CSRNode, HashMapCSRContainer](inputStr, delimiter1, delimiter2)
    )
  }

  def makeAllALGraphContainer[N<:Node](inputStr: String, delimiter1: String, delimiter2: String) = {
    val g1 = GraphBuilder.buildFromString[SetBasedALNode, ArrayALContainer](inputStr, delimiter1, delimiter2)
    val g2 = GraphBuilder.buildFromString[DefaultALNode, HashMapALContainer](inputStr, delimiter1, delimiter2)

    List(g1,g2)
  }

}
