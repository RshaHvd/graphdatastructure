package hvd.edu.benchmark.jmh.find.range

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark10KNodes1MEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray10K1MBenchmark {

    val graph = ALArrayType.readG("generated/generated10k/generated10000_1000000.txt", "\\t", 995170)

    @Benchmark
    def findInGraph1() = {
      graph.rangeEdges(47, 9818)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap10K1MBenchmark {
    val graph = ALMapType.readG("generated/generated10k/generated10000_1000000.txt", "\\t", 995170)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(47, 9818)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree10K1MBenchmark {
    val graph = ALTreeType.readG("generated/generated10k/generated10000_1000000.txt", "\\t", 995170)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(47, 9818)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray10K1MBenchmark {
    val graph = CSRArrayType.readG("generated/generated10k/generated10000_1000000.txt", "\\t", 995170)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(47, 9818)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap10K1MBenchmark {
    val graph = CSRMapType.readG("generated/generated10k/generated10000_1000000.txt", "\\t", 995170)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(47, 9818)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree10K1MBenchmark {
    val graph = CSRTreeType.readG("generated/generated10k/generated10000_1000000.txt", "\\t", 995170)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(47, 9818)
    }
  }
}
