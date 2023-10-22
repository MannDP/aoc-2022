package com.manndp.solutions
import scala.collection.mutable

object D7 extends Solution {
  private val cdRegex = """\$ cd (\S+)""".r
  private val dirRegex = """dir (\S+)""".r
  private val fileRegex = """(\d+) (\S+)""".r

  private sealed trait FileSystemElement {
    def getSize: Int
    def populateSize(
        acc: mutable.ArrayBuffer[FileSystemElement],
        f: FileSystemElement => Boolean
    ): Unit = {}
  }

  private case class File(name: String, size: Int) extends FileSystemElement {
    override def getSize: Int = size
  }

  private case class Directory(name: String) extends FileSystemElement {
    private var size: Int = 0
    private val children: mutable.Map[String, FileSystemElement] =
      mutable.Map[String, FileSystemElement]()

    def getChild(name: String): FileSystemElement = children(name)
    def addChild(name: String, f: FileSystemElement): Unit =
      children += (name -> f)
    override def getSize: Int = size

    override def populateSize(
        acc: mutable.ArrayBuffer[FileSystemElement],
        f: FileSystemElement => Boolean
    ): Unit = {
      var total = 0
      for ((_, ch) <- children) {
        ch.populateSize(acc, f)
        total += ch.getSize
      }
      size = total
      if (f(this)) {
        acc += this
      }
    }
  }

  private def findDirectories(
      input: Seq[String],
      f: FileSystemElement => Boolean
  ): (FileSystemElement, mutable.ArrayBuffer[FileSystemElement]) = {
    val acc = mutable.ArrayBuffer[FileSystemElement]()
    val root = Directory("/")
    val fileStack = mutable.Stack[FileSystemElement](root)

    def resetStack(): Unit = {
      fileStack.popWhile(_ => fileStack.size != 1)
    }

    def getTop: Directory = {
      fileStack.top match {
        case d: Directory => d
        case _            => assert(false)
      }
    }

    for (line <- input.tail) {
      line match {
        case cdRegex(dirName) =>
          dirName match {
            case "/"  => resetStack()
            case ".." => fileStack.pop()
            case _    => fileStack.push(getTop.getChild(dirName))
          }
        case dirRegex(dirName) =>
          val dir = Directory(dirName)
          getTop.addChild(dirName, dir)
        case fileRegex(fileSize, fileName) =>
          val file = File(fileName, fileSize.toInt)
          getTop.addChild(fileName, file)
        case "$ ls" => ()
        case _      => assert(false)
      }
    }

    root.populateSize(acc, f)
    (root, acc)
  }

  override def solve1(input: Seq[String]): Result = {
    val (_, dirs) = findDirectories(input, f => f.getSize <= 100000)
    ScalarResult(dirs.foldLeft(0)((acc, f) => {
      acc + f.getSize
    }))
  }

  override def solve2(input: Seq[String]): Result = {
    val (root, dirs) = findDirectories(input, f => true)
    val needSpace = 30000000 - (70000000 - root.getSize)

    var res: Option[Int] = None
    for (dir <- dirs) {
      val dirSize = dir.getSize
      if (dirSize >= needSpace) {
        res match {
          case Some(prevSize) =>
            if (dirSize < prevSize) res = Some(dirSize)
          case _ => res = Some(dirSize)
        }
      }
    }

    ScalarResult(res.get)
  }
}
