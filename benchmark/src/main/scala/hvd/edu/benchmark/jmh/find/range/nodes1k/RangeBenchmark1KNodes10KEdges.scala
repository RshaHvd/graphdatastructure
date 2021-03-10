package hvd.edu.benchmark.jmh.find.range.nodes1k

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object RangeBenchmark1KNodes10KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALArray1K10KBenchmark {

    val graph = ALArrayType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    @Benchmark
    def findInGraph1() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALMap1K10KBenchmark {
    val graph = ALMapType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    @Benchmark
    def findInGraph2() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeALTree1K10KBenchmark {
    val graph = ALTreeType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    @Benchmark
    def findInGraph3() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRArray1K10KBenchmark {
    val graph = CSRArrayType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    @Benchmark
    def findInGraph4() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRMap1K10KBenchmark {
    val graph = CSRMapType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    @Benchmark
    def findInGraph5() = {
      graph.rangeEdges(8, 974)
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class RangeCSRTree1K10KBenchmark {
    val graph = CSRTreeType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    @Benchmark
    def findInGraph6() = {
      graph.rangeEdges(8, 974)
      //      val mString = m.map {
      //        case (k, v) => s"${k} = ${v.map(_.id).mkString(",")};"
      //      }
      //      //println(s"Graph6 found  = ${mString}")
      //      val f = new File(s"findInGraph6.txt")
      //      val printStream = new PrintWriter(f, "UTF-8")
      //      try {
      //        printStream.print(mString)
      //      }
      //      finally {
      //        printStream.flush()
      //        printStream.close()
      //      }
    }
  }

}
