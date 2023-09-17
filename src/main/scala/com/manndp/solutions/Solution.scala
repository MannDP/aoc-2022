package com.manndp.solutions

trait Solution {
  def solve(input: Seq[String]): Result
  def apply(input: Seq[String]): Result = solve(input)
}
