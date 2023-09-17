package com.manndp.solutions
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Queue}

object D5 extends Solution {
  override def solve1(input: Seq[String]): Result = {
    val emptyLineIdx = input.indexOf("")
    val (inputConfig, inputMoves) = input.splitAt(emptyLineIdx)

    val stackCount =
      inputConfig.last.split("\\s+").filter(!_.isBlank).map(_.toInt).last
    val state: Array[mutable.Queue[Char]] =
      Array.ofDim[mutable.Queue[Char]](stackCount)

    // Initialize the stacks
    inputConfig
      .dropRight(1)
      .foreach(line => {
        for (
          idx <- Range(1, (stackCount * 3 + stackCount - 1), 4)
            .filter(_ < line.length)
        ) {
          val item = line(idx)
          if (!item.isWhitespace) {
            val stackIdx = (idx - 1) / 4
            if (state(stackIdx) == null) state(stackIdx) = mutable.Queue()
            state(stackIdx).append(item)
          }
        }
      })

    // Process the stacks
    val pattern = "move (\\d+) from (\\d+) to (\\d+)".r
    inputMoves.tail.foreach {
      case pattern(countStr, fromStackStr, toStackStr) => {
        val count = countStr.toInt
        val fromStack = fromStackStr.toInt - 1
        val toStack = toStackStr.toInt - 1
        for (_ <- Range(0, count)) {
          state(toStack).prepend(state(fromStack).dequeue())
        }
      }
      case _ => assert(false)
    }

    val topLine = state
      .map(stack => {
        stack.head
      })

    ScalarResult(topLine.mkString)
  }

  override def solve2(input: Seq[String]): Result = ???
}
