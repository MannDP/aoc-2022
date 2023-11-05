package com.manndp.solutions
import scala.collection.mutable

object D12 extends Solution {
  private type Location = (Int, Int)

  // (elevationMap, start, end)
  private def parseInput(
      input: Seq[String]
  ): (Location, Location, Seq[Seq[Character]]) = {
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
    (startOpt.get, endOpt.get, elevationMap)
  }

  private def bfs(
      start: Location,
      targetPredicate: Location => Boolean,
      neighborPredicate: (Character, Character) => Boolean,
      elevationMap: Seq[Seq[Character]]
  ): (Int, Boolean) = {
    val rows = elevationMap.size
    val cols = elevationMap.head.length

    // neighbors are processed on the incremented step count, so don't have to store it
    val visited = mutable.Set[Location]()
    val queue = mutable.Queue[Location]()
    visited.add(start)
    queue.enqueue(start)
    var done = false
    var step = 0
    while (!done && queue.nonEmpty) {
      val count = queue.size
      for (_ <- 0 until count if !done) {
        // process the entire frontier
        val currentLocation = queue.dequeue()
        val currentElevation =
          elevationMap(currentLocation._1)(currentLocation._2)
        if (targetPredicate(currentLocation)) {
          done = true
        } else {
          Seq((1, 0), (0, 1), (-1, 0), (0, -1))
            .map(delta =>
              (currentLocation._1 + delta._1, currentLocation._2 + delta._2)
            )
            .filter(loc =>
              loc._1 >= 0 && loc._1 < rows && loc._2 >= 0 && loc._2 < cols
            )
            .filter(loc =>
              neighborPredicate(currentElevation, elevationMap(loc._1)(loc._2))
            )
            .filter(loc => !visited.contains(loc))
            .foreach(loc => {
              visited.add(loc)
              queue.enqueue(loc)
            })
        }
      }

      if (!done) step += 1
    }

    (step, done)
  }

  override def solve1(input: Seq[String]): Result = {
    val (start, end, map) = parseInput(input)
    val (sol, found) =
      bfs(start, loc => loc == end, (ch, nh) => nh <= ch + 1, map)
    assert(found)
    ScalarResult(sol)
  }

  override def solve2(input: Seq[String]): Result = {
    // BFS backwards starting from the end position
    val (_, end, map) = parseInput(input)
    val (sol, found) =
      bfs(end, loc => map(loc._1)(loc._2) == 'a', (ch, nh) => nh >= ch - 1, map)
    ScalarResult(sol)
  }
}
