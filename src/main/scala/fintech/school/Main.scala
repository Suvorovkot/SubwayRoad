package fintech.school

import akka.actor.ActorSystem
import fintech.school.repositories.Span
import scala.concurrent.duration._

import scala.concurrent.Await

object Main extends App{
  override def main(args: Array[String]): Unit = {
    implicit val actor = ActorSystem()
    implicit val executionContext = actor.dispatcher

    val span = new Span()
    println(Await.ready(span.getAll, 5.seconds))
  }
}
