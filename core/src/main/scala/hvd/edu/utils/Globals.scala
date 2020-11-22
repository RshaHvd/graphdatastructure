package hvd.edu.utils

import com.typesafe.scalalogging.LazyLogging

object Globals extends LazyLogging {

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

  def time[T](f: => T): Long = {
    logger.debug("Start Recording Time")
    val t = System.currentTimeMillis
    val r = f // execute and return
    val t2 = System.currentTimeMillis - t
    logger.debug("End Recording Time")
    t2
  }

}
