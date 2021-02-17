package hvd.edu.benchmark.jmh.find.edges.nodes10k

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object FindEdgesBenchmark10KNodes1KEdges {

  //  def genRandom()=   {
  //    val randomNodesSet = mutable.Set[Int]()
  //    val random = Random
  //
  //    while (randomNodesSet.size < 100) {
  //      randomNodesSet.add(random.nextInt(10001))
  //    }
  //    println(s"Random Nodes are ${randomNodesSet.mkString(",")}")
  //    randomNodesSet.toList
  //  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALArray10K1KBenchmark {

    val graph = ALArrayType.readG("generated/generated10k/generated10000_1000.txt", "\\t", 10488)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    @Benchmark
    def findInGraph1() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALMap10K1KBenchmark {
    val graph = ALMapType.readG("generated/generated10k/generated10000_1000.txt", "\\t", 10488)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

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
  class FindEdgesALTree10K1KBenchmark {
    val graph = ALTreeType.readG("generated/generated10k/generated10000_1000.txt", "\\t", 10488)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

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
  class FindEdgesCSRArray10K1KBenchmark {
    val graph = CSRArrayType.readG("generated/generated10k/generated10000_1000.txt", "\\t", 10488)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

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
  class FindEdgesCSRMap10K1KBenchmark {
    val graph = CSRMapType.readG("generated/generated10k/generated10000_1000.txt", "\\t", 10488)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

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
  class FindEdgesCSRTree10K1KBenchmark {
    val graph = CSRTreeType.readG("generated/generated10k/generated10000_1000.txt", "\\t", 10488)

    val randomNodes: List[Int] = List(52, 9380, 8880, 5911, 9665, 6238, 2769, 1418, 177, 3350, 7756, 3067, 7837, 2182, 6719, 6486,
      6232, 9761, 9151, 6059, 4333, 6155, 5016, 827, 654, 2963, 879, 8945, 575, 1664, 2630, 4960, 7421, 5135,
      1666, 9389, 4221, 5412, 92, 1681, 8019, 9087, 3657, 8427,
      6576, 1146, 9131, 4950, 9741, 7738, 4625, 3732, 6651, 1750, 1344, 3174, 8677, 5627, 5831, 5069, 2841,
      9818, 9789, 8896, 47, 6312, 3162, 9173, 9652, 2604, 6612, 1923, 6185, 3724, 2525, 3572, 3564, 7114, 8203, 4734, 8176, 8155, 3131, 1230,
      6610, 468, 4039, 1782, 7039, 5332, 2219, 7743, 8578, 666, 7868, 6627, 7818, 2286, 312, 254)

    @Benchmark
    def findInGraph6() = {
      for (nid <- randomNodes) {
        graph.edgesForVertexId(nid)
      }
    }
  }

}
