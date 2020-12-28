package hvd.edu.utils

import java.nio.file.{ Files, Paths }

import com.typesafe.scalalogging.LazyLogging
import hvd.edu.graph._

import scala.io.{ BufferedSource, Source }

object GraphInputFileReader extends LazyLogging {

  //  def readFile2[N <: Node, C <: GraphContainer[N]](filePath: String, delimiter: String)(implicit ev: ContainerMaker[N, C], nm: NodeMaker[N], fanout: Option[Int] = None): Graph[N, C] = {
  //    val url = getClass.getClassLoader.getResource(filePath)
  //    val path = Paths.get(url.toURI)
  //    val fileSize = Files.size(path)
  //    readFile(filePath, fileSize, delimiter)
  //  }

  def readFile[N <: Node, C <: GraphContainer[N]](filePath: String, fileLength: Int, delimiter: String)(implicit ev: ContainerMaker[N, C], nm: NodeMaker[N], fanout: Option[Int] = None): Graph[N, C] = {

    Globals.timeAndLog {
      val bufferedFileSource: BufferedSource = Source.fromResource(filePath)
      val br = bufferedFileSource.bufferedReader()
      var count = 0
      val graph = new Graph(fileLength, fileLength) /* should be good number to initialize for edge and nodes */

      var readLine: String = br.readLine()
      while (readLine != null) {
        if (!readLine.startsWith("#")) {
          val edgesInLine = readLine.split(delimiter) // we know there are only 2
          val firstE: Int = edgesInLine(0).toInt
          val secondE: Int = edgesInLine(1).toInt
          val aln1 = nm.make(firstE, firstE)
          val aln2 = nm.make(secondE, secondE)
          graph.addEdge(aln1, aln2)
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
