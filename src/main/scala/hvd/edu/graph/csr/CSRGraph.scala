package hvd.edu.graph.csr

import hvd.edu.{Graph, Node}

/**
 * [ [0, 2], [1, 3], [2, 4], [3, 5], [4, 5], [2, 3]]
 * [0 -> 2]
 * [1 -> 3]
 * [2 -> (3,4)]
 * [3 -> 5]
 * [4 -> 5]
 */

trait CSRNodeDef extends Node{
  def value: Int
  def firstEdgeIndex: Int

}

case class CSRNode(override val id: Int, value: Int, firstEdgeIndex: Int = -1) extends CSRNodeDef

object EmptyCSRNode extends CSRNode(-1, -1, -1)

class CSRGraph[C <: CSRContainers](numVertex: Int, numEdges: Int)(implicit ev: CSRContainerMaker[C]) extends Graph[CSRNode] {

  private val csrGraphContainer = CSRContainerMaker.apply[C](numVertex, numEdges)

  override def addEdge(vertex: CSRNode, edgeNode: CSRNode): Unit = {
    // if vertex was already added
    val node = csrGraphContainer.vertex_?(vertex)
    node match {
      case EmptyCSRNode => csrGraphContainer.add(vertex, edgeNode)
      case c: CSRNode => csrGraphContainer.addEdge(vertex, edgeNode)
    }
  }

  override def addVertex(vertex: CSRNode): Unit = csrGraphContainer.add(vertex, EmptyCSRNode)

  override def printGraph(mayBeVertexLen: Option[Int]): Unit = {
    println(s"************* Printing CSR Graph *************")
    csrGraphContainer.print(mayBeVertexLen)
//    val unVlist = vertexList
//    val vlist = unVlist/*.filterNot(_ == EmptyCSRNode)*/
//    println(s"Total vertex len: ${vlist.size}")
//    val edgeList = csrGraphContainer.allEdges
//    println(s"Total Edges : ${edgeList.size}")
//
//    val zippedVertices = vlist.zip(vlist.tail)
//
//    val vListPair = mayBeVertexLen.fold(zippedVertices) { x =>
//      zippedVertices.take(x + 1)
//    }
//
//    vListPair.foreach {
//      case (n1, n2) =>
//        val edgeStartIndex = n1.firstEdgeIndex
//        val edgeEndIndex = n2.firstEdgeIndex
//        if (n2 != EmptyCSRNode) {
//          val edgeListForN1 = for (i <- edgeStartIndex until edgeEndIndex)
//            yield {
//              if (i != -1) edgeList(i).id else -1
//            }
//
//          println(s"[ ${n1.id} -> ( ${edgeListForN1.mkString(",")} ) ]")
//        }
//    }
//
//    // print the last vertex now
//    val lastNode: CSRNode = vlist(csrGraphContainer.vertexLength - 1)
//    val edgesForLast =
//      (lastNode.firstEdgeIndex until edgeList.size).iterator.takeWhile { j =>
//        edgeList(j) != EmptyCSRNode
//      }
//
//    println(
//      s"[ ${lastNode.id} -> ( ${edgesForLast.map(edgeList(_).id).mkString(",")} ) ]"
//    )

    println(s"************* Finished Printing CSR Graph *************")
  }

  override def vertexList: List[CSRNode] = csrGraphContainer.nonEmptyVertexList

  override def vertexLength: Int = csrGraphContainer.vertexLength

  override def edgeLength: Int = csrGraphContainer.edgeLength

  override def edgesForVertex(v: CSRNode): List[CSRNode] = csrGraphContainer.edgesForVertex(v)

  override def edgesForVertexId(vid: Int): List[CSRNode] = csrGraphContainer.edgesForVertexId(vid)

}

object CSRGraph {

  def buildFromString[C <: CSRContainers](s: String,
                      firstDelimiter: String,
                      secondDelimiter: String)(implicit ev: CSRContainerMaker[C]): CSRGraph[C] = {

    val arrayEdges: Array[String] = s.split(firstDelimiter)

    val graph = new CSRGraph(arrayEdges.length, arrayEdges.length)

    arrayEdges.foreach { currEdge =>
      val currEdgeArray: Array[String] = currEdge.split(secondDelimiter)

      if (currEdgeArray.length == 2 && currEdgeArray(1).nonEmpty) {
        val firstE = currEdgeArray(0).toInt
        val secondE = currEdgeArray(1).toInt
        val aln1 = CSRNode(firstE, firstE)
        val aln2 = CSRNode(secondE, secondE)
        graph.addEdge(aln1, aln2)
      } else if (currEdgeArray.length == 1) {
        val firstE = currEdgeArray(0).toInt
        val aln1 = CSRNode(firstE, firstE)
        graph.addVertex(aln1)
      }
    }
    graph
  }

}
