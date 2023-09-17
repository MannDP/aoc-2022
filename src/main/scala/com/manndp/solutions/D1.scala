package com.manndp.solutions
import java.lang.Math

object D1 extends Solution {
  override def solve1(input: Seq[String]): Result = {
    var res, aggr = 0
    for (line <- input) {
      if (line.isEmpty) {
        res = Math.max(res, aggr)
        aggr = 0
      } else {
        aggr += line.toInt
      }
    }
    ScalarResult(res)
  }

  override def solve2(input: Seq[String]): Result = {
    var top3: Seq[Int] = Seq(0, 0, 0)
    var aggr = 0
    for (line <- input) {
      if (line.isEmpty) {
        // from a set of 4, choose the top 3 values
        top3 = (top3 ++ Seq(aggr)).sortBy(_ * -1).take(3)
        aggr = 0
      } else {
        aggr += line.toInt
      }
    }

    ScalarResult(top3.sum)
  }

  private def swap(data: (Int, Int)): (Int, Int) = data.swap
}
