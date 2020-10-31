package hvd.edu.utils

import hvd.edu.graph.al.{BplusTreeALContainer, DefaultALNode}
import org.scalatest.{FlatSpec, Matchers}

class GraphAlgoALSpecForBPlusTreeBasedALContainer extends FlatSpec with Matchers {

  "Depth First Search on AL based on BplusTreeContainer" should "yield correct nodes on sample graph 1" in {
    val fanout = 5
    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val graph = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString, " ", ",")
    graph shouldNot be(null)
    graph.vertexLength should be(5)
    graph.edgeLength should be(6)
    val walkDFFromNode = DefaultALNode(10, 10)
    val actualListNodes = GraphAlgos.dfs(walkDFFromNode, graph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 13, 14)
    expectedList shouldEqual actualList

  }

  it should "work on sample graph 2" in {
    val fanout = 5
    val inputString =
      "10,11 10,12 10,13 11,12 11,14 12,14 13,12 13,14 14,15 15,10 15,11"
    val graph = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString, " ", ",")
    graph shouldNot be(null)
    val walkDFFromNode = DefaultALNode(10, 10)
    val actualListNodes = GraphAlgos.dfs(walkDFFromNode, graph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 14, 15, 13)
    expectedList shouldEqual actualList

  }

  it should "produce different list from different start vertices of the same graph" in {
    val fanout = 5
    val inputString = "1,2 2,3 2,4 3, 4, 5,6 6,1 6,2 6,7 7, "
    val graph = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString, " ", ",")
    graph shouldNot be(null)
    val walkDFFromNodeFrom1 = DefaultALNode(1, 1)
    val actualListNodesFrom1 = GraphAlgos.dfs(walkDFFromNodeFrom1, graph)
    val walkDFFromNode6 = DefaultALNode(6, 6)
    val actualListNodes6 = GraphAlgos.dfs(walkDFFromNode6, graph)

    val walkDFFromNode5 = DefaultALNode(5, 5)
    val actualListNodes5 = GraphAlgos.dfs(walkDFFromNode5, graph)

    val actualList1 = actualListNodesFrom1.map(_.id)
    val expectedList1 = List(1, 2, 3, 4)

    val actualList6 = actualListNodes6.map(_.id)
    val expectedList6 = List(6, 1, 2, 3, 4, 7)

    val actualList5 = actualListNodes5.map(_.id)
    val expectedList5 = List(5, 6, 1, 2, 3, 4, 7)

    expectedList1 shouldEqual actualList1
    expectedList6 shouldEqual actualList6
    expectedList5 shouldEqual actualList5

  }

  "Breath First Search on AL Using BPLusTreeContainer" should "yield correct nodes on sample graph 1" in {
    val fanout = 5
    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val graph = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString, " ", ",")
    graph shouldNot be(null)
    graph.vertexLength should be(5)
    graph.edgeLength should be(6)
    val walkDFFromNode = DefaultALNode(10, 10)
    val actualBFSListNodes = GraphAlgos.bfs(walkDFFromNode, graph)

    val actualBFSList = actualBFSListNodes.map(_.id)
    val expectedBFSList = List(10, 11, 12, 13, 14)

    expectedBFSList.sorted shouldEqual actualBFSList.sorted
  }

  it should "work on sample graph 2" in {
    val fanout = 5
    val inputString =
      "10,11 10,12 10,13 11,12 11,14 12,14 13,12 13,14 14,15 15,10 15,11"
    val graph = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString, " ", ",")
    graph shouldNot be(null)
    val walkDFFromNode = DefaultALNode(10, 10)
    val actualListNodes = GraphAlgos.bfs(walkDFFromNode, graph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 13, 14, 15)
    expectedList shouldEqual actualList

  }

  it should "produce different list from different start vertices of the same graph" in {
    val fanout = 5
    val inputString = "1,2 2,3 2,4 3,5 4, 5,6 6,1 6,2 6,7 7,8"
    val graph = GraphBuilder.buildFromString[DefaultALNode, BplusTreeALContainer](inputString, " ", ",")
    graph shouldNot be(null)
    val walkDFFromNodeFrom1 = DefaultALNode(1, 1)
    val actualListNodesFrom1 = GraphAlgos.bfs(walkDFFromNodeFrom1, graph)

    val walkDFFromNode6 = DefaultALNode(6, 6)
    val actualListNodes6 = GraphAlgos.bfs(walkDFFromNode6, graph)

    val walkDFFromNode5 = DefaultALNode(5, 5)
    val actualListNodes5 = GraphAlgos.bfs(walkDFFromNode5, graph)

    val actualList1 = actualListNodesFrom1.map(_.id)
    val expectedList1 = List(1, 2, 3, 4, 5, 6, 7, 8)

    val actualList6 = actualListNodes6.map(_.id)
    val expectedList6 = List(6, 1, 2, 7, 3, 4, 8, 5)

    val actualList5 = actualListNodes5.map(_.id)
    val expectedList5 = List(5, 6, 1, 2, 7, 3, 4, 8)

    expectedList1 shouldEqual actualList1
    expectedList6 shouldEqual actualList6
    expectedList5 shouldEqual actualList5

  }

}
