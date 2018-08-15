package gol

class Board(val tiles: List[List[Int]]) {
  val ySize: Int = tiles.size
  val xSize: Int = tiles.headOption.getOrElse(List.empty).size // TODO this can be done way better

  def display(): Unit = {
    tiles.foreach {
      ln => println(ln.mkString("  "))
    }
  }

  def getNeighbours(y: Int, x: Int): Int = {
    val neighbours = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
    neighbours.count{case (yP, xP) =>
      x + xP >= 0 && x + xP < xSize && y + yP >= 0 && y + yP < ySize && tiles(y + yP)(x + xP) == 1
    }
  }

  def getNextIteration: Board = {
    val newTiles = List.tabulate(ySize, xSize)((y, x) => {
      val alive = tiles(y)(x)
      val nn = getNeighbours(y, x)
      if (alive == 1) {
        if (nn > 1 && nn < 4) 1
        else 0
      }
      else if (nn == 3) 1 else 0
    })
    new Board(newTiles)
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