package hvd.edu.utils

import hvd.edu.graph.DefaultALNodeMaker
import hvd.edu.graph.al.{ ArrayALContainer, ALNode }
import hvd.edu.graph.csr.BplusTreeCSRContainer
import org.scalatest.{ FlatSpec, Matchers }

class GraphAlgoItertaiveBFSALSpec extends FlatSpec with Matchers {

  "Breath First Search on Adjacency List" should "yield correct nodes on sample graph 1" in {

    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = ArrayALContainer(arrayEdges.length)
    val graph = StringGraphEngine.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    graph shouldNot be(null)
    graph.vertexLength should be(5)
    graph.edgeLength should be(6)
    val walkDFFromNode = ALNode(10, 10)
    val actualBFSListNodes = GraphAlgos.bfsIterative(walkDFFromNode, graph)

    val actualBFSList = actualBFSListNodes
    val expectedBFSList = List(10, 11, 12, 13, 14)

    expectedBFSList shouldEqual actualBFSList
  }

  it should "work on sample graph 2" in {
    val inputString = "10,11 10,12 10,13 11,12 11,14 12,14 13,12 13,14 14,15 15,10 15,11"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = ArrayALContainer(arrayEdges.length)
    val graph = StringGraphEngine.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    graph shouldNot be(null)
    val walkDFFromNode = ALNode(10, 10)
    val actualListNodes = GraphAlgos.bfsIterative(walkDFFromNode, graph)
    val actualList = actualListNodes
    val expectedList = List(10, 11, 12, 13, 14, 15)
    expectedList shouldEqual actualList

  }

  it should "produce different list from different start vertices of the same graph" in {
    val inputString = "1,2 2,3 2,4 3,5 4, 5,6 6,1 6,2 6,7 7,8"
    val arrayEdges: Array[String] = inputString.split(" ")
    val gc = ArrayALContainer(arrayEdges.length)
    val graph = StringGraphEngine.buildFromString(inputString, " ", ",", gc, DefaultALNodeMaker)
    graph shouldNot be(null)
    val walkDFFromNodeFrom1 = ALNode(1, 1)
    val actualListNodesFrom1 = GraphAlgos.bfsIterative(walkDFFromNodeFrom1, graph)

    val walkDFFromNode6 = ALNode(6, 6)
    val actualListNodes6 = GraphAlgos.bfsIterative(walkDFFromNode6, graph)

    val walkDFFromNode5 = ALNode(5, 5)
    val actualListNodes5 = GraphAlgos.bfsIterative(walkDFFromNode5, graph)

    val actualList1 = actualListNodesFrom1
    val expectedList1 = List(1, 2, 3, 4, 5, 6, 7, 8)

    val actualList6 = actualListNodes6
    val expectedList6 = List(6, 1, 2, 7, 3, 4, 8, 5)

    val actualList5 = actualListNodes5
    val expectedList5 = List(5, 6, 1, 2, 7, 3, 4, 8)

    expectedList1 shouldEqual actualList1
    expectedList6 shouldEqual actualList6
    expectedList5 shouldEqual actualList5

  }

}
