package com.manndp.solutions
import scala.collection.mutable

object D12 extends Solution {
  private type Location = (Int, Int)

  override def solve1(input: Seq[String]): Result = {
    // parse the input map
    // turn 'S' -> 'a'
    // turn 'E' -> 'z'
    // keep track of 'S', 'E' positions
    var startOpt: Option[Location] = None
    var endOpt: Option[Location] = None
    val elevationMap: Seq[Seq[Character]] =
      input.zipWithIndex.map((line, rIdx) => {
        line.zipWithIndex.map((elevation, cIdx) => {
          elevation match {
            case 'S' =>
              startOpt = Some((rIdx, cIdx))
              'a'
            case 'E' =>
              endOpt = Some((rIdx, cIdx))
              'z'
            case _ => elevation
          }
        })
      })
    val rows = elevationMap.size
    val cols = elevationMap.head.size
    val start = startOpt.get
    val end = endOpt.get

    // implement bfs, since minimizing number of steps
    // definition of a neighbor constrained by indices out of bounds, and relative elevations
    val visited = mutable.Set[Location]()
    val queue = mutable.Queue[Location]()
    visited.add(start)
    queue.enqueue(start)
    var done = false
    var step = 0
    while (!done) {
      assert(queue.nonEmpty)
      val count = queue.size
      for (_ <- 0 until count if !done) {
        // process the entire frontier
        val currentLocation = queue.dequeue()
        val currentElevation =
          elevationMap(currentLocation._1)(currentLocation._2)
        if (currentLocation == end) {
          done = true
        } else {
          // neighbors are processed on the incremented step count
          // that represents the number of edges traversed
          Seq((1, 0), (0, 1), (-1, 0), (0, -1))
            .map(delta =>
              (currentLocation._1 + delta._1, currentLocation._2 + delta._2)
            )
            .filter(loc =>
              loc._1 >= 0 && loc._1 < rows && loc._2 >= 0 && loc._2 < cols
            )
            .filter(loc => currentElevation + 1 >= elevationMap(loc._1)(loc._2))
            .filter(loc => !visited.contains(loc))
            .foreach(loc => {
              visited.add(loc)
              queue.enqueue(loc)
            })
        }
      }

      if (!done) step += 1
    }

    ScalarResult(step)
  }

  override def solve2(input: Seq[String]): Result = {
    ScalarResult(10)
  }
}
