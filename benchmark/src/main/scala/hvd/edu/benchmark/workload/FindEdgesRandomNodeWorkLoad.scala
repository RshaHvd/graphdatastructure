package hvd.edu.benchmark.workload

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.WorkloadTypes.FindEdgesOfRandomNode
import hvd.edu.benchmark.{ BenchmarkConfig, RecordableWorkload, Recorder, WorkLoad }
import hvd.edu.graph.Node
import hvd.edu.utils.Globals

import scala.concurrent.duration.{ Duration, _ }

object FindEdgesRandomNodeWorkLoad extends WorkLoad with LazyLogging {

  override val workLoadType = FindEdgesOfRandomNode

  override def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                                    gt: GraphType, iteration: Int, file: String,
                                    delimiter: String, linesInFile: Int): Unit = {

    // first read graph
    val readGraph = GraphTypeUtils.readGraphFromFile(gt, file, delimiter, linesInFile)
    val randomNodeIdToRead = benchmarkConfig.getRandomFor(file)

    Globals.timeAndLog2 {
      readGraph.edgesForVertexId(randomNodeIdToRead)
    } { (foundNodes, recordedTime) =>
      logger.info(s"FoundNodes ${foundNodes.size}  in ${recordedTime} ms")
      val rw = RecordableWorkload(file, workLoadType, iteration, gt, Duration(recordedTime, MILLISECONDS))
      recorder.recordTimeForGraph(rw)
    }
  }

}