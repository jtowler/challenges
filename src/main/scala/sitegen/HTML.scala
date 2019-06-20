package sitegen

import HTML.addTag

trait HTML {
  def getContents: String
}

abstract class BodyHTML(contents: List[HTML]) extends HTML {
  def getContents: String =
    contents.foldLeft(""){case (acc, html) => acc ++ html.getContents}
}

case class Head(title: String) {
  def getContents: String = addTag(addTag(title, "title"), "head")
}
case class Body(contents: List[HTML]) extends BodyHTML(contents: List[HTML]) {
  override def getContents: String =
    addTag(super.getContents, "body")
}

case class Heading(heading: String, level: Int) extends HTML {
  def getContents: String = addTag(heading, s"h$level")
}

case class Paragraph(text: String, contents: List[HTML]) extends BodyHTML(contents) {
  override def getContents: String = text + addTag(super.getContents, "p")
}

case class HR() extends HTML {
  override def getContents: String = "<HR>"
}

case class Image(src: String) extends HTML {
  override def getContents: String = s"<img src = $src>"
}

case class Page(head: Head, body: Body) extends HTML {
  def getContents: String =
    addTag(s"${head.getContents}${body.getContents}", "html")
}

object HTML {
  def addTag(html: String, tag: String): String = s"<$tag>$html</$tag>"
}
