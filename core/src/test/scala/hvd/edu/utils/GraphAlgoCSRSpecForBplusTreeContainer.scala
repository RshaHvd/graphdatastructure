package hvd.edu.utils

import hvd.edu.graph.csr.{BplusTreeCSRContainer, CSRNode}
import org.scalatest.{FlatSpec, Matchers}

class GraphAlgoCSRSpecForBplusTreeContainer extends FlatSpec with Matchers {

  "Depth First Search on all CSR Containers" should "yield correct nodes on sample graph 1" in {

    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val csrGraph = GraphBuilder.buildFromString[CSRNode, BplusTreeCSRContainer](inputString, " ", ",")
    csrGraph shouldNot be(null)
    csrGraph.vertexLength should be(5)
    csrGraph.edgeLength should be(6)
    val walkDFFromNode = CSRNode(10, 10)
    val actualListNodes = GraphAlgos.dfs(walkDFFromNode, csrGraph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 13, 14)
    expectedList shouldEqual actualList

  }

  it should "work on sample graph 2" in {
    val inputString =
      "10,11 10,12 10,13 11,12 11,14 12,14 13,12 13,14 14,15 15,10 15,11"
    val csrGraph = GraphBuilder.buildFromString[CSRNode, BplusTreeCSRContainer](inputString, " ", ",")
    csrGraph shouldNot be(null)
    val walkDFFromNode = CSRNode(10, 10)
    val actualListNodes = GraphAlgos.dfs(walkDFFromNode, csrGraph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 14, 15, 13)
    expectedList shouldEqual actualList
  }

  it should "produce different list from different start vertices of the same graph" in {
    val inputString = "1,2 2,3 2,4 3, 4, 5,6 6,1 6,2 6,7 7, "
    val csrGraph = GraphBuilder.buildFromString[CSRNode, BplusTreeCSRContainer](inputString, " ", ",")

    csrGraph shouldNot be(null)

    val walkDFFromNodeFrom1 = CSRNode(1, 1)
    val actualListNodesFrom1 = GraphAlgos.dfs(walkDFFromNodeFrom1, csrGraph)

    val walkDFFromNode6 = CSRNode(6, 6)
    val actualListNodes6 = GraphAlgos.dfs(walkDFFromNode6, csrGraph)

    val walkDFFromNode5 = CSRNode(5, 5)
    val actualListNodes5 = GraphAlgos.dfs(walkDFFromNode5, csrGraph)

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

  "Breath First Search on CSR" should "yield correct nodes on sample graph 1" in {

    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val csrGraph = GraphBuilder.buildFromString[CSRNode, BplusTreeCSRContainer](inputString, " ", ",")
    csrGraph shouldNot be(null)
    csrGraph.vertexLength should be(5)
    csrGraph.edgeLength should be(6)
    val walkDFFromNode = CSRNode(10, 10)
    val actualBFSListNodes = GraphAlgos.bfs(walkDFFromNode, csrGraph)

    val actualBFSList = actualBFSListNodes.map(_.id)
    val expectedBFSList = List(10, 11, 12, 13, 14)

    expectedBFSList shouldEqual actualBFSList
  }

  it should "work on sample graph 2" in {
    val inputString =
      "10,11 10,12 10,13 11,12 11,14 12,14 13,12 13,14 14,15 15,10 15,11"
    val csrGraph = GraphBuilder.buildFromString[CSRNode, BplusTreeCSRContainer](inputString, " ", ",")
    csrGraph shouldNot be(null)
    val walkDFFromNode = CSRNode(10, 10)
    val actualListNodes = GraphAlgos.bfs(walkDFFromNode, csrGraph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 13, 14, 15)
    expectedList shouldEqual actualList
  }

  it should "produce different list from different start vertices of the same graph" in {
    val inputString = "1,2 2,3 2,4 3,5 4, 5,6 6,1 6,2 6,7 7,8"
    val csrGraph = GraphBuilder.buildFromString[CSRNode, BplusTreeCSRContainer](inputString, " ", ",")
    csrGraph shouldNot be(null)
    val walkDFFromNodeFrom1 = CSRNode(1, 1)
    val actualListNodesFrom1 = GraphAlgos.bfs(walkDFFromNodeFrom1, csrGraph)

    val actualList1 = actualListNodesFrom1.map(_.id)
    val expectedList1 = List(1, 2, 3, 4, 5, 6, 7, 8)

    expectedList1 shouldEqual actualList1
  }
}
