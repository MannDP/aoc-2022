import com.manndp.solutions.*
import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source
import scala.util.Using

class SolutionSuite extends AnyFunSuite {
  def fileToSeq(
      path: String,
      appendBlankLine: Boolean = false
  ): Seq[String] = {
    var lines: IndexedSeq[String] = IndexedSeq.empty
    Using(Source.fromFile(path)) { source =>
      lines = source.getLines().toIndexedSeq
    }
    assert(lines.nonEmpty, s"Test failed to read input file, $path")
    if (appendBlankLine) lines = lines ++ Seq("")
    lines
  }

  def getFilePath(name: String): String = s"src/main/resources/inputs/$name"

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
        13886
      )
    ) {
      assert(
        D2.solve2(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 3, Puzzle 1") {
    for (
      (inputFile, expected) <- Seq("d3s1.txt", "day3.txt") zip Seq(
        157,
        7826
      )
    ) {
      assert(
        D3.solve1(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 3, Puzzle 2") {
    for (
      (inputFile, expected) <- Seq("d3s1.txt", "day3.txt") zip Seq(
        70,
        2577
      )
    ) {
      assert(
        D3.solve2(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 4, Puzzle 1") {
    for (
      (inputFile, expected) <- Seq("d4s1.txt", "day4.txt") zip Seq(
        2,
        547
      )
    ) {
      assert(
        D4.solve1(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 4, Puzzle 2") {
    for (
      (inputFile, expected) <- Seq("d4s1.txt", "day4.txt") zip Seq(
        4,
        843
      )
    ) {
      assert(
        D4.solve2(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 5, Puzzle 1") {
    for (
      (inputFile, expected) <- Seq("d5s1.txt", "day5.txt") zip Seq(
        "CMZ",
        "MQSHJMWNH"
      )
    ) {
      assert(
        D5.solve1(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 5, Puzzle 2") {
    for (
      (inputFile, expected) <- Seq("d5s1.txt", "day5.txt") zip Seq(
        "MCD",
        "LLWJRBHVZ"
      )
    ) {
      assert(
        D5.solve2(fileToSeq(getFilePath(inputFile))) == ScalarResult(
          expected
        )
      )
    }
  }

  test("Day 6, Puzzle 1") {
    assert(D6.solve1(Seq("bvwbjplbgvbhsrlpgdmjqwftvncz")) == ScalarResult(5))
    assert(D6.solve1(Seq("nppdvjthqldpwncqszvftbrmjlhg")) == ScalarResult(6))
    assert(
      D6.solve1(Seq("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == ScalarResult(10)
    )
    assert(
      D6.solve1(Seq("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == ScalarResult(11)
    )
    assert(D6.solve1(fileToSeq(getFilePath("day6.txt"))) == ScalarResult(1929))
  }

  test("Day 6, Puzzle 2") {
    assert(D6.solve2(Seq("mjqjpqmgbljsphdztnvjfqwrcgsmlb")) == ScalarResult(19))
    assert(D6.solve2(Seq("bvwbjplbgvbhsrlpgdmjqwftvncz")) == ScalarResult(23))
    assert(D6.solve2(Seq("nppdvjthqldpwncqszvftbrmjlhg")) == ScalarResult(23))
    assert(
      D6.solve2(Seq("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")) == ScalarResult(29)
    )
    assert(
      D6.solve2(Seq("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")) == ScalarResult(26)
    )
    assert(D6.solve2(fileToSeq(getFilePath("day6.txt"))) == ScalarResult(3298))
  }

  test("Day 7, Puzzle 1") {
    assert(
      D7.solve1(fileToSeq(getFilePath("day7.txt"))) == ScalarResult(1749646)
    )
  }

  test("Day 7, Puzzle 2") {
    assert(
      D7.solve2(fileToSeq(getFilePath("day7.txt"))) == ScalarResult(1498966)
    )
  }

  test("Day 8, Puzzle 1") {
    assert(
      D8.solve1(fileToSeq(getFilePath("d8s1.txt"))) == ScalarResult(21)
    )
    assert(
      D8.solve1(fileToSeq(getFilePath("day8.txt"))) == ScalarResult(1698)
    )
  }

  test("Day 8, Puzzle 2") {
    assert(
      D8.solve2(fileToSeq(getFilePath("d8s1.txt"))) == ScalarResult(8)
    )
    assert(
      D8.solve2(fileToSeq(getFilePath("day8.txt"))) == ScalarResult(672280)
    )
  }

  test("Day 9, Puzzle 1") {
    assert(
      D9.solve1(fileToSeq(getFilePath("d9s1.txt"))) == ScalarResult(13)
    )
    assert(
      D9.solve1(fileToSeq(getFilePath("day9.txt"))) == ScalarResult(6486)
    )
  }

  test("Day 9, Puzzle 2") {
    assert(
      D9.solve2(fileToSeq(getFilePath("d9s2.txt"))) == ScalarResult(36)
    )
    assert(
      D9.solve2(fileToSeq(getFilePath("day9.txt"))) == ScalarResult(2678)
    )
  }

  test("Day 10, Puzzle 1") {
    assert(
      D10.solve1(fileToSeq(getFilePath("day10.txt"))) == ScalarResult(15220)
    )
  }

  test("Day 10, Puzzle 2") {
    assert(
      D10.solve2(fileToSeq(getFilePath("day10.txt"))) == ScalarResult(
        Set(1, 2, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18, 20, 23, 25, 26,
          27, 30, 31, 32, 33, 36, 37, 40, 43, 45, 53, 55, 60, 62, 65, 68, 70,
          75, 78, 80, 83, 85, 86, 87, 92, 95, 96, 97, 100, 101, 105, 106, 107,
          110, 111, 112, 115, 118, 120, 121, 122, 125, 131, 135, 140, 142, 145,
          148, 150, 155, 156, 157, 158, 160, 162, 165, 170, 175, 180, 182, 185,
          188, 190, 195, 198, 200, 203, 205, 210, 211, 212, 213, 215, 216, 217,
          218, 220, 223, 225, 226, 227, 230, 235, 238)
      )
    )
  }

  test("Day 11, Puzzle 1") {
    assert(D11.solve1(fileToSeq(getFilePath("day11.txt"))) == ScalarResult(108240))
  }

  test("Day 11, Puzzle 2") {
    assert(D11.solve2(fileToSeq(getFilePath("day11.txt"))) == ScalarResult(25712998901L))
  }

  test("Day 12, Puzzle 1") {
    assert(D12.solve1(fileToSeq(getFilePath("d12s1.txt"))) == ScalarResult(31))
    assert(D12.solve1(fileToSeq(getFilePath("day12.txt"))) == ScalarResult(534))
  }

  test("Day 12, Puzzle 2") {
    assert(D12.solve2(fileToSeq(getFilePath("d12s1.txt"))) == ScalarResult(29))
    assert(D12.solve2(fileToSeq(getFilePath("day12.txt"))) == ScalarResult(525))
  }
}
