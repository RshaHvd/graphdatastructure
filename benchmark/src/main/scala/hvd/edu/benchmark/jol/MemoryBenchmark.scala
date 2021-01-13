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

    for ((k, v) <- fileName_lineCount) yield {
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
    "generated/generated1k/generated1000_1000000.txt" -> 631568
  )

  override val outputFileName = "./output/mem_benchmark1KNodes"
}

object MemoryBenchmark10KNodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated10k/generated10000_1000.txt" -> 10488,
    "generated/generated10k/generated10000_10000.txt" -> 14942,
    "generated/generated10k/generated10000_100000.txt" -> 99926,
    "generated/generated10k/generated10000_1000000.txt" -> 995170
  )

  override val outputFileName = "./output/mem_benchmark10KNodes"
}

object MemoryBenchmark100KNodes extends MemoryBenchmark {

  override val fileName_lineCount: Map[String, Int] = Map(
    "generated/generated100k/generated100000_1000.txt" -> 100500,
    "generated/generated100k/generated100000_10000.txt" -> 104981,
    "generated/generated100k/generated100000_100000.txt" -> 149977,
    "generated/generated100k/generated100000_1000000.txt" -> 999942
  )

  override val outputFileName = "./output/mem_benchmark100KNodes"
}