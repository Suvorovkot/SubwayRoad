package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.models.Spans
import fintech.school.repositories.Span
import fintech.school.tools.Json4sSupport._

class SpanRoutes(spanRepository: Span) {
  def routes = {
    path("spans"){
      get {
        complete(spanRepository.getAll)
      } ~
      post {
        entity(as[Spans]) { params =>
          complete(spanRepository.create(params))
        }
      }
    } ~
    path("spans" / IntNumber) { id =>
      get {
        complete(spanRepository.getById(id))
      } ~
      patch {
        entity(as[Spans]) { params =>
          complete(spanRepository.update(id,params))
        }
      }
    }
  }
}
