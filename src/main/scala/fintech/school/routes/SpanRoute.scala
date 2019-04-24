package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.models.Span
import fintech.school.repositories.SpanRepository
import fintech.school.tools.Json4sSupport._

import scala.concurrent.ExecutionContext

class SpanRoute(city: String)(implicit executionContext: ExecutionContext) {
  val spanRepository = new SpanRepository(city)
  def routes = pathPrefix("spans") {
    path(IntNumber) { id =>
      get {
        complete(spanRepository.getById(id))
      }
    } ~
      patch {
        entity(as[Span]) { params =>
          complete(spanRepository.update(params))
        }
      } ~
      get {
        complete(spanRepository.getAll)
      } ~
      post {
        entity(as[Span]) { elem =>
          complete(spanRepository.create(elem))
        }
      } ~
      delete {
        entity(as[Span]) { elem =>
          complete(spanRepository.delete(elem))
        }
      }
  }
}
