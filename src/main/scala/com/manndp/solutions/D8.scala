package com.manndp.solutions

import scala.collection.mutable

object D8 extends Solution {
  private type coordinate = (Int, Int)

  private def getTreeHeights(input: Seq[String]): (Seq[Seq[Int]], Int, Int) = {
    val treeHeights = input.map(line => {
      line.map(c => c - '0')
    })
    val rows = treeHeights.size
    val cols = treeHeights.head.size
    (treeHeights, rows, cols)
  }

  override def solve1(input: Seq[String]): Result = {
    val (treeHeights, rows, cols) = getTreeHeights(input)
    val visible = mutable.Set[coordinate]()

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
    // Unfortunaxtely, the brute force solution is the most efficient I can think of
    // Consider one direction as a case, right
    // DP memoization is only useful if the tree to the right is taller than you (reduces to 1)
    // if not, then you must linear scan right to find a taller tree, or until you run out of trees
    // so worst case still a linear scan
    val (treeHeights, rows, cols) = getTreeHeights(input)

    var result = 0
    for (rowIdx <- Range(1, rows - 1)) {
      for (colIdx <- Range(1, cols - 1)) {
        val height = treeHeights(rowIdx)(colIdx)

        // look left
        var leftIdx = colIdx - 1
        while (leftIdx > 0 && treeHeights(rowIdx)(leftIdx) < height) {
          leftIdx -= 1
        }

        // look right
        var rightIdx = colIdx + 1
        while (rightIdx < cols - 1 && treeHeights(rowIdx)(rightIdx) < height) {
          rightIdx += 1
        }

        // look up
        var topIdx = rowIdx - 1
        while (topIdx > 0 && treeHeights(topIdx)(colIdx) < height) {
          topIdx -= 1
        }

        // look down
        var bottomIdx = rowIdx + 1
        while (
          bottomIdx < rows - 1 && treeHeights(bottomIdx)(colIdx) < height
        ) {
          bottomIdx += 1
        }

        val leftDiff = colIdx - leftIdx
        val rightDiff = rightIdx - colIdx
        val topDiff = rowIdx - topIdx
        val bottomDiff = bottomIdx - rowIdx
        result = Math.max(result, leftDiff * rightDiff * topDiff * bottomDiff)
      }
    }

    ScalarResult(result)
  }
}
