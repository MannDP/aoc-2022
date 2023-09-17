import com.manndp.solutions.{D1, D2, MultiResult, ScalarResult}
import org.scalatest.funsuite.AnyFunSuite

import java.nio.file.Paths
import scala.io.Source
import scala.util.Using

class SolutionSuite extends AnyFunSuite {
  def fileToSeq(
      path: String,
      emptyTrailingLine: Boolean = false
  ): Seq[String] = {
    var lines: IndexedSeq[String] = IndexedSeq.empty
    Using(Source.fromFile(path)) { source =>
      lines = source.getLines().toIndexedSeq
    }
    assert(lines.nonEmpty, s"Test failed to read input file, ${path}")
    if (emptyTrailingLine) lines = lines ++ Seq("")
    lines
  }

  def getFilePath(name: String): String = {
    s"src/main/resources/inputs/${name}"
  }

  test("Day 1, Puzzle 1") {
    for (
      (inputFile, expected) <- Seq("d1s1.txt", "day1.txt") zip Seq(
        24_000,
        70509
      )
    ) {
      assert(
        D1.solve1(fileToSeq(getFilePath(inputFile), true)) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 1, Puzzle 2") {
    for (
      (inputFile, expected) <- Seq("d1s1.txt", "day1.txt") zip Seq(
        45_000,
        208_567
      )
    ) {
      assert(
        D1.solve2(fileToSeq(getFilePath(inputFile), true)) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 2, Puzzle 1") {
    for (
      (inputFile, expected) <- Seq("d2s1.txt", "day2.txt") zip Seq(
        15,
        11767
      )
    ) {
      assert(
        D2.solve1(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 2, Puzzle 2") {
    for (
      (inputFile, expected) <- Seq("d2s1.txt", "day2.txt") zip Seq(
        12,
        13886,
      )
    ) {
      assert(
        D2.solve2(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }
}
