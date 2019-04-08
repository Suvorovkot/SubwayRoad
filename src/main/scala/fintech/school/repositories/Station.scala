package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class Station(implicit executionContext: ExecutionContext) extends Repository[Stations, String] {
  val dataBase = new DatabaseConnection

  override def getAll(): Future[List[Stations]] = ???

  override def getById(stId: Int): Future[Option[Stations]] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val st = stations.filter(_.id === stId).list.head
      st match {
        case null => None
        case _ => Option(st)
      }
    }
  }

  override def getByName(stName: String): Future[Option[Stations]] = Future {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[StationsTable]
      val st = stations.filter(_.name === stName).list.head
      st match {
        case null => None
        case _ => Option(st)
      }
    }
  }

  override def create(params: String): Future[Stations] = ???

  override def update(id: Int, params: String): Future[Option[Stations]] = ???

  override def delete(id: Int): Future[Boolean] = ???
}
