package hvd.edu.benchmark.jmh

import java.io.{ File, PrintWriter }

import scala.util.Random
import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

import scala.collection.mutable

object FindEdgesBenchmark100KNodes1MEdges {

  //  def genRandom() =    {
  //      val randomNodesSet = mutable.Set[Int]()
  //      val random = Random
  //
  //      while (randomNodesSet.size < 100) {
  //        randomNodesSet.add(random.nextInt(100001))
  //      }
  //      println(s"Random Nodes are ${randomNodesSet.mkString(",")}")
  //      randomNodesSet.toList
  //    }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALArray100K1MBenchmark {

    val graph = ALArrayType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    @Benchmark
    def findInGraph1() = {
      for (nid <- randomNodes) yield {
        graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALMap100K1MBenchmark {
    val graph = ALMapType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    @Benchmark
    def findInGraph2() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALTree100K1MBenchmark {
    val graph = ALTreeType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    @Benchmark
    def findInGraph3() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesCSRArray100K1MBenchmark {
    val graph = CSRArrayType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    @Benchmark
    def findInGraph4() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesCSRMap100K1MBenchmark {
    val graph = CSRMapType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    @Benchmark
    def findInGraph5() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesCSRTree100K1MBenchmark {
    val graph = CSRTreeType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    @Benchmark
    def findInGraph6() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
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