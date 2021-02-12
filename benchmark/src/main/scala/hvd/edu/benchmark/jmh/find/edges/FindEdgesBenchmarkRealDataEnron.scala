package hvd.edu.benchmark.jmh.find.edges

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object FindEdgesBenchmarkRealDataEnron {

  //  def randomNodes = {
  //    val randomNodesSet = mutable.Set[Int]()
  //    val random = Random
  //
  //    while (randomNodesSet.size < 100) {
  //      val nextInt = random.nextInt(403395)
  //      if (graph.findVertexById(nextInt).nonEmpty) {
  //        randomNodesSet.add(nextInt)
  //      }
  //    }
  //    println(s"Random Nodes are ${randomNodesSet.mkString(",")}")
  //    randomNodesSet.toList
  //  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALArrayEnron {

    val graph = ALArrayType.readG("realdata/email-Enron.txt", "\\t", 367666)

    val randomNodes = List(13873, 1301, 12684, 29764, 5686, 18666, 489, 20611,
      28088, 17215, 29970, 22485, 13738, 7575, 34813, 19110, 12070, 22966, 4556, 10902, 22437, 10161, 28236, 35061,
      3642, 17211, 18525, 14925, 13705, 16472, 30824, 22200, 16016, 34803, 18317, 25294, 36219, 24553, 9724, 23691,
      28940, 25869, 23662, 15525, 3859, 33173, 10938, 8710, 35228, 1814, 30073, 35555, 32819, 19425, 18663, 32282,
      18440, 28378, 30585, 2666, 3145, 16612, 1629, 19319, 15465, 11255, 1065, 7576, 25834, 34654, 1800, 5473, 32957,
      36397, 12159, 1692, 2048, 6527, 6354, 30898, 32516, 12663, 28837, 26986, 2167, 11220, 15880, 16338, 17762, 26603,
      14285, 4949, 22474, 19890, 11266, 23890, 7666, 13169, 10933, 14737)

    //    def randomNodes = {
    //      val randomNodesSet = mutable.Set[Int]()
    //      val random = Random
    //
    //      while (randomNodesSet.size < 100) {
    //        val nextInt = random.nextInt(36695)
    //        if (graph.findVertexById(nextInt).nonEmpty) {
    //          randomNodesSet.add(nextInt)
    //        }
    //      }
    //      println(s"Random Nodes are ${randomNodesSet.mkString(",")}")
    //      randomNodesSet.toList
    //    }

    @Benchmark
    def findInGraph1() = {
      val m = for (nid <- randomNodes) yield {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALMapEnron {
    val graph = ALMapType.readG("realdata/email-Enron.txt", "\\t", 367666)

    val randomNodes = List(13873, 1301, 12684, 29764, 5686, 18666, 489, 20611,
      28088, 17215, 29970, 22485, 13738, 7575, 34813, 19110, 12070, 22966, 4556, 10902, 22437, 10161, 28236, 35061,
      3642, 17211, 18525, 14925, 13705, 16472, 30824, 22200, 16016, 34803, 18317, 25294, 36219, 24553, 9724, 23691,
      28940, 25869, 23662, 15525, 3859, 33173, 10938, 8710, 35228, 1814, 30073, 35555, 32819, 19425, 18663, 32282,
      18440, 28378, 30585, 2666, 3145, 16612, 1629, 19319, 15465, 11255, 1065, 7576, 25834, 34654, 1800, 5473, 32957,
      36397, 12159, 1692, 2048, 6527, 6354, 30898, 32516, 12663, 28837, 26986, 2167, 11220, 15880, 16338, 17762, 26603,
      14285, 4949, 22474, 19890, 11266, 23890, 7666, 13169, 10933, 14737)

    @Benchmark
    def findInGraph2() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALTreeEnron {
    val graph = ALTreeType.readG("realdata/email-Enron.txt", "\\t", 367666)

    val randomNodes = List(13873, 1301, 12684, 29764, 5686, 18666, 489, 20611,
      28088, 17215, 29970, 22485, 13738, 7575, 34813, 19110, 12070, 22966, 4556, 10902, 22437, 10161, 28236, 35061,
      3642, 17211, 18525, 14925, 13705, 16472, 30824, 22200, 16016, 34803, 18317, 25294, 36219, 24553, 9724, 23691,
      28940, 25869, 23662, 15525, 3859, 33173, 10938, 8710, 35228, 1814, 30073, 35555, 32819, 19425, 18663, 32282,
      18440, 28378, 30585, 2666, 3145, 16612, 1629, 19319, 15465, 11255, 1065, 7576, 25834, 34654, 1800, 5473, 32957,
      36397, 12159, 1692, 2048, 6527, 6354, 30898, 32516, 12663, 28837, 26986, 2167, 11220, 15880, 16338, 17762, 26603,
      14285, 4949, 22474, 19890, 11266, 23890, 7666, 13169, 10933, 14737)

    @Benchmark
    def findInGraph3() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesCSRArrayEnron {
    val graph = CSRArrayType.readG("realdata/email-Enron.txt", "\\t", 367666)

    val randomNodes = List(13873, 1301, 12684, 29764, 5686, 18666, 489, 20611,
      28088, 17215, 29970, 22485, 13738, 7575, 34813, 19110, 12070, 22966, 4556, 10902, 22437, 10161, 28236, 35061,
      3642, 17211, 18525, 14925, 13705, 16472, 30824, 22200, 16016, 34803, 18317, 25294, 36219, 24553, 9724, 23691,
      28940, 25869, 23662, 15525, 3859, 33173, 10938, 8710, 35228, 1814, 30073, 35555, 32819, 19425, 18663, 32282,
      18440, 28378, 30585, 2666, 3145, 16612, 1629, 19319, 15465, 11255, 1065, 7576, 25834, 34654, 1800, 5473, 32957,
      36397, 12159, 1692, 2048, 6527, 6354, 30898, 32516, 12663, 28837, 26986, 2167, 11220, 15880, 16338, 17762, 26603,
      14285, 4949, 22474, 19890, 11266, 23890, 7666, 13169, 10933, 14737)

    @Benchmark
    def findInGraph4() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesCSRMapEnron {

    val graph = CSRMapType.readG("realdata/email-Enron.txt", "\\t", 367666)

    val randomNodes = List(13873, 1301, 12684, 29764, 5686, 18666, 489, 20611,
      28088, 17215, 29970, 22485, 13738, 7575, 34813, 19110, 12070, 22966, 4556, 10902, 22437, 10161, 28236, 35061,
      3642, 17211, 18525, 14925, 13705, 16472, 30824, 22200, 16016, 34803, 18317, 25294, 36219, 24553, 9724, 23691,
      28940, 25869, 23662, 15525, 3859, 33173, 10938, 8710, 35228, 1814, 30073, 35555, 32819, 19425, 18663, 32282,
      18440, 28378, 30585, 2666, 3145, 16612, 1629, 19319, 15465, 11255, 1065, 7576, 25834, 34654, 1800, 5473, 32957,
      36397, 12159, 1692, 2048, 6527, 6354, 30898, 32516, 12663, 28837, 26986, 2167, 11220, 15880, 16338, 17762, 26603,
      14285, 4949, 22474, 19890, 11266, 23890, 7666, 13169, 10933, 14737)

    @Benchmark
    def findInGraph5() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesCSRTreeEnron {
    val graph = CSRTreeType.readG("realdata/email-Enron.txt", "\\t", 367666)

    val randomNodes = List(13873, 1301, 12684, 29764, 5686, 18666, 489, 20611,
      28088, 17215, 29970, 22485, 13738, 7575, 34813, 19110, 12070, 22966, 4556, 10902, 22437, 10161, 28236, 35061,
      3642, 17211, 18525, 14925, 13705, 16472, 30824, 22200, 16016, 34803, 18317, 25294, 36219, 24553, 9724, 23691,
      28940, 25869, 23662, 15525, 3859, 33173, 10938, 8710, 35228, 1814, 30073, 35555, 32819, 19425, 18663, 32282,
      18440, 28378, 30585, 2666, 3145, 16612, 1629, 19319, 15465, 11255, 1065, 7576, 25834, 34654, 1800, 5473, 32957,
      36397, 12159, 1692, 2048, 6527, 6354, 30898, 32516, 12663, 28837, 26986, 2167, 11220, 15880, 16338, 17762, 26603,
      14285, 4949, 22474, 19890, 11266, 23890, 7666, 13169, 10933, 14737)

    @Benchmark
    def findInGraph6() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

}
