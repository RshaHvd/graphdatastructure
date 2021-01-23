package hvd.edu.utils

import hvd.edu.graph.{ CSRNodeMaker, DefaultALNodeMaker }
import hvd.edu.graph.al.{ ArrayALContainer, BplusTreeALContainer, HashMapALContainer }
import hvd.edu.graph.csr.{ ArrayCSRContainer, BplusTreeCSRContainer, CSRNode, HashMapCSRContainer }
import org.scalatest.{ FlatSpec, Matchers }

class GraphInputFileReaderSpec extends FlatSpec with Matchers {

  "Reading from a file" should "Generate valid ArrayBasedCSR Graph" in {

    val gc = ArrayCSRContainer(88234, 88234)
    val readGraph = GraphInputFileReader.readFile(
      "facebook_combined.txt", 88234, " ", gc, CSRNodeMaker)
    readGraph shouldNot be(null)
    val edgesForNode10 = readGraph.edgesForVertexId(10)
    val actualEdge10Indices = edgesForNode10
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10Indices should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid HashMapBased CSR graph" in {
    val gc = HashMapCSRContainer(88234, 88234)
    val readGraph = GraphInputFileReader.readFile(
      "facebook_combined.txt",
      88234, " ", gc, CSRNodeMaker)
    readGraph shouldNot be(null)
    val edgesForNode10ForCSR = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesForCSR = edgesForNode10ForCSR
    val expectedEdge10Indidces = List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesForCSR.sorted should equal(expectedEdge10Indidces.sorted)
  }

  it should "Generate a valid BplusTree CSR graph" in {
    val gc = BplusTreeCSRContainer(88234, None)
    val readGraph = GraphInputFileReader.readFile(
      "facebook_combined.txt", 88234, " ", gc, CSRNodeMaker)
    readGraph shouldNot be(null)
    val edgesForNode10ForBplusTreeCSR = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesForBplusTreeCSR = edgesForNode10ForBplusTreeCSR
    val expectedEdge10Indidces = List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesForBplusTreeCSR.sorted should equal(expectedEdge10Indidces.sorted)
  }

  it should "Generate a valid Adjacency List graph" in {
    val gc = ArrayALContainer(88234)
    val readGraph = GraphInputFileReader.readFile(
      "facebook_combined.txt", 88234, " ", gc, DefaultALNodeMaker)
    readGraph shouldNot be(null)
    val edgesForNode10AL = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesAL = edgesForNode10AL
    val expectedEdge10Indidces = List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesAL should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid Adjacency List graph for HashMap Container" in {
    val gc = HashMapALContainer(88234)
    val readGraph = GraphInputFileReader.readFile(
      "facebook_combined.txt", 88234, " ", gc, DefaultALNodeMaker)
    readGraph shouldNot be(null)
    val edgesForNode10ALH = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesALH = edgesForNode10ALH
    val expectedEdge10Indidces = List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesALH.sorted should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid Adjacency List graph for BplusTreeBasedContainer" in {
    val gc = BplusTreeALContainer(88234, None)
    val readGraph = GraphInputFileReader.readFile("facebook_combined.txt", 88234, " ",
      gc, DefaultALNodeMaker)
    readGraph shouldNot be(null)
    val edgesForNode10ALBTree = readGraph.edgesForVertexId(10)
    val actualEdge10IndicesALBTree = edgesForNode10ALBTree
    val expectedEdge10Indidces = List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10IndicesALBTree.sorted should equal(expectedEdge10Indidces)
  }

}
