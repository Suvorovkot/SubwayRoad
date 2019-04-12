package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.repositories.SpanRepository
import fintech.school.routefinder.Service

import scala.concurrent.ExecutionContext

class Router(implicit executionContext: ExecutionContext) {
  val spanRoutes   = new SpanRoutes(new SpanRepository)
  val serviceRoute = new ServiceRoute(new Service)

  val routes = {
    pathPrefix("metro") {
      spanRoutes.routes ~ serviceRoute.routes
    }
  }

}
