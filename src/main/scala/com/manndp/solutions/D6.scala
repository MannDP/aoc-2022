package com.manndp.solutions

import scala.collection.mutable

object D6 extends Solution {
  private def solution(input: Seq[String], sequenceSize: Int): Result = {
    val line = input.head

    val prefix = line.splitAt(sequenceSize)._1
    val seen = mutable.Map[Char, Int]().withDefault(_ => 0)
    for (c <- prefix) {
      seen(c) += 1
    }

    var res: Option[Int] = None
    (sequenceSize until line.length).foreach(i => {
      if (seen.size == sequenceSize && res.isEmpty) {
        res = Some(i)
      } else {
        val c = line(i)
        val o = line(i - sequenceSize)
        seen(o) -= 1
        if (seen(o) == 0) {
          seen.remove(o)
        }
        seen(c) += 1
      }
    })

    assert(res.isDefined)
    ScalarResult(res.get)
  }

  override def solve1(input: Seq[String]): Result = {
    solution(input, 4)
  }

  override def solve2(input: Seq[String]): Result = {
    solution(input, 14)
  }
}
