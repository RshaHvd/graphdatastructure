package hvd.edu.benchmark.workload

import enumeratum._

sealed abstract class GraphType(override val entryName: String) extends EnumEntry

object GraphTypes extends Enum[GraphType] {

  val values = findValues

  case object ALArrayType extends GraphType("ALA")
  case object ALMapType extends GraphType("ALM")
  case object ALTreeType extends GraphType("ALT")
  case object CSRArrayType extends GraphType("CSRA")
  case object CSRMapType extends GraphType("CSRM")
  case object CSRTreeType extends GraphType("CSRT")
}
