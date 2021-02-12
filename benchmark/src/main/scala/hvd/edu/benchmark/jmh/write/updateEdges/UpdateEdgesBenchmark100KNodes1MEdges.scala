package hvd.edu.benchmark.jmh.write.updateEdges

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.graph.al.ALNode
import hvd.edu.graph.csr.CSRNode
import org.openjdk.jmh.annotations._

object UpdateEdgesBenchmark100KNodes1MEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class UpdateEdgesALArray100K1MBenchmark {

    val graph = ALArrayType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 100000
        val vNode = ALNode(nid, nid)
        val eNode = ALNode(eId, eId)
        (vNode, eNode)
    }

    @Benchmark
    def graph1() = {
      for ((vid, eid) <- randomNodesEdges) {
        graph.addEdge(vid, eid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class UpdateEdgesALMap100K1MBenchmark {

    val graph = ALMapType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 100000
        val vNode = ALNode(nid, nid)
        val eNode = ALNode(eId, eId)
        (vNode, eNode)
    }

    @Benchmark
    def graph2() = {
      for ((vid, eid) <- randomNodesEdges) {
        graph.addEdge(vid, eid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class UpdateEdgesALTree100K1MBenchmark {

    val graph = ALTreeType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 100000
        val vNode = ALNode(nid, nid)
        val eNode = ALNode(eId, eId)
        (vNode, eNode)
    }

    @Benchmark
    def graph3() = {
      for ((vid, eid) <- randomNodesEdges) {
        graph.addEdge(vid, eid)
      }
    }
  }

  //  @State(Scope.Thread)
  //  @BenchmarkMode(Array(Mode.AverageTime))
  //  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  //  class FindEdgesCSRArray1K1KBenchmark {
  //    val graph = CSRArrayType.readG("generated/generated1k/generated1000_1000.txt", "\\t", 1488)
  //    val randomNodes = List(560, 539, 306, 662, 387, 510, 887, 743, 104, 787, 279, 402, 360, 250, 98, 454, 433, 27, 383, 506, 731, 956, 296, 581, 806, 298, 421, 675, 800, 779, 648, 771, 983, 873, 729, 119, 344, 90, 140, 794, 396, 875, 825, 215, 796, 165, 157, 36, 892, 719, 313, 457, 640, 9, 509, 386, 611, 938, 532, 105, 707, 940, 911, 26, 251, 303, 70, 172, 478, 326, 449, 805, 603, 197, 320,
  //      299, 909, 757, 37, 647, 851, 974, 780, 518, 243, 112, 722, 672, 439, 868, 339, 85, 187, 920, 666, 891, 333, 312, 762, 8)
  //
  //    @Benchmark
  //    def findInGraph4() = {
  //      for (nid <- randomNodes) {
  //        nid -> graph.edgesForVertexId(nid)
  //      }
  //    }
  //  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class UpdateEdgesCSRMap100K1MBenchmark {

    val graph = CSRMapType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 100000
        val vNode = CSRNode(nid, nid)
        val eNode = CSRNode(eId, eId)
        (vNode, eNode)
    }

    @Benchmark
    def graph5() = {
      for ((vid, eid) <- randomNodesEdges) {
        graph.addEdge(vid, eid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class UpdateEdgesCSRTree100K1MBenchmark {

    val graph = CSRTreeType.readG("generated/generated100k/generated100000_1000000.txt", "\\t", 999942)

    val randomNodes = List(99794, 15116, 14862, 91730, 90489, 53271, 43181, 85750, 12301, 16482, 85619, 10340, 59734, 66224, 43989, 83050,
      77170, 83995, 84241, 43025, 51947, 35592, 11629, 75548, 49632, 16595, 64086, 79273, 45626, 1685, 1279, 90679,
      87231, 68444, 4954, 88414, 69527, 92445, 117, 88439, 92599, 98987, 85470, 36586, 73275, 95612, 21401, 69981,
      43332, 70104, 35826, 38664, 69931, 27058, 41025, 41381, 77735, 19502, 28220, 85133, 93809, 7474, 44113, 45456,
      12652, 88075, 57703, 62944, 17720, 48042, 27404, 2119, 92483, 89891, 318, 2506, 70496, 61830, 19232, 29497,
      10710, 49352, 42202, 55334, 76074, 83551, 97162,
      41890, 56577, 24650, 57056, 12840, 99646, 95, 87430, 50356, 15722, 27215, 96454, 38598)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 100000
        val vNode = CSRNode(nid, nid)
        val eNode = CSRNode(eId, eId)
        (vNode, eNode)
    }

    @Benchmark
    def graph6() = {
      for ((vid, eid) <- randomNodesEdges) {
        graph.addEdge(vid, eid)
      }
    }
  }

}
