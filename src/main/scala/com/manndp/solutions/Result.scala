package com.manndp.solutions

sealed trait Result {
  def print(): Unit
}

case class ScalarResult[T](value: T) extends Result {
  override def print(): Unit = println(value)
}

case class MultiResult[T](value: Seq[T]) extends Result {
  override def print(): Unit = {
    for (v <- value) println(v)
  }
}
