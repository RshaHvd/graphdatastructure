package hvd.edu.benchmark.jmh

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object LoadBenchmark1kNodes {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class LoadAL1KBenchmark {

    @Param(Array(
      "generated/generated1k/generated1000_1000.txt:1488",
      "generated/generated1k/generated1000_10000.txt:9941",
      "generated/generated1k/generated1000_100000.txt:95174",
      "generated/generated1k/generated1000_1000000.txt:631568"))
    var fileName_lineCount: String = _

    @Benchmark
    def loadALArray() = {
      val params = fileName_lineCount.split(":")
      val fileName = params.head
      val lineCount = params.last.toInt
      ALArrayType.readG(fileName, "\\t", lineCount)
      // println(GraphLayout.parseInstance(g).toFootprint)
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
  class LoadCSR1KBenchmark {

    @Param(Array(
      "generated/generated1k/generated1000_1000.txt:1488",
      "generated/generated1k/generated1000_10000.txt:9941",
      "generated/generated1k/generated1000_100000.txt:95174",
      "generated/generated1k/generated1000_1000000.txt:631568"))
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
    }

  }

}