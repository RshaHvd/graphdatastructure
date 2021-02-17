package hvd.edu.benchmark.jmh.find.range.nodes10m

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark10MNodes1MEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray10M1MBenchmark {

    val graph = ALArrayType.readG("generated/generated10000k/generated10000000_1000000.txt", "\\t", 10500346)

    @Benchmark
    def findInGraph1() = {
      graph.rangeEdges(202, 9812624)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap10M1MBenchmark {
    val graph = ALMapType.readG("generated/generated10000k/generated10000000_1000000.txt", "\\t", 10500346)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(202, 9812624)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree10M1MBenchmark {
    val graph = ALTreeType.readG("generated/generated10000k/generated10000000_1000000.txt", "\\t", 10500346)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(202, 9812624)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray10M1MBenchmark {
    val graph = CSRArrayType.readG("generated/generated10000k/generated10000000_1000000.txt", "\\t", 10500346)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(202, 9812624)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap10M1MBenchmark {
    val graph = CSRMapType.readG("generated/generated10000k/generated10000000_1000000.txt", "\\t", 10500346)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(202, 9812624)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree10M1MBenchmark {
    val graph = CSRTreeType.readG("generated/generated10000k/generated10000000_1000000.txt", "\\t", 10500346)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(202, 9812624)
    }
  }

}
