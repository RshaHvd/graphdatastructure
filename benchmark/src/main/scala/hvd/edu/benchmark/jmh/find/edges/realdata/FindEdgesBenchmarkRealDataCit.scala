package hvd.edu.benchmark.jmh.find.edges.realdata

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object FindEdgesBenchmarkRealDataCit {

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
  class FindEdgesALArrayCit {

    val graph = ALArrayType.readG("realdata/cit-HepTh.txt", "\\t", 352811)

    val randomNodes = List(9184, 3296, 11158, 9032, 9134, 10071, 12016, 11254, 5222, 6057, 9099, 1216, 11235, 8135, 12062, 4077, 9101,
      12164, 7250, 3040, 12166, 2076, 4173, 12218, 4123, 7063, 11100, 12066, 9118, 6157, 4044, 8175, 8154, 3211,
      4177, 3182, 6101, 12185, 9085, 4142, 9035, 3257, 6045, 7011, 6147, 5050, 11040, 5233, 1052, 8042, 7178, 11157,
      9162, 12102, 6091, 8146, 10091, 1098, 9083, 5279, 4132, 4111, 2006, 9004, 6166, 7103, 10043, 2181, 10247,
      11205, 11024, 12011, 10110, 3112, 11099, 12065, 4203, 2229, 5009, 12109, 2223, 10056, 12009, 8082, 2144,
      12061, 3154, 3133, 1049, 2015, 2138, 1122, 10052, 8172, 3106, 8072, 7208, 7179, 3100, 4189)

    //    def randomNodes = {
    //      val randomNodesSet = mutable.Set[Int]()
    //      val random = Random
    //
    //      while (randomNodesSet.size < 100) {
    //        val nextInt = random.nextInt(27775)
    //        if (graph.findVertexById(nextInt).nonEmpty) {
    //          randomNodesSet.add(nextInt)
    //        }
    //      }
    //      println(s"Random Nodes are ${randomNodesSet.mkString(",")}")
    //      randomNodesSet.toList
    //    }

    @Benchmark
    def findInGraph1() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALMapCit {
    val graph = ALMapType.readG("realdata/cit-HepTh.txt", "\\t", 352811)

    val randomNodes = List(9184, 3296, 11158, 9032, 9134, 10071, 12016, 11254, 5222, 6057, 9099, 1216, 11235, 8135, 12062, 4077, 9101,
      12164, 7250, 3040, 12166, 2076, 4173, 12218, 4123, 7063, 11100, 12066, 9118, 6157, 4044, 8175, 8154, 3211,
      4177, 3182, 6101, 12185, 9085, 4142, 9035, 3257, 6045, 7011, 6147, 5050, 11040, 5233, 1052, 8042, 7178, 11157,
      9162, 12102, 6091, 8146, 10091, 1098, 9083, 5279, 4132, 4111, 2006, 9004, 6166, 7103, 10043, 2181, 10247,
      11205, 11024, 12011, 10110, 3112, 11099, 12065, 4203, 2229, 5009, 12109, 2223, 10056, 12009, 8082, 2144,
      12061, 3154, 3133, 1049, 2015, 2138, 1122, 10052, 8172, 3106, 8072, 7208, 7179, 3100, 4189)

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
  class FindEdgesALTreeCit {
    val graph = ALTreeType.readG("realdata/cit-HepTh.txt", "\\t", 352811)

    val randomNodes = List(9184, 3296, 11158, 9032, 9134, 10071, 12016, 11254, 5222, 6057, 9099, 1216, 11235, 8135, 12062, 4077, 9101,
      12164, 7250, 3040, 12166, 2076, 4173, 12218, 4123, 7063, 11100, 12066, 9118, 6157, 4044, 8175, 8154, 3211,
      4177, 3182, 6101, 12185, 9085, 4142, 9035, 3257, 6045, 7011, 6147, 5050, 11040, 5233, 1052, 8042, 7178, 11157,
      9162, 12102, 6091, 8146, 10091, 1098, 9083, 5279, 4132, 4111, 2006, 9004, 6166, 7103, 10043, 2181, 10247,
      11205, 11024, 12011, 10110, 3112, 11099, 12065, 4203, 2229, 5009, 12109, 2223, 10056, 12009, 8082, 2144,
      12061, 3154, 3133, 1049, 2015, 2138, 1122, 10052, 8172, 3106, 8072, 7208, 7179, 3100, 4189)

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
  class FindEdgesCSRArrayCit {
    val graph = CSRArrayType.readG("realdata/cit-HepTh.txt", "\\t", 352811)

    val randomNodes = List(9184, 3296, 11158, 9032, 9134, 10071, 12016, 11254, 5222, 6057, 9099, 1216, 11235, 8135, 12062, 4077, 9101,
      12164, 7250, 3040, 12166, 2076, 4173, 12218, 4123, 7063, 11100, 12066, 9118, 6157, 4044, 8175, 8154, 3211,
      4177, 3182, 6101, 12185, 9085, 4142, 9035, 3257, 6045, 7011, 6147, 5050, 11040, 5233, 1052, 8042, 7178, 11157,
      9162, 12102, 6091, 8146, 10091, 1098, 9083, 5279, 4132, 4111, 2006, 9004, 6166, 7103, 10043, 2181, 10247,
      11205, 11024, 12011, 10110, 3112, 11099, 12065, 4203, 2229, 5009, 12109, 2223, 10056, 12009, 8082, 2144,
      12061, 3154, 3133, 1049, 2015, 2138, 1122, 10052, 8172, 3106, 8072, 7208, 7179, 3100, 4189)

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
  class FindEdgesCSRMapCit {

    val graph = CSRMapType.readG("realdata/cit-HepTh.txt", "\\t", 352811)

    val randomNodes = List(9184, 3296, 11158, 9032, 9134, 10071, 12016, 11254, 5222, 6057, 9099, 1216, 11235, 8135, 12062, 4077, 9101,
      12164, 7250, 3040, 12166, 2076, 4173, 12218, 4123, 7063, 11100, 12066, 9118, 6157, 4044, 8175, 8154, 3211,
      4177, 3182, 6101, 12185, 9085, 4142, 9035, 3257, 6045, 7011, 6147, 5050, 11040, 5233, 1052, 8042, 7178, 11157,
      9162, 12102, 6091, 8146, 10091, 1098, 9083, 5279, 4132, 4111, 2006, 9004, 6166, 7103, 10043, 2181, 10247,
      11205, 11024, 12011, 10110, 3112, 11099, 12065, 4203, 2229, 5009, 12109, 2223, 10056, 12009, 8082, 2144,
      12061, 3154, 3133, 1049, 2015, 2138, 1122, 10052, 8172, 3106, 8072, 7208, 7179, 3100, 4189)

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
  class FindEdgesCSRTreeCit {
    val graph = CSRTreeType.readG("realdata/cit-HepTh.txt", "\\t", 352811)

    val randomNodes = List(9184, 3296, 11158, 9032, 9134, 10071, 12016, 11254, 5222, 6057, 9099, 1216, 11235, 8135, 12062, 4077, 9101,
      12164, 7250, 3040, 12166, 2076, 4173, 12218, 4123, 7063, 11100, 12066, 9118, 6157, 4044, 8175, 8154, 3211,
      4177, 3182, 6101, 12185, 9085, 4142, 9035, 3257, 6045, 7011, 6147, 5050, 11040, 5233, 1052, 8042, 7178, 11157,
      9162, 12102, 6091, 8146, 10091, 1098, 9083, 5279, 4132, 4111, 2006, 9004, 6166, 7103, 10043, 2181, 10247,
      11205, 11024, 12011, 10110, 3112, 11099, 12065, 4203, 2229, 5009, 12109, 2223, 10056, 12009, 8082, 2144,
      12061, 3154, 3133, 1049, 2015, 2138, 1122, 10052, 8172, 3106, 8072, 7208, 7179, 3100, 4189)

    @Benchmark
    def findInGraph6() = {
      for (nid <- randomNodes) {
        nid -> graph.edgesForVertexId(nid)
      }
    }
  }

}
