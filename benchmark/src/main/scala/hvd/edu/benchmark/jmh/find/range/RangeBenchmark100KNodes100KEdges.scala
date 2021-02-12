package hvd.edu.benchmark.jmh.find.range

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark100KNodes100KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray100K100KBenchmark {

    val graph = ALArrayType.readG("generated/generated100k/generated100000_100000.txt", "\\t", 149977)

    @Benchmark
    def findInGraph1() = {
      //95, 99794
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap100K100KBenchmark {
    val graph = ALMapType.readG("generated/generated100k/generated100000_100000.txt", "\\t", 149977)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree100K100KBenchmark {
    val graph = ALTreeType.readG("generated/generated100k/generated100000_100000.txt", "\\t", 149977)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray100K100KBenchmark {
    val graph = CSRArrayType.readG("generated/generated100k/generated100000_100000.txt", "\\t", 149977)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap100K100KBenchmark {
    val graph = CSRMapType.readG("generated/generated100k/generated100000_100000.txt", "\\t", 149977)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree100K100KBenchmark {
    val graph = CSRTreeType.readG("generated/generated100k/generated100000_100000.txt", "\\t", 149977)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(10340, 35592)
    }
  }
}
