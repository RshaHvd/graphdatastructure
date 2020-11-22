package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.utils.Table
import hvd.edu.benchmark.workload.{ GraphType, WorkloadType }

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration

class Recorder(configForThisWorkload: BenchmarkConfig) extends LazyLogging {

  private val measuredTimeByGraph: ListBuffer[RecordableWorkload] = mutable.ListBuffer[RecordableWorkload]()

  def recordTimeForGraph(w: RecordableWorkload) = {
    measuredTimeByGraph.append(w)
  }

  def flushToConsole(): Unit = {
    logger.info(measuredTimeByGraph.toList.mkString(","))
  }

  def recordedDataAsSeq(): Seq[Seq[Any]] = {
    val headers: Seq[Any] = Seq("DataSet", "WorkLoad", "Iteration", "GraphType", "TimeInMillis")
    val rows: Seq[Seq[Any]] = measuredTimeByGraph.map(
      rw => Seq(rw.dataSet, rw.workLoad.displayName, rw.iteration, rw.graphType.displayName, rw.duration.toMillis)
    )
    val finalSeq = Seq(headers) ++ rows
    finalSeq
  }
}

case class RecordableWorkload(dataSet: String, workLoad: WorkloadType,
                              iteration: Int, graphType: GraphType, duration: Duration) {
  override def toString: String = s"${dataSet}:${workLoad.entryName}:${iteration}:${graphType.entryName}:${duration.toMillis}"
}

