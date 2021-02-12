package hvd.edu.benchmark.jmh.write.addNewNode

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.graph.al.ALNode
import hvd.edu.graph.csr.CSRNode
import org.openjdk.jmh.annotations._

object AddNewNodesAndEdgesBenchmark1KNodes10KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class AddNewNodesALArray1K10KBenchmark {

    val graph = ALArrayType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)
    val randomNodes = List(560, 539, 306, 662, 387, 510, 887, 743, 104, 787, 279, 402, 360, 250, 98, 454, 433, 27, 383, 506, 731, 956, 296, 581, 806, 298, 421, 675, 800, 779, 648, 771, 983, 873, 729, 119, 344, 90, 140, 794, 396, 875, 825, 215, 796, 165, 157, 36, 892, 719, 313, 457, 640, 9, 509, 386, 611, 938, 532, 105, 707, 940, 911, 26, 251, 303, 70, 172, 478, 326, 449, 805, 603, 197, 320,
      299, 909, 757, 37, 647, 851, 974, 780, 518, 243, 112, 722, 672, 439, 868, 339, 85, 187, 920, 666, 891, 333, 312, 762, 8)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 1000
          val vNode = ALNode(nid, nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = ALNode(eid, eid)
            (vNode, eNode)
          }
          nodesAndEdges
        }
        else {
          Nil
        }
    }.toList

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
  class AddNewNodesALMap1K10KBenchmark {
    val graph = ALMapType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    val randomNodes = List(560, 539, 306, 662, 387, 510, 887, 743, 104, 787, 279, 402, 360, 250, 98, 454, 433, 27, 383, 506, 731, 956, 296, 581, 806, 298, 421, 675, 800, 779, 648, 771, 983, 873, 729, 119, 344, 90, 140, 794, 396, 875, 825, 215, 796, 165, 157, 36, 892, 719, 313, 457, 640, 9, 509, 386, 611, 938, 532, 105, 707, 940, 911, 26, 251, 303, 70, 172, 478, 326, 449, 805, 603, 197, 320,
      299, 909, 757, 37, 647, 851, 974, 780, 518, 243, 112, 722, 672, 439, 868, 339, 85, 187, 920, 666, 891, 333, 312, 762, 8)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 1000
          val vNode = ALNode(nid, nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = ALNode(eid, eid)
            (vNode, eNode)
          }
          nodesAndEdges
        }
        else {
          Nil
        }
    }.toList

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
  class AddNewNodesALTree1K10KBenchmark {
    val graph = ALTreeType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    val randomNodes = List(560, 539, 306, 662, 387, 510, 887, 743, 104, 787, 279, 402, 360, 250, 98, 454, 433, 27, 383, 506, 731, 956, 296, 581, 806, 298, 421, 675, 800, 779, 648, 771, 983, 873, 729, 119, 344, 90, 140, 794, 396, 875, 825, 215, 796, 165, 157, 36, 892, 719, 313, 457, 640, 9, 509, 386, 611, 938, 532, 105, 707, 940, 911, 26, 251, 303, 70, 172, 478, 326, 449, 805, 603, 197, 320,
      299, 909, 757, 37, 647, 851, 974, 780, 518, 243, 112, 722, 672, 439, 868, 339, 85, 187, 920, 666, 891, 333, 312, 762, 8)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 1000
          val vNode = ALNode(nid, nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = ALNode(eid, eid)
            (vNode, eNode)
          }
          nodesAndEdges
        }
        else {
          Nil
        }
    }.toList

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
  class AddNewNodesCSRMap1K10KBenchmark {
    val graph = CSRMapType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    val randomNodes = List(560, 539, 306, 662, 387, 510, 887, 743, 104, 787, 279, 402, 360, 250, 98, 454, 433, 27, 383, 506, 731, 956, 296, 581, 806, 298, 421, 675, 800, 779, 648, 771, 983, 873, 729, 119, 344, 90, 140, 794, 396, 875, 825, 215, 796, 165, 157, 36, 892, 719, 313, 457, 640, 9, 509, 386, 611, 938, 532, 105, 707, 940, 911, 26, 251, 303, 70, 172, 478, 326, 449, 805, 603, 197, 320,
      299, 909, 757, 37, 647, 851, 974, 780, 518, 243, 112, 722, 672, 439, 868, 339, 85, 187, 920, 666, 891, 333, 312, 762, 8)

    val randomNodesEdges = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 1000
          val vNode = CSRNode(nid, nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = CSRNode(eid, eid)
            (vNode, eNode)
          }
          nodesAndEdges
        }
        else {
          Nil
        }
    }.toList

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
  class AddNewNodesCSRTree1K10KBenchmark {
    val graph = CSRTreeType.readG("generated/generated1k/generated1000_10000.txt", "\\t", 9941)

    val randomNodes = List(560, 539, 306, 662, 387, 510, 887, 743, 104, 787, 279, 402, 360, 250, 98, 454, 433, 27, 383, 506, 731, 956, 296, 581, 806, 298, 421, 675, 800, 779, 648, 771, 983, 873, 729, 119, 344, 90, 140, 794, 396, 875, 825, 215, 796, 165, 157, 36, 892, 719, 313, 457, 640, 9, 509, 386, 611, 938, 532, 105, 707, 940, 911, 26, 251, 303, 70, 172, 478, 326, 449, 805, 603, 197, 320,
      299, 909, 757, 37, 647, 851, 974, 780, 518, 243, 112, 722, 672, 439, 868, 339, 85, 187, 920, 666, 891, 333, 312, 762, 8)

    val randomNodesEdges = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 1000
          val vNode = CSRNode(nid, nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = CSRNode(eid, eid)
            (vNode, eNode)
          }
          nodesAndEdges
        }
        else {
          Nil
        }
    }.toList

    @Benchmark
    def graph6() = {
      for ((vid, eid) <- randomNodesEdges) {
        graph.addEdge(vid, eid)
      }
    }
  }

}
