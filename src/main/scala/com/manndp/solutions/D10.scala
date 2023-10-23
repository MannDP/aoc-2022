package com.manndp.solutions

object D10 extends Solution {
  private val cyclesToSample = Set(20, 60, 100, 140, 180, 220)

  override def solve1(input: Seq[String]): Result = {
    var X: Int = 1
    var sumSignalStrength: Int = 0
    var cycle: Int = 1

    def track(currSignalStrength: Int, currX: Int, cycle: Int): Int = {
      if (cyclesToSample.contains(cycle)) {
        currSignalStrength + currX * cycle
      } else {
        currSignalStrength
      }
    }

    for (line <- input) {
      // in each iteration, complete the processing of the instruction
      // including updating the cycle count
      // and the return value
      line match {
        case "noop" =>
          cycle += 1
          sumSignalStrength = track(sumSignalStrength, X, cycle)
        case _ =>
          val V = line.substring(5).toInt
          cycle += 1
          sumSignalStrength = track(sumSignalStrength, X, cycle)
          cycle += 1
          X += V
          sumSignalStrength = track(sumSignalStrength, X, cycle)
      }
    }

    ScalarResult(sumSignalStrength)
  }

  override def solve2(input: Seq[String]): Result = ScalarResult(10)
}
