import com.manndp.solutions.Solution

import java.nio.file.Paths
import scala.io.Source // read from various sources
import scala.util.Using // try-with-resources

// solutions
import com.manndp.solutions.D1

@main
def main(): Unit = {
  val inputFilePath = {
    val inputFileName = "day1.txt"
    s"src/main/resources/inputs/${inputFileName}"
  }

  var lines: IndexedSeq[String] = IndexedSeq.empty
  Using(Source.fromFile(inputFilePath)) { source =>
    lines = source.getLines().toIndexedSeq
  }

  if (lines.isEmpty) {
    System.err.println(s"Failed to read input file path: ${inputFilePath}")
    System.exit(1)
  }

   D1(lines).print()
}
