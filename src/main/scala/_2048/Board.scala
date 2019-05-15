package _2048

sealed trait Direction
object Up extends Direction
object Left extends Direction
object Down extends Direction
object Right extends Direction


class Board(tiles: List[List[Option[Int]]]) {

  private def hasFreeSpace = tiles.flatten.count(_.isDefined) < 16

  private def getRandomSpace: (Int, Int) = {
    val r = scala.util.Random
    def go(): (Int, Int) = (r.nextInt(4), r.nextInt(4)) match {
      case (a, b) if tiles(a)(b).isEmpty => (a, b)
      case _ => go()
    }
    go()
  }

  private def reduceLeft(list: List[Option[Int]]): List[Option[Int]] = {
    def go(in: List[Int], acc: List[Int]): List[Int] = in match {
      case h1 :: h2 :: t if h1 == h2 => go(t, acc :+ (h1 + h2))
      case h1 :: h2 :: t => go(t, acc ++ List(h1, h2))
      case _ => acc
    }
    go(list.flatten, List.empty)
      .map(Option(_))
      .padTo(4, None)
  }

  private def reduceRight(list: List[Option[Int]]): List[Option[Int]] = {
    reduceLeft(list.reverse).reverse
  }

  def move(dir: Direction): Board = dir match {
    case Left => new Board(tiles.map(l => reduceLeft(l)))
    case Right => new Board(tiles.map(l => reduceRight(l)))
    case Up => new Board(tiles.transpose.map(l => reduceLeft(l)).transpose)
    case Up => new Board(tiles.transpose.map(l => reduceRight(l)).transpose)
  }


}