package hvd.edu.benchmark.workload

import enumeratum._

sealed abstract class WorkloadType(override val entryName: String) extends EnumEntry {
  def displayName = entryName
}

object WorkloadTypes extends Enum[WorkloadType] {

  val values = findValues

  case object LoadGraph extends WorkloadType("LG") {
    override val displayName = "Load"
  }

  case object FindEdgesOfRandomNode extends WorkloadType("FE") {
    override val displayName = "Edges"
  }

  case object BreadthFirstSearch extends WorkloadType("BFS") {
    override val displayName = "BFS"
  }

  case object DepthFirstSearch extends WorkloadType("DFS") {
    override val displayName = "DFS"
  }

}
