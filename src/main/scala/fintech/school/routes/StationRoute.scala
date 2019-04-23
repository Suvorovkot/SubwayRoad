package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.models.Station
import fintech.school.repositories.StationRepository
import fintech.school.tools.Json4sSupport._

import scala.concurrent.ExecutionContext

class StationRoute(city: String)(implicit executionContext: ExecutionContext) {
  val stationRepository = new StationRepository(city)
  def routes = pathPrefix("stations") {
    path(IntNumber) { id =>
      get {
        complete(stationRepository.getById(id))
      } ~
        patch {
          entity(as[Station]) { params =>
            complete(stationRepository.update(id, params))
          }
        } ~
        delete {
          complete(stationRepository.delete(id))
        }
    } ~
      get {
        complete(stationRepository.getAll)
      } ~
      post {
        entity(as[Station]) { elem =>
          complete(stationRepository.create(elem))
        }
      }
  }
}
