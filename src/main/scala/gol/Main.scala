package gol

object Main extends App{

  val board = Board(10, 10, List((1,1), (2,1), (3,1)))
  board.display()
  val board2 = board.getNextIteration
  board2.display()
}
