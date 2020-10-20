package hvd.edu.utils

import hvd.edu.graph._

object GraphBuilder {

  def buildFromString[N<:Node, C <: GraphContainer[N]](inputString: String,
                                                       firstDelimiter: String,
                                                       secondDelimiter: String)(implicit ev: ContainerMaker[N, C],
                                                                                nodeMaker: NodeMaker[N],
                                                                                fanout: Option[Int] = None): Graph[N, C] = {

    val arrayEdges: Array[String] = inputString.split(firstDelimiter)

    val graph = new Graph(arrayEdges.length, arrayEdges.length)

    arrayEdges.foreach { currEdge =>
      val currEdgeArray: Array[String] = currEdge.split(secondDelimiter)
      if (currEdgeArray.length == 2 && currEdgeArray(1).nonEmpty) {
        val firstE = currEdgeArray(0).toInt
        val secondE = currEdgeArray(1).toInt
        val aln1 = nodeMaker.make(firstE, firstE)
        val aln2 = nodeMaker.make(secondE, secondE)
        graph.addEdge(aln1, aln2)
      } else if (currEdgeArray.length == 1) {
        val firstE = currEdgeArray(0).toInt
        val aln1 = nodeMaker.make(firstE, firstE)
        graph.addVertex(aln1)
      } else {
        // do nothing
      }
    }
    graph
  }

}
