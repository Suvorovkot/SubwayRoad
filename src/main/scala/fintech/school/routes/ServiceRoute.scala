package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.routefinder.Service
import fintech.school.tools.Json4sSupport._

class ServiceRoute(service: Service) {
  def routes = {
    path("route") {
      parameters('from.as[String], 'to.as[String]) { (from, to) =>
        complete(service.getRoute(from, to))
      }
    }
  }
}
