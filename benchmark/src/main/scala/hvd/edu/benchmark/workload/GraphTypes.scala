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

  def readGraphFromFile(gt: GraphType, filePath: String, delimiter: String, linesInFile: Int) = {
    gt match {
      case GraphTypes.ALArrayType  => GraphInputFileReader.readFile[SetBasedALNode, ArrayALContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      case GraphTypes.ALMapType    => GraphInputFileReader.readFile[DefaultALNode, HashMapALContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      case GraphTypes.ALTreeType   => GraphInputFileReader.readFile[DefaultALNode, BplusTreeALContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      case GraphTypes.CSRArrayType => GraphInputFileReader.readFile[CSRNode, ArrayCSRContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      case GraphTypes.CSRMapType   => GraphInputFileReader.readFile[CSRNode, HashMapCSRContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      case GraphTypes.CSRTreeType  => GraphInputFileReader.readFile[CSRNode, BplusTreeCSRContainer](filePath = filePath, linesInFile, delimiter = delimiter)
    }

  }

}