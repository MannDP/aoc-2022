package com.manndp.solutions

import scala.collection.mutable

object D9 extends Solution {
  private val motionRegex = """(.) (\d+)""".r

  case class Position(var x: Int, var y: Int)

  override def solve1(input: Seq[String]): Result = {
    val head = Position(0, 0)
    val tail = Position(0, 0)
    val visited = mutable.Set[Position]()
    visited.add(tail)

    def moveHead(head: Position, delta: (Int, Int)): Unit = {
      head.x += delta(0)
      head.y += delta(1)
    }

    def moveTail(head: Position, tail: Position): Unit = {
      // calculate delta
      val deltaX = head.x - tail.x
      val deltaY = head.y - tail.y

      if (deltaX == 0) {
        // move tail up/down
        if (deltaY > 0) {
          tail.y += 1
        } else {
          tail.y -= 1
        }
      } else if (deltaY == 0) {
        // move tail left/right
        if (deltaX > 0) {
          tail.x += 1
        } else {
          tail.x -= 1
        }
      } else {
        if (deltaX > 0 && deltaY > 0) {
          // diag up-right
          tail.x += 1
          tail.y += 1
        } else if (deltaX < 0 && deltaY > 0) {
          // diag up-left
          tail.x -= 1
          tail.y += 1
        } else if (deltaX > 0) {
          // diag bottom-right
          tail.x += 1
          tail.y -= 1
        } else {
          tail.x -= 1
          tail.y -= 1
        }
      }
    }

    def isTouch(head: Position, tail: Position): Boolean = {
      // touch if diagonally adjacent, or overlap
      val deltaX = Math.abs(head.x - tail.x)
      val deltaY = Math.abs(head.y - tail.y)
      val delta = deltaX + deltaY

      delta <= 1 || (deltaX == 1 && deltaY == 1)
    }

    for (line <- input) {
      line match {
        case motionRegex(direction, count) =>
          // do each move one at a time
          val delta = direction match {
            case "L" => (-1, 0)
            case "R" => (1, 0)
            case "U" => (0, 1)
            case "D" => (0, -1)
          }

          for (_ <- 0 until count.toInt) {
            moveHead(head, delta)
            if (!isTouch(head, tail)) {
              moveTail(head, tail)
              visited.add(tail)
            }
          }
      }
    }

    ScalarResult(visited.size)
  }

  override def solve2(input: Seq[String]): Result = {
    ScalarResult(1)
  }
}
