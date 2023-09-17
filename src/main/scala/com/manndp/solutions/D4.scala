package com.manndp.solutions

object D4 extends Solution {
  override def solve1(input: Seq[String]): Result = {
    ScalarResult(input.count { line =>
      val parts = line.split(",")
      val (s1, e1) = parseInterval(parts(0))
      val (s2, e2) = parseInterval(parts(1))

      (s1 <= s2 && e1 >= e2) || (s2 <= s1 && e1 <= e2)
    })
  }

  override def solve2(input: Seq[String]): Result = {
    ScalarResult(input.count { line =>
      val parts = line.split(",")
      var (s1, e1) = parseInterval(parts(0))
      var (s2, e2) = parseInterval(parts(1))
      if (s1 > s2) {
        val (t1, t2) = (s1, e1)
        s1 = s2
        e1 = e2
        s2 = t1
        e2 = t2
      }
      e1 >= s2
    })
  }

  private def parseInterval(in: String): (Int, Int) = {
    val parts = in.split("-")
    (parts(0).toInt, parts(1).toInt)
  }
}
