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

  private def findShortestPath(
      start: Location,
      end: Location,
      elevationMap: Seq[Seq[Character]]
  ): (Int, Boolean) = {
    val rows = elevationMap.size
    val cols = elevationMap.head.length

    // implement bfs, since minimizing number of steps
    // definition of a neighbor constrained by indices out of bounds, and relative elevations
    val visited = mutable.Set[Location]()
    val queue = mutable.Queue[Location]()
    visited.add(start)
    queue.enqueue(start)
    var done = false
    var step = 0
    while (!done && queue.nonEmpty) {
      // for the general case, there may not be a path from every start to an end
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

    (step, done)
  }

  override def solve1(input: Seq[String]): Result = {
    val (start, end, map) = parseInput(input)
    val (sol, _) = findShortestPath(start, end, map)
    ScalarResult(sol)
  }

  override def solve2(input: Seq[String]): Result = {
    // this BFS solution is O(VE(V+E)), ie. O(n^3)
    // matches the runtime of Floyd-Warshall (which yields all pairs shortest path)
    // indeed, a more efficient solution to the algorithm exists, by reformulating the end position as the starting point
    // and then traversing backwards to find any 'a'
    // Dijkstra's implements this in O(n^2), and is a formalization able to handle the first part of the problem
    // TODO: manndp implement O(n^2)

    var solution: Option[Int] = None
    val startLocations: Seq[Location] = input.zipWithIndex.flatMap {
      case (row, rowIndex) =>
        row.zipWithIndex.collect { case ('a', colIndex) =>
          (rowIndex, colIndex)
        }
    }
    val (_, end, map) = parseInput(input)
    for (location <- startLocations) {
      val (steps, valid) = findShortestPath(location, end, map)
      if (valid) {
        solution match {
          case Some(value) if steps < value => solution = Some(steps)
          case None                         => solution = Some(steps)
          case _                            => ()
        }
      }
    }

    ScalarResult(solution.get)
  }
}
