package currency

import scala.io.BufferedSource

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object Main extends App {

  @specialized def registerModule[A](evaluateForSideEffectOnly: A): Unit = {
    val _: A = evaluateForSideEffectOnly
    () //Return unit to prevent warning due to discarding value
  }

  def convert(currStr: String): Double = {

    val url: String = f"http://free.currencyconverterapi.com/api/v5/convert?q=" + currStr + "&compact=y"
    val result: BufferedSource = scala.io.Source.fromURL(url)
    val mapper = new ObjectMapper() with ScalaObjectMapper
    registerModule(mapper.registerModule(DefaultScalaModule))
    val parsedJson = mapper.readValue[Map[String, Map[String, Double]]](result.reader())
    val level1: Map[String, Double] = parsedJson.getOrElse(currStr, Map("val" -> -1.0))
    level1.getOrElse("val", -1.0)
  }

  def displayConversion(from: String, to: String, amount: Double): Unit = {
    val currStr = from + "_" + to
    val rate = convert(currStr)
    val convertedAmount = amount * rate
    val convertedString = "%.2f".format(convertedAmount).toDouble
    val printString: String = amount.toString + " " + from + " is equivalent to " + convertedString.toString + " " + to
   registerModule( println(printString))
  }

  val from = "USD"
  val to = "GBP"
  val amount = 100

  displayConversion(from, to, amount)

}
