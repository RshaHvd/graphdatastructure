package hvd.edu.benchmark.workload

import enumeratum._
import hvd.edu.benchmark.{ LoadGraphWorkLoad, WorkLoad }

sealed abstract class WorkloadType(override val entryName: String) extends EnumEntry {
  def workLoad: WorkLoad
}

object WorkloadTypes extends Enum[WorkloadType] {

  val values = findValues

  case object LoadGraph extends WorkloadType("LG") {
    override val workLoad: WorkLoad = LoadGraphWorkLoad
  }

  case object FindNeighbors extends WorkloadType("FN") {
    override val workLoad: WorkLoad = LoadGraphWorkLoad
  }

}
