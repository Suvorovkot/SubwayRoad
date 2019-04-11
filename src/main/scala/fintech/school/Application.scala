package fintech.school

import fintech.school.repositories._
import org.json4s.jackson.Serialization
import org.json4s.DefaultFormats
import akka.actor.ActorSystem

import scala.concurrent.Await
import scala.concurrent.duration._

object Application {
  implicit val serialization = Serialization
  implicit val format = DefaultFormats

  def main(args: Array[String]): Unit = {
    implicit val actor = ActorSystem()
    implicit val executionContext = actor.dispatcher

//    val span = new Span()
//    println(Await.ready(span.getAll, 5.seconds))
    println("Success!")
  }
}
