package com.manndp.solutions

import scala.collection.mutable

object D9 extends Solution {
  private val motionRegex = """(.) (\d+)""".r

  case class Position(var x: Int, var y: Int)

  private def getDeltas(head: Position, tail: Position): (Int, Int) = {
    (head.x - tail.x, head.y - tail.y)
  }

  private def isTouch(head: Position, tail: Position): Boolean = {
    // touch if diagonally adjacent, or overlap
    var (deltaX, deltaY) = getDeltas(head, tail)
    deltaX = Math.abs(deltaX)
    deltaY = Math.abs(deltaY)
    val delta = deltaX + deltaY
    delta <= 1 || (deltaX == 1 && deltaY == 1)
  }

  private def moveHead(head: Position, delta: (Int, Int)): Unit = {
    head.x += delta(0)
    head.y += delta(1)
  }

  private def moveTail(head: Position, tail: Position): Unit = {
    val (deltaX, deltaY) = getDeltas(head, tail)

    if (isTouch(head, tail)) return

    val moveX = if (deltaX > 0) 1 else if (deltaX < 0) -1 else 0
    val moveY = if (deltaY > 0) 1 else if (deltaY < 0) -1 else 0

    tail.x += moveX
    tail.y += moveY
  }

  private def solve(input: Seq[String], numKnots: Int): ScalarResult[Int] = {
    val knots = Range(0, numKnots).map(_ => Position(0, 0))
    val visited = mutable.Set[Position]()
    visited.add(knots.last)

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
            moveHead(knots.head, delta)
            for (i <- 0 until knots.length - 1) {
              val head = knots(i)
              val tail = knots(i + 1)
              moveTail(head, tail)
            }
            visited.add(knots.last)
          }
      }
    }

    ScalarResult(visited.size)
  }

  override def solve1(input: Seq[String]): Result = {
    solve(input, 2)
  }

  override def solve2(input: Seq[String]): Result = {
    solve(input, 10)
  }
}
