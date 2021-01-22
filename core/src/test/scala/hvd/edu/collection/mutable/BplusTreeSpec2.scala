package hvd.edu.collection.mutable

import hvd.edu.graph.al.ALNode
import org.scalatest.{ FlatSpec, Matchers }

import scala.collection.mutable.ListBuffer

class BplusTreeSpec2 extends FlatSpec with Matchers {

  "BPlusTree" should "be able to create a tree and grow tree" in {

    val testTree = new BPlusTreeImpl[Int, List[Int]](3)
    testTree.add(8, List(1, 23))
    testTree.add(4, List(54, 78))
    testTree shouldNot be(null)
    val nodesList = testTree.getNodes()
    nodesList shouldEqual (List(4, 8))
    testTree.add(6, List(89, 65))
    val nodesList2 = testTree.getNodes()
    nodesList2 shouldEqual (List(6))
    val child = testTree.getImmediateIndexNodes()
    child.size shouldBe 2
    testTree.add(7, List(77, 88))
    val nodesList3 = testTree.getNodes()
    nodesList3 shouldEqual (List(6, 7))
    val child2 = testTree.getImmediateIndexNodes()
    child2.size shouldBe 3
    val allChildNodesInOrder = child2.flatMap(_.listOfNodes)
    // // println(allChildNodesInOrder.mkString(","))
    val expectedChildNodesInOrder = ListBuffer(4, 6, 7, 8)
    allChildNodesInOrder shouldBe expectedChildNodesInOrder

    val valuesFoundFor6 = testTree.find(6)
    println(s"Values Read for key 6 = ${valuesFoundFor6.mkString(",")}")
    val expectedvaluesFor6 = Option(List(89, 65))
    valuesFoundFor6 shouldEqual expectedvaluesFor6

    val valuesFoundFor7 = testTree.find(7)
    println(s"Values Read for key 7 = ${valuesFoundFor7.mkString(",")}")
    val expectedvaluesFor7 = Option(List(77, 88))
    valuesFoundFor7 shouldEqual expectedvaluesFor7

    // test override
    testTree.update(7, List(177, 188))
    val nodesList4 = testTree.getNodes()
    nodesList4 shouldEqual (List(6, 7))
    val child4 = testTree.getImmediateIndexNodes()
    child4.size shouldBe 3
    val allChildNodesInOrder4 = child4.flatMap(_.listOfNodes)
    val allLeaves = testTree.getLeaves()
    // // println(allChildNodesInOrder.mkString(","))
    val expectedChildNodesInOrder4 = ListBuffer(4, 6, 7, 8)
    allChildNodesInOrder4 shouldBe expectedChildNodesInOrder4
    allLeaves shouldBe expectedChildNodesInOrder4

    val valuesFoundFor7Overidden = testTree.find(7)
    println(
      s"Values Read for key 7 after override = ${valuesFoundFor7Overidden.mkString(",")}")
    val expectedvaluesFor7AfterOverride = Option(List(177, 188))
    valuesFoundFor7Overidden shouldEqual expectedvaluesFor7AfterOverride

  }

}
