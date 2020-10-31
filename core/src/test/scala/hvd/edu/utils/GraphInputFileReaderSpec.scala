package hvd.edu.utils

import hvd.edu.graph.al.{ArrayALContainer, BplusTreeALContainer, DefaultALNode, HashMapALContainer, SetBasedALNode}
import hvd.edu.graph.csr.{ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer}
import org.scalatest.{FlatSpec, Matchers}

class GraphInputFileReaderSpec extends FlatSpec with Matchers {

  "Reading from a file" should "Generate valid ArrayBasedCSR Graph" in {

    val readGraph = GraphInputFileReader.readFile[CSRNode, ArrayCSRContainer](
      "facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    val edgesForNode10 = readGraph.edgesForVertexId(10)
    val actualEdge10Indices = edgesForNode10.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10Indices should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid HashMapBased CSR graph" in {

    val readGraph = GraphInputFileReader.readFile[CSRNode, HashMapCSRContainer](
      "facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    val edgesForNode10ForCSR = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesForCSR = edgesForNode10ForCSR.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesForCSR should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid BplusTre CSR graph" in {

    val readGraph = GraphInputFileReader.readFile[CSRNode, BplusTreeCSRContainer](
      "facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    val edgesForNode10ForBplusTreeCSR = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesForBplusTreeCSR = edgesForNode10ForBplusTreeCSR.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesForBplusTreeCSR should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid Adjacency List graph" in {

    val readGraph = GraphInputFileReader.readFile[SetBasedALNode, ArrayALContainer](
      "facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    val edgesForNode10AL = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesAL = edgesForNode10AL.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesAL should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid Adjacency List graph for HashMap Container" in {

    val readGraph = GraphInputFileReader.readFile[DefaultALNode, HashMapALContainer](
      "facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    val edgesForNode10ALH = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesALH = edgesForNode10ALH.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesALH.sorted should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid Adjacency List graph for BplusTreeBasedContainer" in {

    val readGraph = GraphInputFileReader.readFile[DefaultALNode, BplusTreeALContainer](
      "facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    val edgesForNode10ALBTree = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesALBTree = edgesForNode10ALBTree.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesALBTree.sorted should equal(expectedEdge10Indidces)
  }

}
