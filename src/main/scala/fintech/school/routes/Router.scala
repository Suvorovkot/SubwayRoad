package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.repositories.SpanRepository
import fintech.school.routefinder.Service

import scala.concurrent.ExecutionContext

class Router(implicit executionContext: ExecutionContext) {
  private val piter = "StPetersburg"
  private val volgograd = "Volgograd"

  val routes = {
    pathPrefix(piter) {
      new SpanRoute(piter).routes ~ new StationRoute(piter).routes ~ new TransitionRoute(piter).routes ~ new WorkLoadRoute(piter).routes ~ new ServiceRoute(piter).routes
    } ~
      pathPrefix(volgograd) {
        new SpanRoute(volgograd).routes ~ new StationRoute(volgograd).routes ~ new TransitionRoute(volgograd).routes ~ new WorkLoadRoute(volgograd).routes ~ new ServiceRoute(volgograd).routes
      }
  }
}
