package hvd.edu.benchmark.workload

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.WorkloadTypes.BreadthFirstSearch
import hvd.edu.benchmark.{ BenchmarkConfig, RecordableWorkload, Recorder, WorkLoad }
import hvd.edu.graph.{ Graph, Node, NodeMaker }
import hvd.edu.graph.al.ALNode
import hvd.edu.utils.{ Globals, GraphAlgos }

import scala.concurrent.duration.{ Duration, _ }

object BFWorkLoad extends WorkLoad with LazyLogging {

  override val workLoadType = BreadthFirstSearch

  override def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                                    gt: GraphType[N], iteration: Int, file: String,
                                    delimiter: String, linesInFile: Int): Unit = {

    val nodeIdToWalkFrom = benchmarkConfig.bfsFromNode(file)

    val recordedTime = {
      val g = gt.readG(file, delimiter, linesInFile)
      val n = gt.nodeMaker.make(nodeIdToWalkFrom)
      Globals.time {
        val foundNodes = GraphAlgos.bfs(n, g)
        logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
      }
    }
    val rw = RecordableWorkload(file, workLoadType, iteration, gt, Duration(recordedTime, MILLISECONDS))
    recorder.recordTimeForGraph(rw)
  }

}