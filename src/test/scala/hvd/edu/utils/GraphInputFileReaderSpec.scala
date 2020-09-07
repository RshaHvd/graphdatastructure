package hvd.edu.utils

import hvd.edu.graph.al.{ALNodeWithSetBasedEdges, ArrayBasedALContainer}
import hvd.edu.graph.csr.{ArrayBasedCSRContainer, CSRNode, HashMapBasedCSRContainer}
import org.scalatest.{FlatSpec, Matchers}

class GraphInputFileReaderSpec extends FlatSpec with Matchers {

  "Reading from a file" should "Generate valid ArrayBasedCSR Graph" in {

    val readGraph = GraphInputFileReader.readFile[CSRNode, ArrayBasedCSRContainer](
      "/Users/rsha/harvardcourses/thesis/project/graphdatastructures/src/test/resources/facebook_combined.txt",
      88234,
      " "
    )
    readGraph shouldNot be(null)
    // readGraph.printGraph(Option(10))
    val edgesForNode10 = readGraph.edgesForVertexId(10)
    val actualEdge10Indices = edgesForNode10.map(_.id)
    val expectedEdge10Indidces =
      List(67, 142, 169, 200, 277, 285, 291, 323, 332)
    actualEdge10Indices should equal(expectedEdge10Indidces)
  }

  it should "Generate a valid HashMapBased CSR  graph" in {

    val readGraph = GraphInputFileReader.readFile[CSRNode, HashMapBasedCSRContainer](
      "/Users/rsha/harvardcourses/thesis/project/graphdatastructures/src/test/resources/facebook_combined.txt",
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

  it should "Generate a valid Adjacency List graph" in {

    val readGraph = GraphInputFileReader.readFile[ALNodeWithSetBasedEdges,ArrayBasedALContainer](
      "/Users/rsha/harvardcourses/thesis/project/graphdatastructures/src/test/resources/facebook_combined.txt",
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

}
