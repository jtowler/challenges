package _2048

sealed trait Direction

object Up extends Direction

object Left extends Direction

object Down extends Direction

object Right extends Direction


class Board(val tiles: List[List[Option[Int]]], val score: Int) {

  private def getRandomSpace: (Int, Int) = {
    val r = scala.util.Random

    val freeSpaces = for {
      x <- 0 until 4
      y <- 0 until 4
      if tiles(y)(x).isEmpty
    } yield (y, x)

    r.shuffle(freeSpaces).headOption.getOrElse((-1, -1))
  }

  private def addRandomTile: Board = {
    val v = if (scala.util.Random.nextDouble > 0.9) 4 else 2
    val (y, x) = getRandomSpace
    new Board(tiles.updated(y, tiles(y).updated(x, Option(v))), score)
  }

  private def reduceLeft(list: List[Option[Int]]): (List[Option[Int]], Int) = {
    def go(in: List[Int], acc: List[Int], s: Int): (List[Int], Int) = in match {
      case h1 :: h2 :: t if h1 == h2 => go(t, acc :+ (h1 + h2), h1 + h2 + s)
      case h :: t => go(t, acc :+ h, s)
      case _ => (acc, s)
    }

    val (l, s) = go(list.flatten, List.empty, 0)
    (l.map(Option(_)).padTo(4, None), s)
  }

  private def reduceRight(list: List[Option[Int]]): (List[Option[Int]], Int)  = {
    val (l, s) = reduceLeft(list.reverse)
    (l.reverse, s)
  }

  def move(dir: Direction): Board = dir match {
    case Left =>
      val (l, s) = tiles.map(l => reduceLeft(l)).unzip
      new Board(l, s.sum)
    case Right =>
      val (l, s) = tiles.map(l => reduceRight(l)).unzip
      new Board(l, s.sum)
    case Up =>
      val (l, s) = tiles.transpose.map(l => reduceLeft(l)).unzip
      new Board(l.transpose, s.sum)
    case Down =>
      val (l, s) = tiles.transpose.map(l => reduceRight(l)).unzip
      new Board(l.transpose, s.sum)
  }

  def moveAndAdd(dir: Direction): Board = move(dir).addRandomTile

  def display(): Unit = {
    tiles.foreach(x => println(x.map(y => if (y.isDefined) y.getOrElse(0).toString else " ").mkString(" ")))
  }

}

object Board {

  def init: Board = {
    new Board(List.tabulate(4, 4)((_, _) => None), 0).addRandomTile.addRandomTile
  }

}
