package hvd.edu.utils

import hvd.edu.graph.Node
import hvd.edu.graph.al.{
  ArrayALContainer,
  DefaultALNode,
  HashMapALContainer,
  SetBasedALNode
}
import hvd.edu.graph.csr.{ ArrayCSRContainer, CSRNode, HashMapCSRContainer }

object TestGraphUtil {

  def makeAllCSRGraphContainer(inputStr: String, delimiter1: String, delimiter2: String) = {
    List(
      GraphBuilder.buildFromString[CSRNode, ArrayCSRContainer](inputStr, delimiter1, delimiter2),
      GraphBuilder.buildFromString[CSRNode, HashMapCSRContainer](inputStr, delimiter1, delimiter2)
    )
  }

}
