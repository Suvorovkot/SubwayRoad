package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class StationRepository(implicit executionContext: ExecutionContext) extends Repository[Station] {
  val dataBase = new DatabaseConnection

  override def getAll(): Future[List[Station]] = ???

  override def getById(stId: Int): Future[Option[Station]] = Future {
    dataBase.db withSession { implicit session ⇒
      val stations = TableQuery[StationsTable]
      val st       = stations.filter(_.id === stId).list.head
      st match {
        case null => None
        case _    => Option(st)
      }
    }
  }

  override def getByName(stName: String): Future[Option[Station]] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val st       = stations.filter(_.name === stName).list.head
      st match {
        case null => None
        case _    => Option(st)
      }
    }
  }

  override def create(params: Station): Future[Station] = ???

  override def update(id: Int, params: Station): Future[Option[Station]] = ???

  override def delete(id: Int): Future[Boolean] = ???
}
