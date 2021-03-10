package hvd.edu.benchmark.jmh.load

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object LoadBenchmark10mNodes {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class LoadAL10mBenchmark {

    @Param(Array(
      "generated/generated10000k/generated10000000_1000.txt:10000491",
      "generated/generated10000k/generated10000000_10000.txt:10004958",
      "generated/generated10000k/generated10000000_100000.txt:10050008",
      "generated/generated10000k/generated10000000_1000000.txt:10500346",
      "generated/generated10000k/generated10000000_10000000.txt:14999265"))
    var fileName_lineCount: String = _

    @Benchmark
    def loadALArray() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      ALArrayType.readG(fileName, "\\t", lineCount)
    }

    @Benchmark
    def loadALMap() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      ALMapType.readG(fileName, "\\t", lineCount)
    }

    @Benchmark
    def loadBplusTree() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      ALTreeType.readG(fileName, "\\t", lineCount)
    }

  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class LoadCSR10mBenchmark {

    @Param(Array(
      "generated/generated10000k/generated10000000_1000.txt:10000491",
      "generated/generated10000k/generated10000000_10000.txt:10004958",
      "generated/generated10000k/generated10000000_100000.txt:10050008",
      "generated/generated10000k/generated10000000_1000000.txt:10500346",
      "generated/generated10000k/generated10000000_10000000.txt:14999265"))
    var fileName_lineCount: String = _

    @Benchmark
    def loadCSRArray() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      CSRArrayType.readG(fileName, "\\t", lineCount)
    }

    @Benchmark
    def loadCSRMap() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      CSRMapType.readG(fileName, "\\t", lineCount)
    }

    @Benchmark
    def loadCSRBplusTree() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      CSRTreeType.readG(fileName, "\\t", lineCount)
      // println(GraphLayout.parseInstance(g).toFootprint)
    }

  }

}
