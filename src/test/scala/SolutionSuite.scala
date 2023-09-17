import com.manndp.solutions.{D1, MultiResult, ScalarResult}
import org.scalatest.funsuite.AnyFunSuite

import java.nio.file.Paths
import scala.io.Source
import scala.util.Using

class SolutionSuite extends AnyFunSuite {
  def fileToSeq(path: String): Seq[String] = {
    var lines: IndexedSeq[String] = IndexedSeq.empty
    Using(Source.fromFile(path)) { source =>
      lines = source.getLines().toIndexedSeq
    }
    assert(lines.nonEmpty, s"Test failed to read input file, ${path}")
    lines
  }

  def getFilePath(name: String): String = {
    s"src/main/resources/inputs/${name}"
  }

  test("Day 1 sample case") {
    D1(fileToSeq(getFilePath("d1s1.txt"))) match {
      case ScalarResult(value) => assert(value == 24_000)
      case _                   => assert(false)
    }
  }
}
