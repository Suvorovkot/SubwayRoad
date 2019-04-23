package fintech.school

import fintech.school.repositories._
import org.json4s.jackson.Serialization
import org.json4s.DefaultFormats
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import fintech.school.routefinder.Service
import routes.Router

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Application {
  implicit val actor                           = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext                = actor.dispatcher

  def main(args: Array[String]): Unit = {
    val router = new Router

    Http().bindAndHandle(router.routes, "0.0.0.0", 8080).onComplete {
      case Success(b) =>
        println(s"Server is running at ${b.localAddress.getHostName}:${b.localAddress.getPort}")
      case Failure(e) => println(s"Could not start application: {}", e.getMessage)
    }
  }
}
