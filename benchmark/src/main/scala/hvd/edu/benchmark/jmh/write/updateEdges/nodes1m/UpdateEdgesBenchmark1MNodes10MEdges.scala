package hvd.edu.benchmark.jmh.write.updateEdges.nodes1m

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import hvd.edu.graph.al.ALNode
import hvd.edu.graph.csr.CSRNode
import org.openjdk.jmh.annotations._

object UpdateEdgesBenchmark1MNodes10MEdges {

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class UpdateEdgesALArray1M10MBenchmark {

    val graph = ALArrayType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 1000000
        val vNode = ALNode(nid)
        val eNode = ALNode(eId)
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
  class UpdateEdgesALMap1M10MBenchmark {
    val graph = ALMapType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 1000000
        val vNode = ALNode(nid)
        val eNode = ALNode(eId)
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
  class UpdateEdgesALTree1M10MBenchmark {
    val graph = ALTreeType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 1000000
        val vNode = ALNode(nid)
        val eNode = ALNode(eId)
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
  class UpdateEdgesCSRMap1M10MBenchmark {
    val graph = CSRMapType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 1000000
        val vNode = CSRNode(nid)
        val eNode = CSRNode(eId)
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
  class UpdateEdgesCSRTree1M10MBenchmark {
    val graph = CSRTreeType.readG("generated/generated1000k/generated1000000_10000000.txt", "\\t", 10000001)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

    val randomNodesEdges = randomNodes.map {
      nid =>
        val eId = nid + 1000000
        val vNode = CSRNode(nid)
        val eNode = CSRNode(eId)
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
