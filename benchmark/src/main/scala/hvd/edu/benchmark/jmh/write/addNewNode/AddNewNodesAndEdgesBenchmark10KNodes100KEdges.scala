package hvd.edu.benchmark.jmh.write.addNewNode

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.graph.al.ALNode
import hvd.edu.graph.csr.CSRNode
import org.openjdk.jmh.annotations._

object AddNewNodesAndEdgesBenchmark10KNodes100KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class AddNewNodesALArray10K100KBenchmark {

    val graph = ALArrayType.readG("generated/generated10k/generated10000_100000.txt", "\\t", 99926)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000
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
  class AddNewNodesALMap10K100KBenchmark {
    val graph = ALMapType.readG("generated/generated10k/generated10000_100000.txt", "\\t", 99926)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000
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
  class AddNewNodesALTree10K100KBenchmark {
    val graph = ALTreeType.readG("generated/generated10k/generated10000_100000.txt", "\\t", 99926)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000
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
  class AddNewNodesCSRMap10K100KBenchmark {
    val graph = CSRMapType.readG("generated/generated10k/generated10000_100000.txt", "\\t", 99926)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    val randomNodesEdges = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000
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
  class AddNewNodesCSRTree10K100KBenchmark {
    val graph = CSRTreeType.readG("generated/generated10k/generated10000_100000.txt", "\\t", 99926)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    val randomNodesEdges = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000
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
