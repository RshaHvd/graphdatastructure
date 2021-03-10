package hvd.edu.benchmark.jmh.find.range.nodes1m

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark1MNodes10MEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray1M10MBenchmark {

    val graph = ALArrayType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    @Benchmark
    def findInGraph1() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap1M10MBenchmark {
    val graph = ALMapType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree1M10MBenchmark {
    val graph = ALTreeType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray1M10MBenchmark {
    val graph = CSRArrayType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap1M10MBenchmark {
    val graph = CSRMapType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree1M10MBenchmark {
    val graph = CSRTreeType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(202, 15745)
    }
  }

}
