package hvd.edu.graph

import hvd.edu.graph.al._
import hvd.edu.utils.GraphBuilder
import org.scalatest.{ FlatSpec, Matchers }

class AdjacencyListSpec extends FlatSpec with Matchers {

  "Adjacency list" should "be generated from simple string" in {
    val inputString = "0,2 1,3 2,3 2,4 3,5 4,5"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = ArrayALContainer(arrayEdges.length)
    val nm = SetBasedALNodeMaker
    val generatedAL = GraphBuilder.buildFromString(inputString, " ", ",", gc, nm)
    generatedAL shouldNot be(null)
    //generatedAL.printGraph(None)
    generatedAL.vertexLength should be(5)
    generatedAL.edgeLength should be(6)
  }

  "it" should "generate graph where some vertices do not have outgoingedges" in {
    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = ArrayALContainer(arrayEdges.length)
    val generatedAL = GraphBuilder.buildFromString(inputString, " ", ",", gc, SetBasedALNodeMaker)
    generatedAL shouldNot be(null)
    //generatedAL.printGraph(None)
    generatedAL.vertexLength should be(5)
    generatedAL.edgeLength should be(6)

  }

  "Adjacency list using HashMap" should "be generated from simple string" in {
    val inputString = "0,2 1,3 2,3 2,4 3,5 4,5"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = HashMapALContainer(arrayEdges.length)
    val generatedAL = GraphBuilder.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    generatedAL shouldNot be(null)
    //generatedAL.printGraph(None)
    generatedAL.vertexLength should be(5)
    generatedAL.edgeLength should be(6)
  }

  "it" should "generate graph where some vertices do not have outgoingedges for HashMapBasedContainer" in {
    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = HashMapALContainer(arrayEdges.length)
    val generatedAL = GraphBuilder.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    generatedAL shouldNot be(null)
    //generatedAL.printGraph(None)
    generatedAL.vertexLength should be(5)
    generatedAL.edgeLength should be(6)

  }

  "Adjacency list using BPlusTree" should "be generated from simple string" in {
    val inputString = "0,2 1,3 2,3 2,4 3,5 4,5"
    val fanout = 5 // implicit
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = BplusTreeALContainer(arrayEdges.length, Option(fanout))
    val generatedAL = GraphBuilder.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    generatedAL shouldNot be(null)
    //generatedAL.printGraph(None)
    generatedAL.vertexLength should be(5)
    generatedAL.edgeLength should be(6)
  }

  "it" should "generate graph where some vertices do not have outgoingedges for BplusTreeBasedALContainer" in {
    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = BplusTreeALContainer(arrayEdges.length, None)
    val generatedAL = GraphBuilder.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    generatedAL shouldNot be(null)
    //generatedAL.printGraph(None)
    generatedAL.vertexLength should be(5)
    generatedAL.edgeLength should be(6)

  }

}
