package hvd.edu.benchmark.workload

import enumeratum._
import hvd.edu.graph.al._
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }
import hvd.edu.utils.GraphInputFileReader

sealed abstract class GraphType(override val entryName: String) extends EnumEntry {
  def displayName = entryName
}

object GraphTypes extends Enum[GraphType] {

  val values = findValues

  case object ALArrayType extends GraphType("ALA") {
    override val displayName: String = "AL-Array"
  }

  case object ALMapType extends GraphType("ALM") {
    override val displayName: String = "AL-Map"

  }

  case object ALTreeType extends GraphType("ALT") {
    override val displayName: String = "AL-Tree"
  }

  case object CSRArrayType extends GraphType("CSRA") {
    override val displayName: String = "CSR-Array"

  }

  case object CSRMapType extends GraphType("CSRM") {
    override val displayName: String = "CSR-Map"

  }

  case object CSRTreeType extends GraphType("CSRT") {
    override val displayName: String = "CSR-Tree"

  }
}

/**
 * Utility to link GraphType Enums in benchmarks to Graphs and Nodes definitions
 */
object GraphTypeUtils {

  def readGraphFromFile(gt: GraphType, filePath: String, delimiter: String) = {
    gt match {
      case GraphTypes.ALArrayType  => GraphInputFileReader.readFile2[SetBasedALNode, ArrayALContainer](filePath = filePath, delimiter = delimiter)
      case GraphTypes.ALMapType    => GraphInputFileReader.readFile2[DefaultALNode, HashMapALContainer](filePath = filePath, delimiter = delimiter)
      case GraphTypes.ALTreeType   => GraphInputFileReader.readFile2[DefaultALNode, BplusTreeALContainer](filePath = filePath, delimiter = delimiter)
      case GraphTypes.CSRArrayType => GraphInputFileReader.readFile2[CSRNode, ArrayCSRContainer](filePath = filePath, delimiter = delimiter)
      case GraphTypes.CSRMapType   => GraphInputFileReader.readFile2[CSRNode, HashMapCSRContainer](filePath = filePath, delimiter = delimiter)
      case GraphTypes.CSRTreeType  => GraphInputFileReader.readFile2[CSRNode, BplusTreeCSRContainer](filePath = filePath, delimiter = delimiter)
    }

  }

  def createNode(gt: GraphType, id: Long) = {
    gt match {
      case GraphTypes.ALArrayType  => SetBasedALNode(id, id)
      case GraphTypes.ALMapType    => DefaultALNode(id, id)
      case GraphTypes.ALTreeType   => DefaultALNode(id, id)
      case GraphTypes.CSRArrayType => CSRNode(id, id)
      case GraphTypes.CSRMapType   => CSRNode(id, id)
      case GraphTypes.CSRTreeType  => CSRNode(id, id)
    }

  }

}