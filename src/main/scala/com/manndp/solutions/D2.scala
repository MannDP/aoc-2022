package com.manndp.solutions

import scala.collection.immutable.Map

object D2 extends Solution {
  private val shapeScore = Map(
    "X" -> 1,
    "Y" -> 2,
    "Z" -> 3
  )

  private val outcomeScore = Map(
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

  override def solve2(input: Seq[String]): Result = {
    val moveMap = Map(
      "A" -> Map("X" -> "Z", "Y" -> "X", "Z" -> "Y"),
      "B" -> Map("X" -> "X", "Y" -> "Y", "Z" -> "Z"),
      "C" -> Map("X" -> "Y", "Y" -> "Z", "Z" -> "X")
    )

    ScalarResult(input.foldLeft(0)((acc, line) => {
      val parts = line.split(" ")
      val move = moveMap(parts(0))(parts(1))
      val moveScore = shapeScore(move)
      val roundScore = outcomeScore(parts(0))(move)
      acc + moveScore + roundScore
    }))
  }
}
