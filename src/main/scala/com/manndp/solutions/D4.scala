package com.manndp.solutions

object D4 extends Solution {
  private def parseInterval(in: String): (Int, Int) = {
    val parts = in.split("-")
    (parts(0).toInt, parts(1).toInt)
  }

  override def solve1(input: Seq[String]): Result = {
    ScalarResult(input.count { line =>
      val parts = line.split(",")
      val (s1, e1) = parseInterval(parts(0))
      val (s2, e2) = parseInterval(parts(1))

      (s1 <= s2 && e1 >= e2) || (s2 <= s1 && e1 <= e2)
    })
  }

  override def solve2(input: Seq[String]): Result = ???
}
