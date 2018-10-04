package minesweeper

import scala.util.Random

class Board(val tiles: List[List[Boolean]]) {

  private val displayTiles: List[List[Char]] = List.tabulate(tiles.size, tiles.headOption.getOrElse(List.empty).size)((_, _) => '#')

}

object Board {

  def apply(x: Int, y: Int, mines: Int): Board = {
    val mineIndices = randomInts(x * y, mines, List.empty)
    val flatList = List.tabulate(x * y)(a => mineIndices.contains(a))
    val tiles = flatList.grouped(x).toList
    new Board(tiles)
  }

  private def randomInts(max: Int, n: Int, list: List[Int]): List[Int] = n match {
    case 0 => list
    case _ =>
      val r = Random.nextInt(max)
      if (!list.contains(r)) randomInts(max, n - 1, list :+ r)
      else randomInts(max, n, list)
  }
}
