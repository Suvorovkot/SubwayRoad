package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class StationRepository(implicit executionContext: ExecutionContext){
  val dataBase = new DatabaseConnection

  def getAll(): Future[List[Station]] = ???

  def getById(stId: Int): Future[Option[Station]] = Future { // убери Option
    dataBase.db withSession { implicit session ⇒
      val stations = TableQuery[StationsTable]
      val st       = stations.filter(_.id === stId).list.head
      st match {
        case null => None
        case _    => Option(st)
      }
    }
  }

  def getByName(stName: String): Future[Option[Station]] = Future { // убери Option
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val st       = stations.filter(_.name === stName).list.head
      st match {
        case null => None
        case _    => Option(st)
      }
    }
  }

  def create(params: Station): Future[Station] = ???

  def update(id: Int, params: Station): Future[Station] = ???

  def delete(id: Int): Future[Boolean] = ???
}
