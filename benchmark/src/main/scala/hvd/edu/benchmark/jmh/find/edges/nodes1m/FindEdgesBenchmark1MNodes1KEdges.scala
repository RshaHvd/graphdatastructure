package hvd.edu.benchmark.jmh.find.edges.nodes1m

import java.util.concurrent.TimeUnit

import hvd.edu.benchmark.workload.GraphTypes._
import org.openjdk.jmh.annotations._

object FindEdgesBenchmark1MNodes1KEdges {

  //  def genRandom() = {
  //    val randomNodesSet = mutable.Set[Int]()
  //    val random = Random
  //
  //    while (randomNodesSet.size < 100) {
  //      randomNodesSet.add(random.nextInt(1000001))
  //    }
  //    println(s"Random Nodes are ${randomNodesSet.mkString(",")}")
  //    randomNodesSet.toList
  //  }

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class FindEdgesALArray1M1KBenchmark {

    val graph = ALArrayType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

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
  class FindEdgesALMap1M1KBenchmark {
    val graph = ALMapType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

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
  class FindEdgesALTree1M1KBenchmark {
    val graph = ALTreeType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

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
  class FindEdgesCSRArray1M1KBenchmark {
    val graph = CSRArrayType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

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
  class FindEdgesCSRMap1M1KBenchmark {
    val graph = CSRMapType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

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
  class FindEdgesCSRTree1M1KBenchmark {
    val graph = CSRTreeType.readG("generated/generated1000k/generated1000000_1000.txt", "\\t", 1000486)

    val randomNodes = List(67244, 630867, 190935, 662199, 288289, 169006, 311440, 510023, 645438, 440226, 576963, 859239,
      1870, 599373, 118265, 777674, 210574, 39090, 480663, 458428, 813674, 842734, 298, 891306, 150738,
      734114, 683612, 585365, 682596, 225503, 679556, 98693, 560904, 71180, 489806, 97088, 985025, 248473,
      883621, 966929, 944642, 153807, 186357, 329147, 1537, 490441, 928820, 599199, 70872, 389175, 460388,
      978300, 828970, 186041, 597140, 100732, 401316, 734958, 546203, 438730, 711157, 552999, 413738,
      648125, 841163, 788323, 871079, 928225, 225943, 124735, 305976, 108757, 282733, 710391, 628491,
      741642, 389336, 103918, 433989, 639905, 892346, 470060, 819274, 318015, 287418, 718189, 900629,
      770188, 61091, 375438, 720369, 985230, 776976, 104822, 925771, 521845, 509150, 817994, 15745, 497963)

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