package hvd.edu.benchmark.jmh.write.addNewNode.nodes10m

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.graph.al.ALNode
import hvd.edu.graph.csr.CSRNode
import org.openjdk.jmh.annotations._

object AddNewNodesAndEdgesBenchmark10MNodes100KEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class AddNewNodesALArray10M100KBenchmark {

    val graph = ALArrayType.readG("generated/generated10000k/generated10000000_100000.txt", "\\t", 10050008)

    val randomNodes = List(2777282, 1311922, 7944809, 728975, 3780598, 3084052, 4357353, 672035, 8371574, 3149248,
      7484445, 2454778, 2587609, 9533835, 202, 7332046, 3572088, 7845086, 11933,
      2252487, 2432335, 9440475, 5097934, 1411192, 5807083, 5729068, 1340256, 934906, 7076632,
      5820086, 2869440, 5354115, 9812624, 2724343, 4324733, 8847740, 2219869, 5770515, 4914743,
      3984522, 838689, 8100, 7778454, 9547461, 6989816, 6760547, 8099924, 4573772, 9129685, 849791,
      5971382, 5553989, 3182240, 299904, 6061434, 8121799, 1680324, 6615192, 8036511, 8021418, 3419184,
      9040793, 4288030, 2292644, 2747095, 8278856, 7251019, 3815252, 3385947, 9634203, 7490545, 5350272,
      5392760, 1019206, 6050093, 6277482, 4771972, 8160025, 2089889, 3725667, 3572868, 8706531, 8305472,
      6385407, 1557119, 8745927, 8139910, 8322002,
      5613663, 7621521, 5066714, 9013091, 5572533, 6570833, 7142522, 4068766, 8987962, 8147639, 3728293, 5950141)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000000
          val vNode = ALNode(nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = ALNode(eid)
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
  class AddNewNodesALMap10M100KBenchmark {
    val graph = ALMapType.readG("generated/generated10000k/generated10000000_100000.txt", "\\t", 10050008)

    val randomNodes = List(2777282, 1311922, 7944809, 728975, 3780598, 3084052, 4357353, 672035, 8371574, 3149248,
      7484445, 2454778, 2587609, 9533835, 202, 7332046, 3572088, 7845086, 11933,
      2252487, 2432335, 9440475, 5097934, 1411192, 5807083, 5729068, 1340256, 934906, 7076632,
      5820086, 2869440, 5354115, 9812624, 2724343, 4324733, 8847740, 2219869, 5770515, 4914743,
      3984522, 838689, 8100, 7778454, 9547461, 6989816, 6760547, 8099924, 4573772, 9129685, 849791,
      5971382, 5553989, 3182240, 299904, 6061434, 8121799, 1680324, 6615192, 8036511, 8021418, 3419184,
      9040793, 4288030, 2292644, 2747095, 8278856, 7251019, 3815252, 3385947, 9634203, 7490545, 5350272,
      5392760, 1019206, 6050093, 6277482, 4771972, 8160025, 2089889, 3725667, 3572868, 8706531, 8305472,
      6385407, 1557119, 8745927, 8139910, 8322002,
      5613663, 7621521, 5066714, 9013091, 5572533, 6570833, 7142522, 4068766, 8987962, 8147639, 3728293, 5950141)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000000
          val vNode = ALNode(nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = ALNode(eid)
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
  class AddNewNodesALTree10M100KBenchmark {
    val graph = ALTreeType.readG("generated/generated10000k/generated10000000_100000.txt", "\\t", 10050008)

    val randomNodes = List(2777282, 1311922, 7944809, 728975, 3780598, 3084052, 4357353, 672035, 8371574, 3149248,
      7484445, 2454778, 2587609, 9533835, 202, 7332046, 3572088, 7845086, 11933,
      2252487, 2432335, 9440475, 5097934, 1411192, 5807083, 5729068, 1340256, 934906, 7076632,
      5820086, 2869440, 5354115, 9812624, 2724343, 4324733, 8847740, 2219869, 5770515, 4914743,
      3984522, 838689, 8100, 7778454, 9547461, 6989816, 6760547, 8099924, 4573772, 9129685, 849791,
      5971382, 5553989, 3182240, 299904, 6061434, 8121799, 1680324, 6615192, 8036511, 8021418, 3419184,
      9040793, 4288030, 2292644, 2747095, 8278856, 7251019, 3815252, 3385947, 9634203, 7490545, 5350272,
      5392760, 1019206, 6050093, 6277482, 4771972, 8160025, 2089889, 3725667, 3572868, 8706531, 8305472,
      6385407, 1557119, 8745927, 8139910, 8322002,
      5613663, 7621521, 5066714, 9013091, 5572533, 6570833, 7142522, 4068766, 8987962, 8147639, 3728293, 5950141)

    val randomNodesEdges: List[(ALNode, ALNode)] = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000000
          val vNode = ALNode(nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = ALNode(eid)
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
  class AddNewNodesCSRMap10M100KBenchmark {
    val graph = CSRMapType.readG("generated/generated10000k/generated10000000_100000.txt", "\\t", 10050008)

    val randomNodes = List(2777282, 1311922, 7944809, 728975, 3780598, 3084052, 4357353, 672035, 8371574, 3149248,
      7484445, 2454778, 2587609, 9533835, 202, 7332046, 3572088, 7845086, 11933,
      2252487, 2432335, 9440475, 5097934, 1411192, 5807083, 5729068, 1340256, 934906, 7076632,
      5820086, 2869440, 5354115, 9812624, 2724343, 4324733, 8847740, 2219869, 5770515, 4914743,
      3984522, 838689, 8100, 7778454, 9547461, 6989816, 6760547, 8099924, 4573772, 9129685, 849791,
      5971382, 5553989, 3182240, 299904, 6061434, 8121799, 1680324, 6615192, 8036511, 8021418, 3419184,
      9040793, 4288030, 2292644, 2747095, 8278856, 7251019, 3815252, 3385947, 9634203, 7490545, 5350272,
      5392760, 1019206, 6050093, 6277482, 4771972, 8160025, 2089889, 3725667, 3572868, 8706531, 8305472,
      6385407, 1557119, 8745927, 8139910, 8322002,
      5613663, 7621521, 5066714, 9013091, 5572533, 6570833, 7142522, 4068766, 8987962, 8147639, 3728293, 5950141)

    val randomNodesEdges = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000000
          val vNode = CSRNode(nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = CSRNode(eid)
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
  class AddNewNodesCSRTree10M100KBenchmark {
    val graph = CSRTreeType.readG("generated/generated10000k/generated10000000_100000.txt", "\\t", 10050008)

    val randomNodes = List(2777282, 1311922, 7944809, 728975, 3780598, 3084052, 4357353, 672035, 8371574, 3149248,
      7484445, 2454778, 2587609, 9533835, 202, 7332046, 3572088, 7845086, 11933,
      2252487, 2432335, 9440475, 5097934, 1411192, 5807083, 5729068, 1340256, 934906, 7076632,
      5820086, 2869440, 5354115, 9812624, 2724343, 4324733, 8847740, 2219869, 5770515, 4914743,
      3984522, 838689, 8100, 7778454, 9547461, 6989816, 6760547, 8099924, 4573772, 9129685, 849791,
      5971382, 5553989, 3182240, 299904, 6061434, 8121799, 1680324, 6615192, 8036511, 8021418, 3419184,
      9040793, 4288030, 2292644, 2747095, 8278856, 7251019, 3815252, 3385947, 9634203, 7490545, 5350272,
      5392760, 1019206, 6050093, 6277482, 4771972, 8160025, 2089889, 3725667, 3572868, 8706531, 8305472,
      6385407, 1557119, 8745927, 8139910, 8322002,
      5613663, 7621521, 5066714, 9013091, 5572533, 6570833, 7142522, 4068766, 8987962, 8147639, 3728293, 5950141)

    val randomNodesEdges = randomNodes.grouped(5).flatMap {
      ids: List[Int] =>
        if (ids.size > 1) {
          val nid = ids.head + 10000000
          val vNode = CSRNode(nid)
          val nodesAndEdges = ids.tail.map { eid =>
            val eNode = CSRNode(eid)
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
