package morse

object Alphabet {

  private val alphabet = Map(
    'A' -> ".-",
    'B' -> "-...",
    'C' -> "-.-.",
    'D' -> "-..",
    'E' -> ".",
    'F' -> "..-.",
    'G' -> "--.",
    'H' -> "....",
    'I' -> "..",
    'J' -> ".---",
    'K' -> "-.-",
    'L' -> ".-..",
    'M' -> "--",
    'N' -> "-.",
    'O' -> "---",
    'P' -> ".--.",
    'Q' -> "--.-",
    'R' -> ".-.",
    'S' -> "...",
    'T' -> "-",
    'U' -> "..-",
    'V' -> "...-",
    'W' -> ".--",
    'X' -> "-..-",
    'Y' -> "-.--",
    'Z' -> "--..",
    '1' -> ".----",
    '2' -> "..---",
    '3' -> "...--",
    '4' -> "....-",
    '5' -> ".....",
    '6' -> "-....",
    '7' -> "--...",
    '8' -> "---..",
    '9' -> "----.",
    '0' -> "-----"
  )

  def toMorse(msg: String): String = {
    val sentences: Array[String] = msg.split("\\.")
    sentences.map {
      sentence =>
        sentence.split("\\W+").map { word =>
          word.map { c =>
            alphabet.getOrElse(c, " ")
          }.mkString("   ")
        }.mkString("       ")
    }.mkString("")
  }


}
