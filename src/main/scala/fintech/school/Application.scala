package fintech.school

import org.json4s.jackson.Serialization
import org.json4s.DefaultFormats

object Application {
  implicit val serialization = Serialization
  implicit val format = DefaultFormats

  def main(args: Array[String]): Unit = {

  }
}
