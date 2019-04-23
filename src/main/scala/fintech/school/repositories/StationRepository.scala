package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class StationRepository(city: String)(implicit executionContext: ExecutionContext) {
  val dataBase = new DatabaseConnection(city)

  def getAll(): Future[List[Station]] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.list
    }
  }

  def getById(stId: Int): Future[Station] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.filter(_.id === stId).list.head
    }
  }

  def getByName(stName: String): Future[Station] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.filter(_.name === stName).list.head
    }
  }

  def create(params: Station): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val n        = stations.insert(params)
      s"$n row inserted"
    }
  }

  def update(stId: Int, params: Station): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val n        = stations.filter(_.id === stId).update(params)
      s"$n row updated"
    }
  }

  def delete(stId: Int): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val n = stations.filter(_.id === stId).delete
      s"$n row deleted"
    }
  }
}
