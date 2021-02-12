package hvd.edu.benchmark.jmh.load

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object LoadBenchmark10kNodes {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class LoadAL10KBenchmark {

    @Param(Array(
      "generated/generated10k/generated10000_1000.txt:10488",
      "generated/generated10k/generated10000_10000.txt:14942",
      "generated/generated10k/generated10000_100000.txt:99926",
      "generated/generated10k/generated10000_1000000.txt:995170",
      "generated/generated10k/generated10000_10000000.txt:9517247"))
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
  class LoadCSR10KBenchmark {

    @Param(Array(
      "generated/generated10k/generated10000_1000.txt:10488",
      "generated/generated10k/generated10000_10000.txt:14942",
      "generated/generated10k/generated10000_100000.txt:99926",
      "generated/generated10k/generated10000_1000000.txt:995170",
      "generated/generated10k/generated10000_10000000.txt:9517247"))
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
