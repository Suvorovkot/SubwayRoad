package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class StationRepository(implicit executionContext: ExecutionContext) {
  val dataBase = new DatabaseConnection

  def getAll(): Future[List[Station]] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.list
    }
  }

  def getById(stId: Int): Future[Station] = Future {
    dataBase.db withSession { implicit session ⇒
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

  def create(params: Station): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.insert(params) == 1
    }
  }

  def update(stId: Int, params: Station): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.filter(_.id === stId).update(params) == 1
    }
  }

  def delete(stId: Int): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      stations.filter(_.id === stId).delete == 1
    }
  }
}
