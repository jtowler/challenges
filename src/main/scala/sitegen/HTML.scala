package sitegen

import HTML.addTag

trait HTML {
  def getContents: String
}
case class Head(title: String) extends HTML {
  def getContents: String = addTag(addTag(title, "title"), "head")
}
case class Body(contents: HTML) extends HTML {
  def getContents: String = addTag(contents.getContents, "body")
}
case class Heading(heading: String, level: Int) extends HTML {
  def getContents: String = addTag(heading, s"h$level")
}

class Page(head: Head, body: Body) extends HTML {
  override def getContents: String =
    addTag(s"${head.getContents}${body.getContents}", "html")
}

object HTML {
  def addTag(html: String, tag: String): String = s"<$tag>$html</$tag>"
}
