package com.manndp.solutions

import scala.collection.mutable

object D10 extends Solution {
  private val cyclesToSample = Set(20, 60, 100, 140, 180, 220)

  private def parseV(line: String): Int = {
    line.substring(5).toInt
  }

  private def track(currSignalStrength: Int, currX: Int, cycle: Int): Int = {
    if (cyclesToSample.contains(cycle)) {
      currSignalStrength + currX * cycle
    } else {
      currSignalStrength
    }
  }

  override def solve1(input: Seq[String]): Result = {
    var X: Int = 1
    var sumSignalStrength: Int = 0
    var cycle: Int = 1

    for (line <- input) {
      // in each iteration, complete the processing of the instruction
      // including updating the cycle count
      // and the return value
      line match {
        case "noop" =>
          cycle += 1
          sumSignalStrength = track(sumSignalStrength, X, cycle)
        case _ =>
          val V = parseV(line)
          cycle += 1
          sumSignalStrength = track(sumSignalStrength, X, cycle)
          cycle += 1
          X += V
          sumSignalStrength = track(sumSignalStrength, X, cycle)
      }
    }

    ScalarResult(sumSignalStrength)
  }

  override def solve2(input: Seq[String]): Result = {
    // the CRT is 40 wide and 6 high
    // left-most pixel index is 0, right-most index is 39
    // X register sets the horizontal position of the middle of the sprite
    // the sprite being drawn or not is controlled only by horizontal position
    val crtWidth = 40
    val crtHeight = 6
    var X: Int = 1
    val litCycles = mutable.Set[Int]()
    var cycle: Int = 0

    def processCycle(): Unit = {
      // ALG: process the current cycle
      val normalizedCycle = cycle % crtWidth
      // calculate intersection
      if (Math.abs(X - normalizedCycle) <= 1) {
        litCycles.add(cycle)
      }
    }

    for (line <- input if cycle < 241) {
      line match {
        case "noop" =>
          cycle += 1
          processCycle()
        case _ =>
          cycle += 1
          processCycle()

          cycle += 1
          val V = parseV(line)
          X += V
          processCycle()
      }
    }

    // print with side-effects
    var counter = 1
    for (_ <- 0 until crtHeight) {
      for (_ <- 0 until crtWidth) {
        val symbol = if (litCycles.contains(counter)) {
          '#'
        } else '.'
        print(symbol)
        counter += 1
      }
      println()
    }

    ScalarResult(litCycles)
  }
}
