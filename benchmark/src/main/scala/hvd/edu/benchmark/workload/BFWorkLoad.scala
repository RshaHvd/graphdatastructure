package hvd.edu.benchmark.workload

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.benchmark.workload.WorkloadTypes.BreadthFirstSearch
import hvd.edu.benchmark.{ BenchmarkConfig, RecordableWorkload, Recorder, WorkLoad }
import hvd.edu.graph.Node
import hvd.edu.graph.al.{ ArrayALContainer, BplusTreeALContainer, DefaultALNode, HashMapALContainer, SetBasedALNode }
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }
import hvd.edu.utils.{ Globals, GraphAlgos, GraphInputFileReader }

import scala.concurrent.duration.{ Duration, _ }

object BFWorkLoad extends WorkLoad with LazyLogging {

  override val workLoadType = BreadthFirstSearch

  override def benchmark[N <: Node](benchmarkConfig: BenchmarkConfig, recorder: Recorder,
                                    gt: GraphType, iteration: Int, file: String,
                                    delimiter: String, linesInFile: Int): Unit = {

    val nodeIdToWalkFrom = benchmarkConfig.bfsFromNode(file)

    val recordedTime = gt match {

      case GraphTypes.ALArrayType => {
        val g = GraphInputFileReader.readFile[SetBasedALNode, ArrayALContainer](filePath = file, linesInFile, delimiter = delimiter)
        val n = SetBasedALNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
        }
      }
      case GraphTypes.ALMapType => {
        val g = GraphInputFileReader.readFile[DefaultALNode, HashMapALContainer](filePath = file, linesInFile, delimiter = delimiter)
        val n = DefaultALNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
        }
      }
      case GraphTypes.ALTreeType => {
        val g = GraphInputFileReader.readFile[DefaultALNode, BplusTreeALContainer](filePath = file, linesInFile, delimiter = delimiter)
        val n = DefaultALNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
        }
      }
      case GraphTypes.CSRArrayType => {
        val g = GraphInputFileReader.readFile[CSRNode, ArrayCSRContainer](filePath = file, linesInFile, delimiter = delimiter)
        val n = CSRNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          //          if (file == "cit-HepTh.txt" || file == "com-youtube.ungraph.txt") // FileType needed - we can exclude certain runs !! for now hardcoded
          //          {
          //            logger.info(s"SKIPPING BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} as it takes too long")
          //          }
          //          else {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size}")
        }
      }
      //}
      case GraphTypes.CSRMapType => {
        val g = GraphInputFileReader.readFile[CSRNode, HashMapCSRContainer](filePath = file, linesInFile, delimiter = delimiter)
        val n = CSRNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size}")
        }
      }
      case GraphTypes.CSRTreeType => {
        val g = GraphInputFileReader.readFile[CSRNode, BplusTreeCSRContainer](filePath = file, linesInFile, delimiter = delimiter)
        val n = CSRNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size}")
        }
      }
    }

    val rw = RecordableWorkload(file, workLoadType, iteration, gt, Duration(recordedTime, MILLISECONDS))
    recorder.recordTimeForGraph(rw)
  }

}