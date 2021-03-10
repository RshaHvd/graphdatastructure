package hvd.edu.utils

import hvd.edu.graph.CSRNodeMaker
import hvd.edu.graph.csr.{ ArrayCSRContainer, HashMapCSRContainer }

object TestGraphUtil {

  def makeAllCSRGraphContainer(inputStr: String, delimiter1: String, delimiter2: String) = {
    val arrayEdges: Array[String] = inputStr.split(" ")
    List(
      StringGraphEngine.buildFromString(inputStr, delimiter1, delimiter2,
        ArrayCSRContainer(arrayEdges.length, arrayEdges.length),
        CSRNodeMaker),
      StringGraphEngine.buildFromString(inputStr, delimiter1, delimiter2,
        HashMapCSRContainer(arrayEdges.length, arrayEdges.length),
        CSRNodeMaker))
  }

}
