package hvd.edu.collection.mutable
import scala.math.Ordering
import Ordering.Implicits._

/**
 * https://stackoverflow.com/questions/56370907/how-can-i-mathematically-compare-two-unknown-types-in-scala
 */
object BPlusTreeUtils {

  def between(t1: Any, t2: Any, d: Any): Boolean = (t1, t2, d) match {
    case (a1: Int, a2: Int, b1: Int) => (b1 >= a1 && b1 <= a2)
    case _                           => false
  }

  def greaterThan(t1: Any, d: Any): Boolean = (t1, d) match {
    case (a1: Int, b1: Int) => (b1 > a1)
    case _                  => false
  }

}
