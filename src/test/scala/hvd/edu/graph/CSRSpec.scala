package hvd.edu.graph

import hvd.edu.graph.csr.{ArrayBasedCSRContainer, CSRNode, HashMapBasedCSRContainer}
import hvd.edu.utils.GraphBuilder
import org.scalatest.{FlatSpec, Matchers}

class CSRSpec extends FlatSpec with Matchers {

  "Reading Graph as String input" should "Generate a valid ArrayBasedCSR Graph" in {
    val inputString = "0,2 1,3 2,3 2,4 3,5 4,5"
    val generatedCSR = GraphBuilder.buildFromString[CSRNode, ArrayBasedCSRContainer](inputString, " ", ",")
    // generatedCSR.printGraph(None)
    generatedCSR shouldNot be(null)
    generatedCSR.vertexLength should be(5)
    generatedCSR.edgeLength should be(6)

  }

  it should "Generate a valid HashMapBased CSR graph" in {

    val inputString = "0,2 1,3 2,3 2,4 3,5 4,5"
    val generatedCSR = GraphBuilder.buildFromString[CSRNode, HashMapBasedCSRContainer](inputString, " ", ",")
    // generatedCSR.printGraph(None)
    generatedCSR shouldNot be(null)
    generatedCSR.vertexLength should be(5)
    generatedCSR.edgeLength should be(6)


  }
}
