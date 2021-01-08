package hvd.edu.benchmark.utils

import java.io.{ File, FileOutputStream, PrintWriter }
import java.util.Arrays

import scala.collection.{ immutable, mutable }
import scala.util.Random

/**
 * https://stackoverflow.com/questions/20171901/how-to-generate-random-graphs
 */
object RandomGraphGenerator {

  def main(args: Array[String]): Unit = {

    //val fileName = args(0)
    val numNodes = args(0).toInt
    // val edges = numNodes + 2
    val maxEdgesLen = if (args(1).nonEmpty) {
      args(1).toInt
    }
    else {
      5
    }
    val mayBeSeed = if (args(2).nonEmpty) Option(args(2).toLong) else None
    val randomNum = mayBeSeed.fold(Random) {
      l =>
        val rr = Random
        rr.setSeed(l)
        rr
    }

    val arrayOfEdges = Array.ofDim[List[Int]](maxEdgesLen)
    val arrayOfNodes = Array.ofDim[Int](numNodes)

    //set up the nodes for now not random
    for (i <- 0 to numNodes) {
      arrayOfNodes(i) = i
      for (k <- arrayOfEdges(i).length + 1 to maxEdgesLen) {
        //val x = randomNum.nextInt(numNodes)
        //arrayOfEdges(i) = x :: arrayOfEdges(i)
        //arrayOfEdges(x) = i :: arrayOfEdges(x)
      }

    }
    // write to the output file

  }

  /**
   * https://stackoverflow.com/questions/50586547/generate-a-graph-with-n-veritces-m-edges-uniformly-at-random-algorithm
   */
  def gen(numNodes: Int, numEdges: Int) = {

    val rand = Random
    val maxNodeIndex = numNodes - 1 // ( as they are 0 indexed)
    val edgeSet = mutable.Set[RandomEdge]()

    /**
     * //https://stackoverflow.com/questions/2394246/algorithm-to-select-a-single-random-combination-of-values/2394292#2394292
     * initialize set S to empty
     * for J := N-M + 1 to N do
     * T := RandInt(1, J)
     * if T is not in S then
     * insert T in S
     * else
     * insert J in S
     */
    // start with 1 and it allows 2 values for random numbers between 0.1
    for (j <- 1 to numEdges + 1; if (edgeSet.size < numEdges)) {
      // when we want to generate more edges than nodes ( dense graphs ) ensure we align to
      // max nodes for random number generation.
      val adjustedJ = if (j < numNodes) j else numNodes
      val randomNode1 = rand.nextInt(adjustedJ)
      val randomNode2 = rand.nextInt(adjustedJ)
      // make all possible comb ( a ->b is same as b->a) and since we add to set - the set will dedupe
      val possibleEdge1 = RandomEdge(randomNode1, randomNode2)
      // if we were to use J as the edge node - make sure its adjusted to <= maxNodeIndex.
      // we do this twice - because random number generation needs to include lastIndex
      // hence the first adjustedJ ensure we use the lastindex ( as it bring all random numbers between
      // 0 and value passed, so if when numNodes is passed it bring all random numbers between 0 and numNodes-1)
      val alignJToNodeMaxIndex = if (adjustedJ == numNodes) maxNodeIndex else adjustedJ
      val possibleEdge2 = RandomEdge(randomNode1, alignJToNodeMaxIndex)
      val possibleEdge3 = RandomEdge(randomNode2, alignJToNodeMaxIndex)
      if (validEdgeToAdd(possibleEdge1)) {
        edgeSet.add(possibleEdge1)
      }
      else if (validEdgeToAdd(possibleEdge2)) {
        edgeSet.add(possibleEdge2)
      }
      else if (validEdgeToAdd(possibleEdge3)) {
        edgeSet.add(possibleEdge3)
      }
    }

    // now just write the final graph out !
    val allNodes = (0 to numNodes - 1).toList
    val allEdges: Map[Int, mutable.Set[Int]] = edgeSet.groupBy(_.from).map {
      v => (v._1, v._2.map(_.to))
    }
    val stringToWrite: List[String] = allNodes.map {
      v =>
        val mayBeEdges = allEdges.get(v)
        val retStr = mayBeEdges.fold(s"${v}") { evs =>
          evs.map { ev => s"$v\t$ev" }.mkString("\n")
        }
        retStr
    }

    val finalString = stringToWrite.mkString("\n")
    // println(finalString)

    // write to file
    val f = new File(s"./benchmark/generated/generated${numNodes}_${numEdges}.txt")
    val printStream = new PrintWriter(f, "UTF-8")
    try {
      printStream.print(finalString)
    }
    finally {
      printStream.flush()
      printStream.close()
    }

  }

  private def validEdgeToAdd(edge: RandomEdge) = {
    edge.from != edge.to
  }

  // The following method is inspired by the generator in com.twitter.cassovary
  // Credits - com.twitter.cassovary.util.Sampling
  // and com.twitter.cassovary.util.BinomialDistribution
  // Not really using this as not able to control generation of fixed edges.
  def genERGraph(numNodes: Int, numEdges: Int, prob: Option[Double]) = {
    val probEdge: Double = prob.getOrElse(numEdges / numNodes.toDouble)
    val rand = Random
    val binomialDistribution = new MyBinomialDistribution(numNodes - 1, probEdge)
    val range = (0 until numNodes - 1)
    val vv: immutable.IndexedSeq[(Int, Int)] = (0 until numNodes).flatMap { source =>
      val positiveBits: Array[Int] = randomSubset(binomialDistribution, range, rand)
      val edgesFromSource = positiveBits.map { x => if (x < source) x else x + 1 }
      edgesFromSource.map {
        d => (source, d)
      }
    }

    val edgeSorted = vv.groupBy(_._1).map {
      v => (v._1, v._2.map(_._2))
    }.toList.sortBy(_._1)

    val stringToWrite: List[String] = edgeSorted.map {
      v =>
        s"${v._1}\t${v._2.mkString("\t")}"
    }

    println(stringToWrite.mkString("\n"))
  }

  private def randomSubset(binDistribution: MyBinomialDistribution, range: Range, rand: Random): Array[Int] = {
    val sample = binDistribution.sample(rand)
    if (sample >= range.size) {
      range.toArray
    }
    else {
      if (sample > range.size / 2) {
        val updatedSample = (range.size - sample)
        val complement: Array[Int] = genSubset(updatedSample, range, rand)
        range.filterNot(complement.contains).toArray
      }
      else {
        genSubset(sample, range, rand)
      }
    }

  }

  private def genSubset(sample: Int, range: Range, rand: Random) = {
    val result = mutable.Set[Int]()
    while (result.size < sample) {
      val randomElement = range(rand.nextInt(range.size))
      result.add(randomElement)
    }
    result.toArray
  }
}

class MyBinomialDistribution(n: Int, p: Double) {
  private lazy val pdf: Array[Double] = calculatePdf()
  private lazy val cdf: Array[Double] = pdf.scanLeft(0.0)(_ + _) drop 1

  private def calculatePdf() = {
    val pdf = Array.fill(n + 1)(0.0)
    pdf(0) = math.pow(1 - p, n)
    for (i <- 1 to n) {
      pdf(i) = p * (n - i + 1) / (i * (1 - p)) * pdf(i - 1)
    }
    pdf
  }

  def sample(rng: Random): Int = p match {
    case 1 => n
    case 0 => 0
    case _ =>
      val unifDouble = rng.nextDouble()
      Arrays.binarySearch(cdf, unifDouble) match {
        case found: Int if found >= 0           => found - 1
        case negatedInsertionPointMinusOne: Int => -negatedInsertionPointMinusOne - 1
      }
  }
}

case class RandomEdge(from: Int, to: Int) {

  override def equals(obj: Any): Boolean = obj match {
    case RandomEdge(f1, t1) => ((from == f1) && (to == t1)) || ((from == t1) && (f1 == to))
    case _                  => false
  }

}