package hvd.edu.benchmark

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.graph.al._
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }
import hvd.edu.utils.{ Globals, GraphInputFileReader }

trait WorkLoad {
  def benchmark(benchmarkConfig: BenchmarkConfig, recorder: Recorder): Unit
}

object LoadGraphWorkLoad extends WorkLoad with LazyLogging {

  override def benchmark(benchmarkConfig: BenchmarkConfig, recorder: Recorder): Unit = {

    // create graphs
    val alGraph = GraphInputFileReader.readFile2[SetBasedALNode, ArrayALContainer](
      filePath = benchmarkConfig.filePath, //"cit-HepTh.txt",
      // fileLength = 352807,
      delimiter = "\t"
    )

    Globals.timeAndLog2 {
      alGraph.edgesForVertexId(9603161)
    } { (allEdges, ms) =>
      logger.debug(s"Total Edges  AL Found for 9603161: ${allEdges.size} in ${ms} ms")
    }

    val alGraphhashMap = GraphInputFileReader.readFile2[DefaultALNode, HashMapALContainer](
      filePath = benchmarkConfig.filePath, //"cit-HepTh.txt",
      // fileLength = 352807,
      delimiter = "\t"
    )

    Globals.timeAndLog2 {
      alGraphhashMap.edgesForVertexId(9603161)
    } { (allEdgesCSRFound, ms) =>
      logger.debug(s"Total Edges ALHashMao Found for 9603161: ${allEdgesCSRFound.size} in ${ms} ms")
      // println(s"${allEdgesCSRFound.mkString(",")}")
    }

    val alPlusTree = GraphInputFileReader.readFile2[DefaultALNode, BplusTreeALContainer](
      filePath = benchmarkConfig.filePath, //"cit-HepTh.txt",
      // fileLength = 352807,
      delimiter = "\t"
    )

    Globals.timeAndLog2 {
      alPlusTree.edgesForVertexId(9603161)
    } { (allEdgesCSRFound, ms) =>
      logger.debug(s"Total Edges AL BplusTree Found for 9603161: ${allEdgesCSRFound.size} in ${ms} ms")
      // println(s"${allEdgesCSRFound.mkString(",")}")
    }

    val csrGraph = GraphInputFileReader.readFile2[CSRNode, ArrayCSRContainer](
      filePath = benchmarkConfig.filePath, //"cit-HepTh.txt",
      // fileLength = 352807,
      delimiter = "\t"
    )

    Globals.timeAndLog2 {
      csrGraph.edgesForVertexId(9603161)
    } { (allEdgesCSRFound, ms) =>
      logger.debug(s"Total Edges CSR Found for 9603161: ${allEdgesCSRFound.size} in ${ms} ms")
      // println(s"${allEdgesCSRFound.mkString(",")}")
    }

    val csrHashMap = GraphInputFileReader.readFile2[CSRNode, HashMapCSRContainer](
      filePath = benchmarkConfig.filePath, //"cit-HepTh.txt",
      // fileLength = 352807,
      delimiter = "\t"
    )

    Globals.timeAndLog2 {
      csrHashMap.edgesForVertexId(9603161)
    } { (allEdgesCSRFound, ms) =>
      logger.debug(s"Total Edges CSRHashMapBased Found for 9603161: ${allEdgesCSRFound.size} in ${ms} ms")
      // println(s"${allEdgesCSRFound.mkString(",")}")
    }

    val csrBplusTree = GraphInputFileReader.readFile2[CSRNode, BplusTreeCSRContainer](
      filePath = benchmarkConfig.filePath, //"cit-HepTh.txt",
      // fileLength = 352807,
      delimiter = "\t"
    )

    Globals.timeAndLog2 {
      csrBplusTree.edgesForVertexId(9603161)
    } { (allEdgesCSRFound, ms) =>
      logger.debug(s"Total Edges CSRBplusTree Found for 9603161: ${allEdgesCSRFound.size} in ${ms} ms")
      // println(s"${allEdgesCSRFound.mkString(",")}")
    }
  }

}
