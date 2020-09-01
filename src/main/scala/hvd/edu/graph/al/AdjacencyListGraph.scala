package hvd.edu.graph.al

import hvd.edu.{Graph, Node}

import scala.collection.mutable

/**
 * [ [0, 2], [1, 3], [2, 3], [2, 4], [3, 5], [4, 5] ]
 * [0 -> 2]
 * [1 -> 3]
 * [2 -> (3,4)]
 * [3 -> 5]
 * [4 -> 5]
 */
object EmptyAdjacencyNode extends AdjacencyListNode(-1, 1)

case class AdjacencyListNode(override val id: Int, v: Int) extends Node {

  private var outgoingEdgeSet = mutable.Set[AdjacencyListNode]()

  def addEdge(e: AdjacencyListNode) = outgoingEdgeSet.add(e)

  def outgoingEdges: Set[AdjacencyListNode] = outgoingEdgeSet.toSet

}

class AdjacencyListGraph(numVertex: Int) extends Graph[AdjacencyListNode] {

  private var aListContainer = new ArrayBasedALContainer(numVertex)

  override def addEdge(u: AdjacencyListNode, v: AdjacencyListNode): Unit = {
    val node = aListContainer.vertex_?(u)
    node match {
      case EmptyAdjacencyNode => aListContainer.add(u, v)
      case aln: AdjacencyListNode => aListContainer.addEdge(u, v)
    }
  }


  override def addVertex(v: AdjacencyListNode): Unit = aListContainer.add(v, EmptyAdjacencyNode)

  override def printGraph(mayBeNumberOfVertex: Option[Int]): Unit = {
    println(s"************* Printing AL Graph *************")
//    val vlist: List[AdjacencyListNode] = aListContainer.allVertices
//    val vlenToUse = mayBeNumberOfVertex.getOrElse(vertexLength - 1)
//
//    for (i <- 0 to vlenToUse) {
//      val n1 = vlist(i)
//      val outGoingEdges = n1.outgoingEdges
//      println(
//        s"[ ${n1.id} -> ( ${outGoingEdges.toList.map(_.id).sorted.mkString(",")} ) ]"
//      )
//    }

    aListContainer.print(mayBeNumberOfVertex)
    println(s"************* Finished Printing AL Graph *************")
  }

  override def vertexList: List[AdjacencyListNode] = aListContainer.allVertices

  override def vertexLength: Int = aListContainer.vertexLength

  override def edgeLength: Int = aListContainer.edgeLength

  override def edgesForVertex(v: AdjacencyListNode): List[AdjacencyListNode] = aListContainer.edgesForVertex(v)

  override def edgesForVertexId(vid: Int): List[AdjacencyListNode] = aListContainer.edgesForVertexId(vid)

}

object AdjacencyList {

  def buildFromString(s: String,
                      firstDelimiter: String,
                      secondDelimiter: String): AdjacencyListGraph = {

    val arrayEdges: Array[String] = s.split(firstDelimiter)

    val graph = new AdjacencyListGraph(arrayEdges.length)

    arrayEdges.foreach { currEdge =>
      val currEdgeArray: Array[String] = currEdge.split(secondDelimiter)
      if (currEdgeArray.length == 2 && currEdgeArray(1).nonEmpty) {
        val firstE = currEdgeArray(0).toInt
        val secondE = currEdgeArray(1).toInt
        val aln1 = AdjacencyListNode(firstE, firstE)
        val aln2 = AdjacencyListNode(secondE, secondE)
        graph.addEdge(aln1, aln2)
      } else if (currEdgeArray.length == 1) {
        val firstE = currEdgeArray(0).toInt
        val aln1 = AdjacencyListNode(firstE, firstE)
        graph.addVertex(aln1)
      } else {
        // do nothing
      }
    }
    graph
  }

}
