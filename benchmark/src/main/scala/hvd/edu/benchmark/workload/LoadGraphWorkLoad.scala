package hvd.edu.benchmark.workload

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.benchmark.workload.WorkloadTypes.LoadGraph
import hvd.edu.benchmark.{ BenchmarkConfig, RecordableWorkload, Recorder, WorkLoad }
import hvd.edu.graph.Node
import hvd.edu.graph.al._
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }
import hvd.edu.utils.{ Globals, GraphInputFileReader }

import scala.concurrent.duration.{ Duration, _ }

object LoadGraphWorkLoad extends WorkLoad with LazyLogging {

  override val workLoadType = LoadGraph

  override def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                                    gt: GraphType, iteration: Int,
                                    filePath: String, delimiter: String,
                                    linesInFile: Int): Unit = {

    val recordedTime: Long = gt match {

      case ALArrayType => Globals.time {
        GraphInputFileReader.readFile[SetBasedALNode, ArrayALContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      }
      case ALMapType => Globals.time {
        GraphInputFileReader.readFile[DefaultALNode, HashMapALContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      }
      case ALTreeType => Globals.time {
        GraphInputFileReader.readFile[DefaultALNode, BplusTreeALContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      }
      case CSRArrayType => Globals.time {
        GraphInputFileReader.readFile[CSRNode, ArrayCSRContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      }
      case CSRMapType => Globals.time {
        GraphInputFileReader.readFile[CSRNode, HashMapCSRContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      }
      case CSRTreeType => Globals.time {
        GraphInputFileReader.readFile[CSRNode, BplusTreeCSRContainer](filePath = filePath, linesInFile, delimiter = delimiter)
      }

    }

    logger.info(s"Finished reading file in ${recordedTime} ms")
    val rw = RecordableWorkload(filePath, workLoadType, iteration, gt, Duration(recordedTime, MILLISECONDS))
    recorder.recordTimeForGraph(rw)

  }

}