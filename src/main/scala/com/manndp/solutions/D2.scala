package com.manndp.solutions

object D2 extends Solution {
  private val shapeScore: Map[String, Int] = Map(
    "X" -> 1,
    "Y" -> 2,
    "Z" -> 3
  )

  private val outcomeScore: Map[String, Map[String, Int]] = Map(
    "A" -> Map("X" -> 3, "Y" -> 6, "Z" -> 0),
    "B" -> Map("X" -> 0, "Y" -> 3, "Z" -> 6),
    "C" -> Map("X" -> 6, "Y" -> 0, "Z" -> 3)
  )

  override def solve1(input: Seq[String]): Result = {
    ScalarResult(input.foldLeft(0)((acc, line) => {
      val parts = line.split(" ")
      val moveScore = shapeScore(parts(1))
      val roundScore = outcomeScore(parts(0))(parts(1))
      acc + moveScore + roundScore
    }))
  }

  override def solve2(input: Seq[String]): Result = ???
}
