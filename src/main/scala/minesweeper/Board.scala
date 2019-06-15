package minesweeper

import scala.util.Random
import Board.neighbours

class Board(val tiles: List[List[Boolean]], val displayTiles: List[List[Char]]) {

  val xSize: Int = tiles.headOption.getOrElse(List.empty).size
  val ySize: Int = tiles.size
  val nMines: Int = tiles.map(_.count(_ == true)).sum

  def clearTile(x: Int, y: Int): Board = {
    if (tiles(y)(x)) {
      println("BOOM!")
      Board(xSize, ySize, nMines)
    }
    else if (displayTiles(y)(x) != '#') Board(tiles, displayTiles)
    else {
      val positiveDisplay = clearAllPositive(x, y, displayTiles)
      val newDisplay = clearAllNegative(x, y, positiveDisplay)
      Board(tiles, newDisplay)
    }
  }

  def clearAllPositive(x: Int, y: Int, oldDisplay: List[List[Char]]): List[List[Char]] = {
    if (checkInBoundsAndNotMine(x, y)) {
      val newTiles = updateOneTile(x, y, oldDisplay)
      val newTiles2 = clearAllPositive(x + 1, y, newTiles)
      clearAllPositive(x, y + 1, newTiles2)
    } else {
      oldDisplay
    }
  }

  def clearAllNegative(x: Int, y: Int, oldDisplay: List[List[Char]]): List[List[Char]] = {
    if (checkInBoundsAndNotMine(x, y)) {
      val newTiles = updateOneTile(x, y, oldDisplay)
      val newTiles2 = clearAllNegative(x - 1, y, newTiles)
      clearAllNegative(x, y - 1, newTiles2)
    } else {
      oldDisplay
    }
  }

  def updateOneTile(x: Int, y: Int, oldDisplay: List[List[Char]]): List[List[Char]] = {
    getSurroundingMines(x, y) match {
      case 0 => updateDisplay(x, y, ' ', oldDisplay)
      case n => updateDisplay(x, y, n.toString.head, oldDisplay)
    }
  }

  def getSurroundingMines(x: Int, y: Int): Int = {
    neighbours.count { case (yP, xP) =>
      x + xP >= 0 && x + xP < xSize && y + yP >= 0 && y + yP < ySize && tiles(y + yP)(x + xP)
    }
  }

  def updateDisplay(x: Int, y: Int, c: Char, oldDisplay: List[List[Char]]): List[List[Char]] = {
    List.tabulate(ySize, xSize)((ys, xs) =>
      if (ys == y && xs == x) c else oldDisplay(y)(x))
  }

  def hasFinishedClearing(display: List[List[Char]]): Boolean = {

    val blanks = for {
      y <- 0 until ySize
      x <- 0 until xSize
      if display(y)(x) == ' '
      (ny, nx) <- neighbours
    } yield (y + ny, x + nx)
    val allTiles = blanks.distinct.map(a => display(a._1)(a._2))
    allTiles contains '#'
  }

  def checkInBoundsAndNotMine(x: Int, y: Int): Boolean =
    x >= 0 && x < xSize && y >= 0 && y < ySize && !tiles(y)(x)


  def display(): Unit = {
    println("+ - " * xSize + "+")
    displayTiles foreach {
      ln =>
        println(ln mkString("| ", " | ", " |"))
        println("+ - " * xSize + "+")
    }
  }

}

object Board {

  val neighbours = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

  def apply(x: Int, y: Int, mines: Int): Board = {
    val mineIndices = randomInts(x * y, mines, List.empty)
    val flatList = List.tabulate(x * y)(a => mineIndices.contains(a))
    val tiles = flatList.grouped(x).toList
    Board(tiles)
  }

  def apply(tiles: List[List[Boolean]], displayTiles: List[List[Char]]): Board = new Board(tiles, displayTiles)

  def apply(tiles: List[List[Boolean]]): Board = {
    val displayTiles: List[List[Char]] = List.tabulate(tiles.size, tiles.headOption.getOrElse(List.empty).size)((_, _) => '#')
    new Board(tiles, displayTiles)
  }

  private def randomInts(max: Int, n: Int, list: List[Int]): List[Int] = n match {
    case 0 => list
    case _ =>
      val r = Random.nextInt(max)
      if (!list.contains(r)) randomInts(max, n - 1, list :+ r)
      else randomInts(max, n, list)
  }
}
