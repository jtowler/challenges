package minesweeper

object Main extends App {
  val board = Board(List(List(false, false, true, true),
    List(false, false, false, true), List(false, false, false, false), List(false, false, false, false)))
  val board2 = board.clearTile(0, 3)
  board.display()
  board2.display()
}
