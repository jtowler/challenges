package gol

object Main extends App{

  val board = Board(10, 10, List((1,1), (2,1), (3,1)))

  def loop(board: Board): Unit = {
    if (board.deadBoard) ()
    else {
      board.display()
      loop(board.getNextIteration)
    }
  }

  loop(board)

}
