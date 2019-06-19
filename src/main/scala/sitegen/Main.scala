package sitegen

object Main extends App {

  val head = Head("The simplest HTML example")
  val heading = Heading("This is an HTML Page", 1)
  val body = Body(heading)
  val page = new Page(head, body)
  println(page.getContents)
}
