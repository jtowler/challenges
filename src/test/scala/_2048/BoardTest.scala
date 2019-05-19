package _2048

import org.scalatest.{FlatSpec, Matchers}

class BoardTest extends FlatSpec with Matchers {

  behavior of "Board"
  it should "update the board when moving tiles upwards" in {
    val b = new Board(List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, Some(2), None, None),
      List(None, Some(2), None, None)
    ))
    val actual = b.move(Up).tiles
    val expected = List(
      List(None, Some(4), None, None),
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, None, None, None)
    )
    actual shouldBe expected
  }

  it should "update the board when moving tiles downwards" in {
    val b = new Board(List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, Some(2), None, None),
      List(None, Some(2), None, None)
    ))
    val actual = b.move(Down).tiles
    val expected = List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, Some(4), None, None)
    )
    actual shouldBe expected
  }

  it should "update the board when moving tiles left" in {
    val b = new Board(List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(Some(2), Some(2), None, None),
      List(None, None, None, None)
    ))
    val actual = b.move(Left).tiles
    val expected = List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(Some(4), None, None, None),
      List(None, None, None, None)
    )
    actual shouldBe expected
  }

  it should "update the board when moving tiles right" in {
    val b = new Board(List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(Some(2), Some(2), None, None),
      List(None, None, None, None)
    ))
    val actual = b.move(Right).tiles
    val expected = List(
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, None, None, Some(4)),
      List(None, None, None, None)
    )
    actual shouldBe expected
  }

  it should "not lose tiles when not combining" in {
    val b = new Board(List(
      List(None, None, None, None),
      List(None, Some(2), None, None),
      List(Some(4), None, None, None),
      List(None, None, None, None)
    ))
    val actual = b.move(Up).tiles
    val expected = List(
      List(Some(4), Some(2), None, None),
      List(None, None, None, None),
      List(None, None, None, None),
      List(None, None, None, None)
    )
    actual shouldBe expected
  }

}
