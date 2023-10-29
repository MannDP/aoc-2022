package com.manndp.solutions

import scala.collection.mutable

object D11 extends Solution {
  private val digitRegex = """\d+""".r

  enum Operation:
    case Add, Multiply

  private case class Monkey(
      items: mutable.Queue[Long],
      op: Operation,
      opArg: Int | String,
      testArg: Int,
      trueRecipient: Int,
      falseRecipient: Int,
      var inspectCount: Long = 0
  ) {
    def playRound(monkeys: Seq[Monkey], preTestAdjuster: Long => Long): Unit = {
      val count = items.size
      inspectCount += count
      for (_ <- 0 until count) {
        // calculate
        var worry = items.dequeue()
        val operand = opArg match {
          case i: Int => i.toLong
          case s: String => worry
        }
        worry = op match {
          case Operation.Add => worry + operand
          case Operation.Multiply => worry * operand
        }
        // adjust before test
        worry = preTestAdjuster(worry)
        // test
        val recipient = if (worry % testArg == 0) {
          trueRecipient
        } else {
          falseRecipient
        }
        // throw
        monkeys(recipient).items.enqueue(worry)
      }
    }
  }

  private def extractInt(line: String): Int = digitRegex.findFirstIn(line).map(_.toInt).get

  private def parseMonkeys(input: Seq[String]): Array[Monkey] = {
    input
      .filter(_.nonEmpty)
      .grouped(6)
      .map(group => {
        val startingItems = digitRegex.findAllIn(group(1)).map(_.toLong).toList
        val op: Operation = if (group(2).contains('+')) {
          Operation.Add
        } else {
          Operation.Multiply
        }
        val opArg = if (group(2).contains("old * old")) {
          "old"
        } else extractInt(group(2))
        val testArg = extractInt(group(3))
        val trueRecipient = extractInt(group(4))
        val falseRecipient = extractInt(group(5))

        Monkey(mutable.Queue(startingItems: _*), op, opArg, testArg, trueRecipient, falseRecipient)
      })
      .toArray
  }

  private def getResult(monkeys: Seq[Monkey]): Result = ScalarResult(monkeys.map(_.inspectCount).sortBy(identity).takeRight(2).product)

  def solve1(input: Seq[String]): Result = {
    val monkeys = parseMonkeys(input)
    for (_ <- 0 until 20) {
      for (m <- monkeys) {
        m.playRound(monkeys, i => i / 3)
      }
    }

    getResult(monkeys)
  }

  def solve2(input: Seq[String]): Result = {
    val monkeys = parseMonkeys(input)
    val lcm = monkeys.foldLeft(1)((acc, monkey) => acc * monkey.testArg)

    for (_ <- 0 until 10000) {
      for (m <- monkeys) {
        m.playRound(monkeys, i => i % lcm)
      }
    }

    getResult(monkeys)
  }
}
