package hvd.edu.utils

import hvd.edu.graph.Graph
import hvd.edu.graph.al.{ ArrayALContainer, SetBasedALNode }
import org.scalatest.{ FlatSpec, Matchers }

class GraphAlgoItertaiveDFSALSpec extends FlatSpec with Matchers {

  "Depth First Search on Adjacency List" should "yield correct nodes on sample graph 1" in {

    val inputString = "10,11 10,12 10,13 11,12 12,11 13,14 14"
    val graph: Graph[SetBasedALNode, ArrayALContainer] = GraphBuilder.buildFromString[SetBasedALNode, ArrayALContainer](
      inputString,
      " ",
      ","
    )
    graph shouldNot be(null)
    graph.vertexLength should be(5)
    graph.edgeLength should be(6)
    val walkDFFromNode: SetBasedALNode = SetBasedALNode(10, 10)
    val actualListNodes = GraphAlgos.dfsIterative(walkDFFromNode, graph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 13, 14)
    expectedList.sorted shouldEqual actualList.sorted

  }

  it should "work on sample graph 2" in {
    val inputString =
      "10,11 10,12 10,13 11,12 11,14 12,14 13,12 13,14 14,15 15,10 15,11"
    val graph = GraphBuilder.buildFromString[SetBasedALNode, ArrayALContainer](
      inputString,
      " ",
      ","
    )
    graph shouldNot be(null)
    val walkDFFromNode = SetBasedALNode(10, 10)
    val actualListNodes = GraphAlgos.dfsIterative(walkDFFromNode, graph)
    val actualList = actualListNodes.map(_.id)
    val expectedList = List(10, 11, 12, 14, 15, 13)
    expectedList.sorted shouldEqual actualList.sorted

  }

  it should "produce different list from different start vertices of the same graph" in {
    val inputString = "1,2 2,3 2,4 3, 4, 5,6 6,1 6,2 6,7 7, "
    val graph = GraphBuilder.buildFromString[SetBasedALNode, ArrayALContainer](
      inputString,
      " ",
      ","
    )
    graph shouldNot be(null)
    val walkDFFromNodeFrom1 = SetBasedALNode(1, 1)
    val actualListNodesFrom1 = GraphAlgos.dfsIterative(walkDFFromNodeFrom1, graph)
    val walkDFFromNode6 = SetBasedALNode(6, 6)
    val actualListNodes6 = GraphAlgos.dfs(walkDFFromNode6, graph)

    val walkDFFromNode5 = SetBasedALNode(5, 5)
    val actualListNodes5 = GraphAlgos.dfs(walkDFFromNode5, graph)

    val actualList1 = actualListNodesFrom1.map(_.id)
    val expectedList1 = List(1, 2, 3, 4)

    val actualList6 = actualListNodes6.map(_.id)
    val expectedList6 = List(6, 1, 2, 3, 4, 7)

    val actualList5 = actualListNodes5.map(_.id)
    val expectedList5 = List(5, 6, 1, 2, 3, 4, 7)

    expectedList1.sorted shouldEqual actualList1.sorted
    expectedList6.sorted shouldEqual actualList6.sorted
    expectedList5.sorted shouldEqual actualList5.sorted

  }
}
