package _2048

import scala.io.StdIn

object Main extends App {

  def getInput: Direction = StdIn.readChar() match {
    case 'u' => Up
    case 'l' => Left
    case 'r' => Right
    case 'd' => Down
    case _ => getInput
  }

  def go(board: Board): Unit = {
    board.display()
    val dir = getInput
    go(board.moveAndAdd(dir))
  }

  go(Board.init)

}
