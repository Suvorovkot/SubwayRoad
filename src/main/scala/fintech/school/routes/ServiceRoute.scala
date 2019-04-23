package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.routefinder.Service
import fintech.school.tools.Json4sSupport._

import scala.concurrent.ExecutionContext

class ServiceRoute(city: String)(implicit executionContext: ExecutionContext) {
  val service = new Service(city)
  def routes = {
    path("route") {
      parameters('from.as[String], 'to.as[String]) { (from, to) =>
        complete(service.getRoute(from, to))
      }
    }
  }
}
