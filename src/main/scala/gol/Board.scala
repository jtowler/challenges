package gol

class Board(val tiles: List[List[Int]]) {

  def display(): Unit = {
    tiles.foreach{
      ln => println(ln.mkString(""))
    }
  }

}

object Board {
  def apply(xSize: Int, ySize: Int): Board = {
    val tiles: List[List[Int]] = List.tabulate(ySize, xSize)((_, _) => 0)
    new Board(tiles)
  }

  def apply(xSize: Int, ySize: Int, onTiles: List[(Int, Int)]): Board = {
    val tiles: List[List[Int]] = List.tabulate(ySize, xSize)((y, x) => if (onTiles.contains((y, x))) 1 else 0)
    new Board(tiles)
  }
}