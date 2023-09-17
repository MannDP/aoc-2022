package com.manndp.solutions

object D3 extends Solution {
  override def solve1(input: Seq[String]): Result = {
    ScalarResult(input.foldLeft(0)((acc, line) => {
      val (firstHalf, secondHalf) = line.splitAt(line.length / 2)
      val seen = firstHalf.toSet
      val dup = secondHalf.find(c => seen.contains(c)).get
      acc + getScore(dup)
    }))
  }

  override def solve2(input: Seq[String]): Result = {
    ScalarResult(
      input
        .grouped(3)
        .foldLeft(0)((acc, group) => {
          val badge = group.tail
            .foldLeft(group.head.toSet)((acc2, line) => {
              acc2.intersect(line.toSet)
            })
            .head
          acc + getScore(badge)
        })
    )
  }

  private def getScore(c: Char): Int = {
    1 + (
      if (c.isUpper) {
        (c.toInt - 'A'.toInt) + 26
      } else {
        c.toInt - 'a'.toInt
      }
    )
  }
}
