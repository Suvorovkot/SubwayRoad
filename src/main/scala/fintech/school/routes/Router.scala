package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.repositories.Span

import scala.concurrent.ExecutionContext

class Router(implicit executionContext: ExecutionContext) {
  val spanRoutes = new SpanRoutes(new Span)

  val routes = {
    pathPrefix("metro") {
      spanRoutes.routes
    }
  }

}
