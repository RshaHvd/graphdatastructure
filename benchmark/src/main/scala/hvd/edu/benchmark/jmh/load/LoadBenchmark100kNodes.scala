package hvd.edu.benchmark.jmh.load

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object LoadBenchmark100kNodes {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class LoadAL100kBenchmark {

    @Param(Array(
      "generated/generated100k/generated100000_1000.txt:100500",
      "generated/generated100k/generated100000_10000.txt:104981",
      "generated/generated100k/generated100000_100000.txt:149977",
      "generated/generated100k/generated100000_1000000.txt:999942",
      "generated/generated100k/generated100000_10000000.txt:9995030"))
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
  class LoadCSR100kBenchmark {

    @Param(Array(
      "generated/generated100k/generated100000_1000.txt:100500",
      "generated/generated100k/generated100000_10000.txt:104981",
      "generated/generated100k/generated100000_100000.txt:149977",
      "generated/generated100k/generated100000_1000000.txt:999942",
      "generated/generated100k/generated100000_10000000.txt:9995030"))
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
