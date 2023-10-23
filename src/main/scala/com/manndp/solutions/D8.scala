package com.manndp.solutions

import scala.collection.mutable

type coord = (Int, Int)

object D8 extends Solution {
  private def getTreeHeights(input: Seq[String]): Seq[Seq[Int]] = {
    input.map(line => {
      line.map(c => c - '0')
    })
  }

  override def solve1(input: Seq[String]): Result = {
    val treeHeights = getTreeHeights(input)
    val rows = treeHeights.size
    val cols = treeHeights.head.size

    val visible = mutable.Set[coord]()

    // pass 1, process visible from left and above
    val topMaxs = mutable.ArrayBuffer[Int]()
    topMaxs ++= treeHeights.head
    for (rowIdx <- Range(1, rows - 1)) {
      var leftMax = treeHeights(rowIdx).head
      for (colIdx <- Range(1, cols - 1)) {
        val height = treeHeights(rowIdx)(colIdx)
        if (height > leftMax || height > topMaxs(colIdx)) {
          visible.add((rowIdx, colIdx))
        }
        leftMax = Math.max(leftMax, height)
         topMaxs(colIdx) = Math.max(topMaxs(colIdx), height)
      }
    }

    // pass 2, process visible from right and below
    val bottomMaxs = mutable.ArrayBuffer[Int]()
    bottomMaxs ++= treeHeights.last
    for (rowIdx <- Range(rows - 2, 0, -1)) {
      var rightMax = treeHeights(rowIdx).last
      for (colIdx <- Range(cols - 2, 0, -1)) {
        val height = treeHeights(rowIdx)(colIdx)
        if (height > rightMax || height > bottomMaxs(colIdx)) {
          visible.add((rowIdx, colIdx))
        }
        rightMax = Math.max(rightMax, height)
        bottomMaxs(colIdx) = Math.max(bottomMaxs(colIdx), height)
      }
    }

    val total = visible.size + rows * 2 + (cols - 2) * 2
    ScalarResult(total)
  }

  override def solve2(input: Seq[String]): Result = {
    val treeHeights = getTreeHeights(input)

    ScalarResult(100)
  }
}
