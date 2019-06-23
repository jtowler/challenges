package sitegen

object Main extends App {


  val page = Page(
      Head("Your Title Here"),
      Body(
          List[HTML](
              Heading("This is a heading", 1),
              HR(),
              Heading("This is another heading", 2),
              Paragraph(
                "An image mixed in with text",
                List(Image("/Users/jack/Documents/1473136640578.png"))
              )
          )
      )
  )

  println(page.getContents)
}
