package hvd.edu.utils

import java.io.{BufferedReader, FileReader, InputStreamReader}

import hvd.edu.graph._

import scala.io.{BufferedSource, Source}

object GraphInputFileReader {

  def readFile[N <: Node, C <: GraphContainer[N]](filePath: String, fileLength: Int, delimiter: String)(implicit
    ev: ContainerMaker[N, C],
    nm: NodeMaker[N],
    fanout: Option[Int] = None
  ) = {

    val startTime = System.currentTimeMillis()

    //val br = new BufferedReader(new FileReader(filePath))
    val bufferedFileSource: BufferedSource = Source.fromResource(filePath)

    val br = bufferedFileSource.bufferedReader()

    var count = 0

    val graph = new Graph(fileLength, fileLength) /* should be good number to initialize for edge and nodes */

    var readLine: String = br.readLine()
    while (readLine != null) {
      val edgesInLine = readLine.split(delimiter) // we know there are only 2
      val firstE: Int = edgesInLine(0).toInt
      val secondE: Int = edgesInLine(1).toInt
      val aln1 = nm.make(firstE, firstE)
      val aln2 = nm.make(secondE, secondE)
      graph.addEdge(aln1, aln2)
      count += 1
      readLine = br.readLine()
    }

    val endTime = System.currentTimeMillis()
    val readTime = endTime - startTime

    //println(s"Read lines : ${fileLength} to create graph in ${readTime} ms")

    graph
  }

}
