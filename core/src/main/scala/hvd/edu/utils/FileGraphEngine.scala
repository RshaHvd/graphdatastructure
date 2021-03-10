package hvd.edu.utils

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.graph.{ GraphContainer, _ }

import scala.io.{ BufferedSource, Source }

object FileGraphEngine extends LazyLogging {

  def readFile[N <: Node](
    filePath:       String,
    fileLength:     Int,
    delimiter:      String,
    graphContainer: GraphContainer[N],
    nodeMaker:      NodeMaker[N]): Graph[N] = {

    Globals.timeAndLog {
      val bufferedFileSource: BufferedSource = Source.fromResource(filePath)
      val br = bufferedFileSource.bufferedReader()
      var count = 0
      val graph = new Graph(graphContainer) /* should be good number to initialize for edge and nodes */

      var readLine: String = br.readLine()
      while (readLine != null) {
        if (!readLine.startsWith("#")) {
          val edgesInLine = readLine.split(delimiter) // we know there are only 2
          if (edgesInLine.length == 2) {
            val firstE: Int = edgesInLine(0).toInt
            val secondE: Int = edgesInLine(1).toInt
            val aln1 = nodeMaker.make(firstE)
            val aln2 = nodeMaker.make(secondE)
            graph.addEdge(aln1, aln2)
          }
          else {
            val firstE: Int = edgesInLine(0).toInt
            val aln1 = nodeMaker.make(firstE)
            graph.addVertex(aln1)
          }
        }
        count += 1
        readLine = br.readLine()
      }
      br.close() // close the file always.
      graph
    } {
      ms => logger.debug(s"Read lines : ${fileLength} to create graph in ${ms} ms")
    }
  }

}
