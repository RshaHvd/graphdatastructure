package hvd.edu.benchmark.jmh

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark100KNodes1KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray100K1KBenchmark {

    val graph = ALArrayType.readG("generated/generated100k/generated100000_1000.txt", "\\t", 100500)

    @Benchmark
    def findInGraph1() = {
      //95, 99794
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap100K1KBenchmark {
    val graph = ALMapType.readG("generated/generated100k/generated100000_1000.txt", "\\t", 100500)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree100K1KBenchmark {
    val graph = ALTreeType.readG("generated/generated100k/generated100000_1000.txt", "\\t", 100500)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray100K1KBenchmark {
    val graph = CSRArrayType.readG("generated/generated100k/generated100000_1000.txt", "\\t", 100500)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap100K1KBenchmark {
    val graph = CSRMapType.readG("generated/generated100k/generated100000_1000.txt", "\\t", 100500)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(10340, 35592)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree100K1KBenchmark {
    val graph = CSRTreeType.readG("generated/generated100k/generated100000_1000.txt", "\\t", 100500)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(10340, 35592)
    }
  }
}