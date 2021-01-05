package hvd.edu.utils

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.graph._
import org.openjdk.jol.info.ClassLayout

object GraphBuilder extends LazyLogging {

  def buildFromString[N <: Node](
    inputString:     String,
    firstDelimiter:  String,
    secondDelimiter: String,
    graphContainer:  GraphContainer[N],
    nodeMaker:       NodeMaker[N]): Graph[N] = {

    val arrayEdges: Array[String] = inputString.split(firstDelimiter)
    val graph = new Graph(graphContainer)
    arrayEdges.foreach { currEdge =>
      val currEdgeArray: Array[String] = currEdge.split(secondDelimiter)
      if (currEdgeArray.length == 2 && currEdgeArray(1).nonEmpty) {
        val firstE = currEdgeArray(0).toInt
        val secondE = currEdgeArray(1).toInt
        val aln1 = nodeMaker.make(firstE, firstE)
        val aln2 = nodeMaker.make(secondE, secondE)
        graph.addEdge(aln1, aln2)
      }
      else if (currEdgeArray.length == 1) {
        val firstE = currEdgeArray(0).toInt
        val aln1 = nodeMaker.make(firstE, firstE)
        graph.addVertex(aln1)
      }
      else {
        // do nothing
      }
    }
    //    logger.warn(
    //      s"""
    //         |The Size of the Graph
    //         |${ClassLayout.parseInstance(graph).}
    //         |""".stripMargin)
    graph
  }

}
