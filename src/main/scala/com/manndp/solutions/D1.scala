package com.manndp.solutions
import java.lang.Math

object D1 extends Solution {
  override def solve(input: Seq[String]): Result = {
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
}
