package hvd.edu.benchmark.jmh.find.range.nodes1m

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark1MNodes1KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray1M1KBenchmark {

    val graph = ALArrayType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    @Benchmark
    def findInGraph1() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap1M1KBenchmark {
    val graph = ALMapType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree1M1KBenchmark {
    val graph = ALTreeType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray1M1KBenchmark {
    val graph = CSRArrayType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap1M1KBenchmark {
    val graph = CSRMapType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(202, 15745)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree1M1KBenchmark {
    val graph = CSRTreeType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(202, 15745)
    }
  }

}
