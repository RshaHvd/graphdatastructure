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
                                    gt: GraphType, iteration: Int, file: String, delimiter: String): Unit = {

    val nodeIdToWalkFrom = benchmarkConfig.bfsFromNode(file)

    val recordedTime = gt match {

      case GraphTypes.ALArrayType => {
        val g = GraphInputFileReader.readFile2[SetBasedALNode, ArrayALContainer](filePath = file, delimiter = delimiter)
        val n = SetBasedALNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
        }
      }
      case GraphTypes.ALMapType => {
        val g = GraphInputFileReader.readFile2[DefaultALNode, HashMapALContainer](filePath = file, delimiter = delimiter)
        val n = DefaultALNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
        }
      }
      case GraphTypes.ALTreeType => {
        val g = GraphInputFileReader.readFile2[DefaultALNode, BplusTreeALContainer](filePath = file, delimiter = delimiter)
        val n = DefaultALNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size} ")
        }
      }
      case GraphTypes.CSRArrayType => {
        val g = GraphInputFileReader.readFile2[CSRNode, ArrayCSRContainer](filePath = file, delimiter = delimiter)
        val n = CSRNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          if (file == "cit-HepTh.txt") // FileType needed - we can exclude certain runs !! for now hardcoded
          {
            Long.MaxValue
            logger.info(s"SKIPPING BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} as takes too long")
          }
          else {
            val foundNodes = GraphAlgos.bfs(n, g)
            logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size}")
          }
        }
      }
      case GraphTypes.CSRMapType => {
        val g = GraphInputFileReader.readFile2[CSRNode, HashMapCSRContainer](filePath = file, delimiter = delimiter)
        val n = CSRNode(nodeIdToWalkFrom, nodeIdToWalkFrom)
        Globals.time {
          val foundNodes = GraphAlgos.bfs(n, g)
          logger.info(s"BFS-${gt.entryName} from Node: ${nodeIdToWalkFrom} found:${foundNodes.size}")
        }
      }
      case GraphTypes.CSRTreeType => {
        val g = GraphInputFileReader.readFile2[CSRNode, BplusTreeCSRContainer](filePath = file, delimiter = delimiter)
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