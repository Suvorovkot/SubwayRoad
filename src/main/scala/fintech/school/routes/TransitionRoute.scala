package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.models.Transition
import fintech.school.repositories.TransitionRepository
import fintech.school.tools.Json4sSupport._

import scala.concurrent.ExecutionContext

class TransitionRoute(city: String)(implicit executionContext: ExecutionContext) {
  val transitionRepository = new TransitionRepository(city)
  def routes = pathPrefix("transition") {
    path(IntNumber) { id =>
      get {
        complete(transitionRepository.getById(id))
      } ~
        patch {
          entity(as[Transition]) { params =>
            complete(transitionRepository.update(params))
          }
        }
    } ~
      get {
        complete(transitionRepository.getAll)
      } ~
      post {
        entity(as[Transition]) { elem =>
          complete(transitionRepository.create(elem))
        }
      } ~
      delete {
        entity(as[Transition]) { elem =>
          complete(transitionRepository.delete(elem))
        }
      }
  }
}
