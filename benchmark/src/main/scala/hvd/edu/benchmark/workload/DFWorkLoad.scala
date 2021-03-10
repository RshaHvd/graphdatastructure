package hvd.edu.benchmark.workload

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.WorkloadTypes.DepthFirstSearch
import hvd.edu.benchmark.{ BenchmarkConfig, RecordableWorkload, Recorder, WorkLoad }
import hvd.edu.graph.{ Graph, Node }
import hvd.edu.utils.{ Globals, GraphAlgos }

import scala.concurrent.duration.{ Duration, _ }

object DFWorkLoad extends WorkLoad with LazyLogging {

  override val workLoadType = DepthFirstSearch

  override def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                                    gt: GraphType[N], iteration: Int, file: String, delimiter: String,
                                    linesInFile: Int): Unit = {

    val nodeIdToWalkFrom = benchmarkConfig.dfsFromNode(file)

    val recordedTime = {

      val g: Graph[N] = gt.readG(file, delimiter, linesInFile)
      val n: N = gt.nodeMaker.make(nodeIdToWalkFrom)
      Globals.time {
        val foundNodes = GraphAlgos.dfsIterative(n, g)
        logger.info(s"DFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size}")
      }
    }
    val rw = RecordableWorkload(file, workLoadType, iteration, gt, Duration(recordedTime, MILLISECONDS))
    recorder.recordTimeForGraph(rw)
  }

}