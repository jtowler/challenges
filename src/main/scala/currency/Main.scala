package currency

import scala.io.BufferedSource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object Main extends App {

  def convert(from: String, to: String): Unit = {

    val url = f"http://free.currencyconverterapi.com/api/v5/convert?q=${from}_$to&compact=y"
    val result: BufferedSource = scala.io.Source.fromURL(url)
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val parsedJson = mapper.readValue[Map[String, Object]](result.reader())
    println(parsedJson)
  }

  val from = "USD"
  val to = "GBP"
  val amount = 100

//  val rate = convert(from, to)
//
//  print(amount * rate)

}
