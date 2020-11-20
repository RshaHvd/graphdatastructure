package hvd.edu.utils

object Globals {

  def timeAndLog[T](f: => T)(l: Long => Unit): T = {
    val t = System.currentTimeMillis
    val r = f
    l.apply(System.currentTimeMillis - t)
    r
  }

  def timeAndLog2[T](f: => T)(l: (T, Long) => Unit): T = {
    val t = System.currentTimeMillis
    val r = f
    l.apply(r, System.currentTimeMillis - t)
    r
  }

  def timeAndLog3(f: => Unit)(l: Long => Unit) {
    val t = System.currentTimeMillis
    val r = f
    l.apply(System.currentTimeMillis - t)
  }

}
