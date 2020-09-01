package hvd.edu.utils

import java.io.{BufferedReader, FileReader}

import hvd.edu.graph.al.{AdjacencyListGraph, AdjacencyListNode}
import hvd.edu.graph.csr.{CSRContainerMaker, CSRContainers, CSRGraph, CSRNode}

object GraphInputFileReader {

  def readFileAsAL(filePath: String,
                   fileLength: Int,
                   delimiter: String): AdjacencyListGraph = {

    val startTime = System.currentTimeMillis()

    val br = new BufferedReader(new FileReader(filePath))

    var count = 0

    val graph = new AdjacencyListGraph(fileLength) /* should be good number to initialize for edge and nodes */

    var readLine: String = br.readLine()
    while (readLine != null) {
      val edgesInLine = readLine.split(delimiter) // we know there are only 2
      val firstE = edgesInLine(0).toInt
      val secondE = edgesInLine(1).toInt
      val aln1 = AdjacencyListNode(firstE, firstE)
      val aln2 = AdjacencyListNode(secondE, secondE)
      graph.addEdge(aln1, aln2)
      count += 1
      readLine = br.readLine()
    }

    val endTime = System.currentTimeMillis()
    val readTime = (endTime - startTime)

    //println(s"Read lines : ${fileLength} to create graph in ${readTime} ms")

    graph
  }

  def readFileAsCSR[C <: CSRContainers](filePath: String,
                                        fileLength: Int,
                                        delimiter: String)(implicit ev: CSRContainerMaker[C]): CSRGraph[C] = {

    val startTime = System.currentTimeMillis()

    val br = new BufferedReader(new FileReader(filePath))

    var count = 0

    val graph = new CSRGraph(fileLength, fileLength) /* should be good number to initialize for edge and nodes */

    var readLine: String = br.readLine()
    while (readLine != null) {
      val edgesInLine = readLine.split(delimiter) // we know there are only 2
      val firstE = edgesInLine(0).toInt
      val secondE = edgesInLine(1).toInt
      val aln1 = CSRNode(firstE, firstE)
      val aln2 = CSRNode(secondE, secondE)
      graph.addEdge(aln1, aln2)
      count += 1
      readLine = br.readLine()
    }

    val endTime = System.currentTimeMillis()
    val readTime = (endTime - startTime)

    // println(s"Read lines : ${fileLength} to create graph in ${readTime} ms")

    graph
  }

}
