package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.models.Span
import fintech.school.repositories.SpanRepository
import fintech.school.tools.Json4sSupport._

class SpanRoutes(spanRepository: SpanRepository) {
  def routes = {
    path("spans"){
      get {
        complete(spanRepository.getAll)
      } ~
      post {
        entity(as[Span]) { params =>
          complete(spanRepository.create(params))
        }
      }
    } ~
    path("spans" / IntNumber) { id =>
      get {
        complete(spanRepository.getById(id))
      } ~
      patch {
        entity(as[Span]) { params =>
          complete(spanRepository.update(id,params))
        }
      }
    }
  }
}
