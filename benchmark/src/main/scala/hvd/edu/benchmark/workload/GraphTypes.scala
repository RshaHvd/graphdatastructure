package hvd.edu.benchmark.workload

import hvd.edu.graph._
import hvd.edu.graph.al.{ ArrayALContainer, HashMapALContainer, _ }
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }
import hvd.edu.utils.{ GraphAlgos, GraphInputFileReader }

sealed abstract class GraphType[N <: Node](val entryName: String, val nodeMaker: NodeMaker[N]) {
  def displayName = entryName
  def graphContainer(linesInFile: Int): GraphContainer[N]
  def readG(filePath: String, delimiter: String, linesInFile: Int): Graph[N] = {
    GraphInputFileReader.readFile(filePath, linesInFile, delimiter, graphContainer(linesInFile), nodeMaker)
  }
}

object GraphTypes {

  val ALL = List(ALArrayType, ALMapType, ALTreeType, CSRArrayType, CSRMapType, CSRTreeType)

  case object ALArrayType extends GraphType[ALNode]("ALA", DefaultALNodeMaker) {
    override val displayName: String = "AL-Array"
    override def graphContainer(linesInFile: Int): GraphContainer[ALNode] = ArrayALContainer(linesInFile)
  }

  case object ALMapType extends GraphType[ALNode]("ALM", DefaultALNodeMaker) {
    override val displayName: String = "AL-Map"
    override def graphContainer(linesInFile: Int): GraphContainer[ALNode] = HashMapALContainer(linesInFile)
  }

  case object ALTreeType extends GraphType[ALNode]("ALT", DefaultALNodeMaker) {
    override val displayName: String = "AL-Tree"
    override def graphContainer(linesInFile: Int): GraphContainer[ALNode] = BplusTreeALContainer(linesInFile, Option(100))
  }

  case object CSRArrayType extends GraphType[CSRNode]("CSRA", CSRNodeMaker) {
    override val displayName: String = "CSR-Array"
    override def graphContainer(linesInFile: Int): GraphContainer[CSRNode] = ArrayCSRContainer(linesInFile, linesInFile)
  }

  case object CSRMapType extends GraphType[CSRNode]("CSRM", CSRNodeMaker) {
    override val displayName: String = "CSR-Map"
    override def graphContainer(linesInFile: Int): GraphContainer[CSRNode] = HashMapCSRContainer(linesInFile, linesInFile)
  }

  case object CSRTreeType extends GraphType[CSRNode]("CSRT", CSRNodeMaker) {
    override val displayName: String = "CSR-Tree"
    override def graphContainer(linesInFile: Int): GraphContainer[CSRNode] = BplusTreeCSRContainer(linesInFile, Option(100))
  }

}