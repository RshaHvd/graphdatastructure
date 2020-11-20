package hvd.edu.benchmark

import hvd.edu.benchmark.workload.GraphType

import scala.collection.mutable
import scala.concurrent.duration.Duration

class Recorder(configForThisWorkload: BenchmarkConfig) {

  private val measuredTimeByGraph: mutable.Map[RecordableWorkload, RecordableDuration] = mutable.Map.empty

  def recordTimeForGraph(w: RecordableWorkload, d: RecordableDuration) = {
    measuredTimeByGraph(w) = d
  }

  def writeToCSV(): Unit = {}

  def flushToConsole(): Unit = {}
}

case class RecordableWorkload(workLoad: WorkLoad, iteration: Int)

case class RecordableDuration(graphType: GraphType, duration: Duration)

