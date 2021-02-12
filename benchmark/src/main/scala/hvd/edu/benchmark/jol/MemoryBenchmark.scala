package hvd.edu.benchmark.jol

import hvd.edu.benchmark.utils.BenchmarkCSVWriter
import hvd.edu.benchmark.workload.GraphType
import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jol.info.GraphLayout

import scala.collection.mutable

trait MemoryBenchmark {

  def fileName_lineCount: Map[String, Int]

  def outputFileName: String

  val recorder = mutable.ListBuffer[(GraphType[_], String, Long)]()

  def loadALArray(fileName: String, lineCount: Int) = {
    val g = ALArrayType.readG(fileName, "\\t", lineCount)
    recorder.append((ALArrayType, fileName, GraphLayout.parseInstance(g).totalSize()))
  }

  def loadALHashMap(fileName: String, lineCount: Int) = {
    val g = ALMapType.readG(fileName, "\\t", lineCount)
    recorder.append((ALMapType, fileName, GraphLayout.parseInstance(g).totalSize()))
  }

  def loadALBTree(fileName: String, lineCount: Int) = {
    val g = ALTreeType.readG(fileName, "\\t", lineCount)
    recorder.append((ALTreeType, fileName, GraphLayout.parseInstance(g).totalSize()))
  }

  def loadCSRArray(fileName: String, lineCount: Int) = {
    val g = CSRArrayType.readG(fileName, "\\t", lineCount)
    recorder.append((CSRArrayType, fileName, GraphLayout.parseInstance(g).totalSize()))
  }

  def loadCSRMap(fileName: String, lineCount: Int) = {
    val g = CSRMapType.readG(fileName, "\\t", lineCount)
    recorder.append((CSRMapType, fileName, GraphLayout.parseInstance(g).totalSize()))
  }

  def loadCSRBplusTree(fileName: String, lineCount: Int) = {
    val g = CSRTreeType.readG(fileName, "\\t", lineCount)
    recorder.append((CSRTreeType, fileName, GraphLayout.parseInstance(g).totalSize()))
  }

  def benchmark(): Unit = {

    fileName_lineCount.foreach {
      case (k, v) =>
        loadALArray(k, v)
        loadALHashMap(k, v)
        loadALBTree(k, v)
        loadCSRArray(k, v)
        loadCSRMap(k, v)
        loadCSRBplusTree(k, v)
    }

    val header: Seq[Seq[Any]] = Seq(Seq("Benchmark", "FileName", "TotalSize (In bytes)"))
    val dataRows: Seq[Seq[Any]] = recorder.map(x => Seq(x._1.displayName, x._2, x._3))
    val allRows = header ++ dataRows
    BenchmarkCSVWriter.apply(outputFileName, allRows)

  }

}

object MemoryBenchmark1KNodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated1k/generated1000_1000.txt" -> 1488,
    "generated/generated1k/generated1000_10000.txt" -> 9941,
    "generated/generated1k/generated1000_100000.txt" -> 95174,
    "generated/generated1k/generated1000_1000000.txt" -> 631568,
    "generated/generated1k/generated1000_10000000.txt" -> 998954)

  override val outputFileName = "./output/mem_benchmark1KNodes"
}

object MemoryBenchmark10KNodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10k/generated10000_1000.txt" -> 10488,
    "generated/generated10k/generated10000_10000.txt" -> 14942,
    "generated/generated10k/generated10000_100000.txt" -> 99926,
    "generated/generated10k/generated10000_1000000.txt" -> 995170,
    "generated/generated10k/generated10000_10000000.txt" -> 9517247)

  override val outputFileName = "./output/mem_benchmark10KNodes"
}

object MemoryBenchmark100KNodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated100k/generated100000_1000.txt" -> 100500,
    "generated/generated100k/generated100000_10000.txt" -> 104981,
    "generated/generated100k/generated100000_100000.txt" -> 149977,
    "generated/generated100k/generated100000_1000000.txt" -> 999942,
    "generated/generated100k/generated100000_10000000.txt" -> 9995030)

  override val outputFileName = "./output/mem_benchmark100KNodes"
}

object MemoryBenchmark1MNodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated1000k/generated1000000_1000.txt" -> 1000486,
    "generated/generated1000k/generated1000000_10000.txt" -> 1004935,
    "generated/generated1000k/generated1000000_100000.txt" -> 1050084,
    "generated/generated1000k/generated1000000_1000000.txt" -> 1499588,
    "generated/generated1000k/generated1000000_10000000.txt" -> 10000001)

  override val outputFileName = "./output/mem_benchmark1MNodes"
}

object MemoryBenchmark10M1KENodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10000k/generated10000000_1000.txt" -> 10000491)
  //"generated/generated10000k/generated10000000_10000.txt" -> 10004958,
  //"generated/generated10000k/generated10000000_100000.txt" -> 10050008,
  //"generated/generated10000k/generated10000000_1000000.txt" -> 10500346,
  //"generated/generated10000k/generated10000000_10000000.txt" -> 14999265)

  override val outputFileName = "./output/mem_benchmark10MNodes1KEdges"
}

object MemoryBenchmark10M10KENodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10000k/generated10000000_10000.txt" -> 10004958)
  //"generated/generated10000k/generated10000000_100000.txt" -> 10050008,
  //"generated/generated10000k/generated10000000_1000000.txt" -> 10500346,
  //"generated/generated10000k/generated10000000_10000000.txt" -> 14999265)

  override val outputFileName = "./output/mem_benchmark10MNodes10KEdges"
}

object MemoryBenchmark10M100KENodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10000k/generated10000000_100000.txt" -> 10050008)
  //"generated/generated10000k/generated10000000_1000000.txt" -> 10500346,
  //"generated/generated10000k/generated10000000_10000000.txt" -> 14999265)

  override val outputFileName = "./output/mem_benchmark10MNodes100KEdges"
}

object MemoryBenchmark10M1MENodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10000k/generated10000000_1000000.txt" -> 10500346)
  //"generated/generated10000k/generated10000000_10000000.txt" -> 14999265)

  override val outputFileName = "./output/mem_benchmark10MNodes1MEdges"
}

object MemoryBenchmark10M10MENodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10000k/generated10000000_10000000.txt" -> 14999265)

  override val outputFileName = "./output/mem_benchmark10MNodes10MEdges"
}

//object MemoryBenchmarksRealDataSets extends MemoryBenchmark {
//
//  override val fileName_lineCount: Map[String, Int] = Map(
//    "realdata/amazon0601.txt" -> 3387392,
//    "realdata/email-Enron.txt" -> 367666,
//    "realdata/cit-HepTh.txt" -> 352811)
//
//  override val outputFileName = "./output/mem_benchmarkRealDataSets"
//}