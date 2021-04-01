package hvd.edu.benchmark.jmh.find.range.nodes1k

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark1KNodes10MEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray1K10MBenchmark {

    val graph = ALArrayType.readG("generated/generated1k/generated1000_10000000.txt", "\\t", 998954)

    @Benchmark
    def findInGraph1() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap1K10MBenchmark {
    val graph = ALMapType.readG("generated/generated1k/generated1000_10000000.txt", "\\t", 998954)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree1K10MBenchmark {
    val graph = ALTreeType.readG("generated/generated1k/generated1000_10000000.txt", "\\t", 998954)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray1K10MBenchmark {
    val graph = CSRArrayType.readG("generated/generated1k/generated1000_10000000.txt", "\\t", 998954)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap1K10MBenchmark {
    val graph = CSRMapType.readG("generated/generated1k/generated1000_10000000.txt", "\\t", 998954)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree1K10MBenchmark {
    val graph = CSRTreeType.readG("generated/generated1k/generated1000_10000000.txt", "\\t", 998954)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(8, 974)
    }
  }

}
