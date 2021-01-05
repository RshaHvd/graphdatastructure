package hvd.edu.benchmark.workload

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.WorkloadTypes.LoadGraph
import hvd.edu.benchmark.{ BenchmarkConfig, RecordableWorkload, Recorder, WorkLoad }
import hvd.edu.graph.Node
import hvd.edu.utils.Globals

import scala.concurrent.duration.{ Duration, _ }

object LoadGraphWorkLoad extends WorkLoad with LazyLogging {

  override val workLoadType = LoadGraph

  override def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                                    gt: GraphType[N], iteration: Int,
                                    filePath: String, delimiter: String,
                                    linesInFile: Int): Unit = {

    val recordedTime: Long = Globals.time { gt.readG(filePath, delimiter, linesInFile) }
    logger.info(s"Finished reading file in ${recordedTime} ms")
    val rw = RecordableWorkload(filePath, workLoadType, iteration, gt, Duration(recordedTime, MILLISECONDS))
    recorder.recordTimeForGraph(rw)

  }

}