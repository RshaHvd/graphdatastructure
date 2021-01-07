package hvd.edu.benchmark.jmh

import java.util.concurrent.TimeUnit

import hvd.edu.graph.al.{ ArrayALContainer, SetBasedALNode }
import hvd.edu.graph.{ Graph, SetBasedALNodeMaker }
import hvd.edu.utils.GraphBuilder
import org.openjdk.jmh.annotations._

object ParamTester {

  val randomNodes: List[Int] = List(2)

  @State(Scope.Thread)
  @BenchmarkMode(Array(Mode.AverageTime))
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  class BenchmarkTester {

    var graph: Graph[SetBasedALNode] = _

    //    @Param(Array("0,2 1,3 2,3 2,4 3,5 4,5",
    //      "10,11 10,12 10,13 11,12 12,11 13,14 14"))

    @Param(Array(
      "0,2 1,3 2,3 2,4 3,5 4,5"))
    var inputStr: String = _

    @Setup
    def loadGraph = {
      val edges = inputStr.split(" ")
      val gc = ArrayALContainer(edges.length)
      graph = GraphBuilder.buildFromString(inputStr, " ", ",", gc, SetBasedALNodeMaker)
    }

    @Benchmark
    def findInGraph() = {
      println(s"Graph Length : ${graph.edgeLength}")
      for (n <- randomNodes) {
        val nn = graph.edgesForVertexId(n)
        println(s"edges for ${n} are ${nn.map(_.id).mkString(",")}")
      }
    }

  }

}

//  @Benchmark
//  def loadALArrayInputString1() = {
//    val generatedAL = GraphBuilder.buildFromString[SetBasedALNode, ArrayALContainer](inputString1, " ", ",", A)
//  }
//
//  @Benchmark
//  def loadALArrayInputString2() = {
//    val generatedAL = GraphBuilder.buildFromString[SetBasedALNode, ArrayALContainer](inputString2, " ", ",")
//  }
//
//  @Benchmark
//  def loadALHashMapInputString1() = {
//    val generatedAL = GraphBuilder.buildFromString[DefaultALNode, HashMapALContainer](inputString1, " ", ",")
//  }
//
//  @Benchmark
//  def loadALHashMapInputString2() = {
//    val generatedAL = GraphBuilder.buildFromString[DefaultALNode, HashMapALContainer](inputString2, " ", ",")
//  }
//
//  @Benchmark
//  def loadALBTreeInputString1() = {
//    val fanout = 5 // implicit
//    val generatedAL = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString1, " ", ",")
//  }
//
//  @Benchmark
//  def loadALBTreeInputString2() = {
//    val fanout = 5 // implicit
//    val generatedAL = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString2, " ", ",")
//  }

