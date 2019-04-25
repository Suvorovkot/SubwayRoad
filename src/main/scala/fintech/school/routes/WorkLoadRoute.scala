package fintech.school.routes

import akka.http.scaladsl.server.Directives._
import fintech.school.models.Workload
import fintech.school.repositories.WorkloadRepository
import fintech.school.tools.Json4sSupport._
import java.sql.Timestamp

import scala.concurrent.ExecutionContext

class WorkLoadRoute(city: String)(implicit executionContext: ExecutionContext) {
  val workloadRepository = new WorkloadRepository(city)
  def routes = pathPrefix("workloads") {
    path(IntNumber) { id =>
      get {
        complete(workloadRepository.getById(id))
      } ~
        delete {
          entity(as[Timestamp]) { params =>
            complete(workloadRepository.delete(id, params))
          }
        }
    }~
      post {
        entity(as[Workload]) { elem =>
          complete(workloadRepository.create(elem))
        }
      } ~
      patch {
        entity(as[Workload]) { params =>
          complete(workloadRepository.update(params))
        }
      }
  }
}
