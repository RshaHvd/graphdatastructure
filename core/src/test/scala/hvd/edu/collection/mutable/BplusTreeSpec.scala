package hvd.edu.collection.mutable

import org.scalatest.{ FlatSpec, Matchers }

import scala.collection.mutable.ListBuffer

class BplusTreeSpec extends FlatSpec with Matchers {

  "BPlusTree" should "be able to create a tree and grow tree" in {

    val testTree = new BPlusTreeImpl[Int, Int](3)
    testTree.add(8, 8)
    testTree.add(4, 4)
    testTree shouldNot be(null)
    val nodesList = testTree.getNodes()
    nodesList shouldEqual (List(4, 8))
    testTree.add(6, 6)
    val nodesList2 = testTree.getNodes()
    nodesList2 shouldEqual (List(6))
    val child = testTree.getImmediateIndexNodes()
    child.size shouldBe 2
    testTree.add(7, 7)
    val nodesList3 = testTree.getNodes()
    nodesList3 shouldEqual (List(6, 7))
    val child2: List[TreeNode[Int]] = testTree.getImmediateIndexNodes()
    child2.size shouldBe 3
    val allChildNodesInOrder = child2.flatMap(_.listOfNodes)
    // // println(allChildNodesInOrder.mkString(","))
    val expectedChildNodesInOrder = ListBuffer(4, 6, 7, 8)
    allChildNodesInOrder.sorted shouldBe expectedChildNodesInOrder

    println("Adding 5")
    testTree.add(5, 5)
    val child3 = testTree.getImmediateIndexNodes()
    child3.size shouldBe 3
    val allChildrenInOrder3 = child3.flatMap(_.listOfNodes)
    // println(allChildrenInOrder3.mkString(","))
    val expectedChildNodesInOrder3 = ListBuffer(4, 5, 6, 7, 8)
    allChildrenInOrder3.sorted shouldBe expectedChildNodesInOrder3

    println("Adding 9")
    testTree.add(9, 9)
    val child4 = testTree.getImmediateIndexNodes()
    child4.size shouldBe 2
    val allChildrenInOrder4 = child4.flatMap(_.listOfNodes)
    // println(allChildrenInOrder4.mkString(","))
    val expectedChildNodesInOrder4 = ListBuffer(6, 8)
    allChildrenInOrder4.sorted shouldBe expectedChildNodesInOrder4

    println("Adding 10")
    testTree.add(10, 10)
    val child5 = testTree.getImmediateIndexNodes()
    child5.size shouldBe 2
    val allChildrenInOrder5 = child5.flatMap(_.listOfNodes)
    // println(allChildrenInOrder5.mkString(","))
    val expectedChildNodesInOrder5 = ListBuffer(6, 8, 9)
    allChildrenInOrder5 shouldBe expectedChildNodesInOrder5
    val allLeaves5 = testTree.getLeaves()
    // println(s"${allLeaves5.mkString(",")}")
    val expectedLeaves5 = List(4, 5, 6, 7, 8, 9, 10)
    allLeaves5.sorted shouldBe expectedLeaves5

    println("Adding 11")
    testTree.add(11, 11)
    val nodes6 = testTree.getNodes()
    // println(s"${nodes6.mkString(",")}")
    nodes6 shouldEqual (List(7, 9))
    val child6 = testTree.getImmediateIndexNodes()
    child6.size shouldBe 3
    val allChildrenInOrder6 = child6.flatMap(_.listOfNodes)
    // println(allChildrenInOrder6.mkString(","))
    val expectedChildNodesInOrder6 = ListBuffer(6, 8, 10)
    allChildrenInOrder6 shouldBe expectedChildNodesInOrder6
    val allLeaves6 = testTree.getLeaves()
    // println(s"${allLeaves6.mkString(",")}")
    val expectedLeaves6 = List(4, 5, 6, 7, 8, 9, 10, 11)
    allLeaves6.sorted shouldBe expectedLeaves6

    println("Adding 12")
    testTree.add(12, 12)
    val nodes7 = testTree.getNodes()
    // println(s"${nodes7.mkString(",")}")
    nodes7 shouldEqual (List(7, 9))
    val child7 = testTree.getImmediateIndexNodes()
    child7.size shouldBe 3
    val allChildrenInOrder7 = child7.flatMap(_.listOfNodes)
    // println(allChildrenInOrder7.mkString(","))
    val expectedChildNodesInOrder7 = ListBuffer(6, 8, 10, 11)
    allChildrenInOrder7 shouldBe expectedChildNodesInOrder7
    val allLeaves7 = testTree.getLeaves()
    // println(s"${allLeaves7.mkString(",")}")
    val expectedLeaves7 = List(4, 5, 6, 7, 8, 9, 10, 11, 12)
    allLeaves7 shouldBe expectedLeaves7

    println("Adding 14")
    testTree.add(14, 14)
    val treeHt = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt}")
    treeHt shouldBe 3

    val nodes8 = testTree.getNodes()
    // println(s"${nodes8.mkString(",")}")
    nodes8 shouldEqual (List(9))

    val child8 = testTree.getImmediateIndexNodes()
    child8.size shouldBe 2
    val allChildrenInOrder8 = child8.flatMap(_.listOfNodes)
    // println(allChildrenInOrder8.mkString(","))
    val expectedChildNodesInOrder8 = ListBuffer(7, 11)
    allChildrenInOrder8 shouldBe expectedChildNodesInOrder8
    val allGrandChild8: List[TreeNode[Int]] = child8.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild8.size shouldBe 4

    val allGrandChildNodes = allGrandChild8.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes.mkString(",")}")
    val expectedGrandChildren = List(6, 8, 10, 12)
    expectedGrandChildren shouldBe allGrandChildNodes

    val allLeaves8 = testTree.getLeaves()
    // println(s"${allLeaves8.mkString(",")}")
    val expectedLeaves8 = List(4, 5, 6, 7, 8, 9, 10, 11, 12, 14)
    allLeaves8 shouldBe expectedLeaves8

    //
    println("Adding 15")
    testTree.add(15, 15)
    val treeHt9 = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt9}")
    treeHt9 shouldBe 3

    val nodes9 = testTree.getNodes()
    // println(s"${nodes9.mkString(",")}")
    nodes9 shouldEqual (List(9))

    val child9 = testTree.getImmediateIndexNodes()
    val allChildrenInOrder9 = child9.flatMap(_.listOfNodes)
    // println(allChildrenInOrder9.mkString(","))
    val expectedChildNodesInOrder9 = ListBuffer(7, 11)
    allChildrenInOrder9 shouldBe expectedChildNodesInOrder9
    val allGrandChild9 = child9.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild9.size shouldBe 4

    val allGrandChildNodes9 = allGrandChild9.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes9.mkString(",")}")
    val expectedGrandChildren9 = List(6, 8, 10, 12, 14)
    expectedGrandChildren9 shouldBe (allGrandChildNodes9)

    val allLeaves9 = testTree.getLeaves()
    // println(s"${allLeaves9.mkString(",")}")
    val expectedLeaves9 = List(4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15)
    allLeaves9 shouldBe expectedLeaves9

    println("Adding 16")
    testTree.add(16, 161)
    val treeHt10 = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt10}")
    treeHt10 shouldBe 3

    val nodes10 = testTree.getNodes()
    // println(s"Root Nodes: ${nodes10.mkString(",")}")
    nodes10 shouldEqual (List(9))

    val child10 = testTree.getImmediateIndexNodes()
    child10.size shouldBe 2
    val allChildrenInOrder10 = child10.flatMap(_.listOfNodes)
    // println(s"Children: ${allChildrenInOrder10.mkString(",")}")
    val expectedChildNodesInOrder10 = ListBuffer(7, 11, 14)
    allChildrenInOrder10 shouldBe expectedChildNodesInOrder10

    val allGrandChild10 = child10.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild10.size shouldBe 5

    val allGrandChildNodes10 = allGrandChild10.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes10.mkString(",")}")
    val expectedGrandChildren10 = List(6, 8, 10, 12, 15)
    expectedGrandChildren10 shouldBe (allGrandChildNodes10)

    val allLeaves10 = testTree.getLeaves()
    // println(s"Leaves: ${allLeaves10.mkString(",")}")
    val expectedLeaves10 = List(4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16)
    allLeaves10 shouldBe expectedLeaves10

    println("Adding 17")
    testTree.add(17, 171)
    val treeHt11 = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt11}")
    treeHt11 shouldBe 3

    val nodes11 = testTree.getNodes()
    // println(s"Root Nodes: ${nodes11.mkString(",")}")
    nodes11 shouldEqual (List(9))

    val child11 = testTree.getImmediateIndexNodes()
    child11.size shouldBe 2
    val allChildrenInOrder11 = child11.flatMap(_.listOfNodes)
    // println(s"Children: ${allChildrenInOrder11.mkString(",")}")
    val expectedChildNodesInOrder11 = ListBuffer(7, 11, 14)
    allChildrenInOrder11 shouldBe expectedChildNodesInOrder11

    val allGrandChild11 = child10.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild11.size shouldBe 5

    val allGrandChildNodes11 = allGrandChild11.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes11.mkString(",")}")
    val expectedGrandChildren11 = List(6, 8, 10, 12, 15, 16)
    expectedGrandChildren11 shouldBe (allGrandChildNodes11)

    val allLeaves11 = testTree.getLeaves()
    // println(s"Leaves: ${allLeaves11.mkString(",")}")
    val expectedLeaves11 = List(4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17)
    allLeaves11 shouldBe expectedLeaves11

    println("Adding 18")
    testTree.add(18, 181)
    val treeHt12 = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt12}")
    treeHt12 shouldBe 3

    val nodes12 = testTree.getNodes()
    // println(s"Root Nodes: ${nodes12.mkString(",")}")
    nodes12 shouldEqual (List(9, 14))

    val child12 = testTree.getImmediateIndexNodes()
    child12.size shouldBe 3
    val allChildrenInOrder12 = child12.flatMap(_.listOfNodes)
    // println(s"Children: ${allChildrenInOrder12.mkString(",")}")
    val expectedChildNodesInOrder12 = ListBuffer(7, 11, 16)
    allChildrenInOrder12 shouldBe expectedChildNodesInOrder12

    val allGrandChild12 = child12.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild12.size shouldBe 6

    val allGrandChildNodes12 = allGrandChild12.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes12.mkString(",")}")
    val expectedGrandChildren12 = List(6, 8, 10, 12, 15, 17)
    expectedGrandChildren12 shouldBe (allGrandChildNodes12)

    val allLeaves12 = testTree.getLeaves()
    // println(s"Leaves: ${allLeaves12.mkString(",")}")
    val expectedLeaves12 =
      List(4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17, 18)
    allLeaves12 shouldBe expectedLeaves12

    println("Adding 19,20,21,22,23,25")
    testTree.add(19, 191)
    testTree.add(20, 201)
    testTree.add(21, 211)
    testTree.add(22, 221)
    testTree.add(23, 231)
    testTree.add(25, 251)

    val treeHt15 = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt15}")
    treeHt15 shouldBe 4

    val nodes15 = testTree.getNodes()
    // println(s"Root Nodes: ${nodes15.mkString(",")}")
    nodes15 shouldEqual (List(14))

    val child15 = testTree.getImmediateIndexNodes()
    child15.size shouldBe 2

    val allChildrenInOrder15 = child15.flatMap(_.listOfNodes)
    // println(s"Children: ${allChildrenInOrder15.mkString(",")}")
    val expectedChildNodesInOrder15 = ListBuffer(9, 18)
    allChildrenInOrder15 shouldBe expectedChildNodesInOrder15

    val allGrandChild15 = child15.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild15.size shouldBe 4

    val allGrandChildNodes15 = allGrandChild15.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes15.mkString(",")}")
    val expectedGrandChildren15 = List(7, 11, 16, 20, 22)
    expectedGrandChildren15 shouldBe (allGrandChildNodes15)

    val allGGChild15 = allGrandChild15.flatMap(TreeOps.immediateIndexNodes(_))
    allGGChild15.size shouldBe 9
    val allGGrandChildNodes15 = allGGChild15.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGGrandChildNodes15.mkString(",")}")
    val expectedGGrandChildren15 = List(6, 8, 10, 12, 15, 17, 19, 21, 23)
    expectedGGrandChildren15 shouldBe (allGGrandChildNodes15)

    val allLeaves15 = testTree.getLeaves()
    // println(s"Leaves: ${allLeaves15.mkString(",")}")
    val expectedLeaves15 = List(4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17,
      18, 19, 20, 21, 22, 23, 25)
    allLeaves15 shouldBe expectedLeaves15

    println("Adding 3,2,1")
    testTree.add(3, 3)
    testTree.add(1, 1)
    testTree.add(2, 2)

    val treeHt16 = testTree.treeHeight()
    // println(s"Tree Height : ${treeHt16}")
    treeHt16 shouldBe 4

    val nodes16 = testTree.getNodes()
    // println(s"Root Nodes: ${nodes16.mkString(",")}")
    nodes15 shouldEqual (List(14))

    val child16 = testTree.getImmediateIndexNodes()
    child16.size shouldBe 2

    val allChildrenInOrder16 = child16.flatMap(_.listOfNodes)
    // println(s"Children: ${allChildrenInOrder16.mkString(",")}")
    val expectedChildNodesInOrder16 = ListBuffer(9, 18)
    allChildrenInOrder16 shouldBe expectedChildNodesInOrder16

    val allGrandChild16 = child16.flatMap(TreeOps.immediateIndexNodes(_))
    allGrandChild16.size shouldBe 4

    val allGrandChildNodes16 = allGrandChild16.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGrandChildNodes16.mkString(",")}")
    val expectedGrandChildren16 = List(4, 7, 11, 16, 20, 22)
    expectedGrandChildren16 shouldBe (allGrandChildNodes16.sorted)

    val allGGChild16 = allGrandChild16.flatMap(TreeOps.immediateIndexNodes(_))
    allGGChild15.size shouldBe 9
    val allGGrandChildNodes16 = allGGChild16.flatMap(_.listOfNodes)
    // println(s"GrandChildren = ${allGGrandChildNodes16.mkString(",")}")
    val expectedGGrandChildren16 = List(2, 6, 8, 10, 12, 15, 17, 19, 21, 23)
    expectedGGrandChildren16 shouldBe (allGGrandChildNodes16)

    val allLeaves16 = testTree.getLeaves()
    // println(s"Leaves: ${allLeaves16.mkString(",")}")
    val expectedLeaves16 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15,
      16, 17, 18, 19, 20, 21, 22, 23, 25)
    allLeaves16 shouldBe expectedLeaves16

    val foundFor19 = testTree.find(19)
    println(s"Found value for key 19 ${foundFor19}")
    val expectedFor19 = Option(191)
    foundFor19 shouldBe (expectedFor19)

    testRangeQueriesOnTree(testTree)

  }

  def testRangeQueriesOnTree(tree: BPlusTree[Int, Int]) = {

    val range1 = tree.range(5, 7)
    println(s"Range1 found ${range1.mkString(",")}")
    val expectedValueForRange1 = List(5, 6, 7)
    range1 shouldBe (expectedValueForRange1)
    val range2 = tree.range(15, 17)
    println(s"Range2 found ${range2.mkString(",")}")
    val expectedValueForRange2 = List(15, 161, 171)
    range2 shouldBe (expectedValueForRange2)

  }

}
