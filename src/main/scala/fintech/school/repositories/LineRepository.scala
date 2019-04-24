package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class LineRepository(city: String)(implicit executionContext: ExecutionContext) {
  val dataBase = new DatabaseConnection(city)

  def getAll(): Future[List[Line]] = Future {
    dataBase.db withSession { implicit session =>
      val lines = TableQuery[LinesTable]
      lines.list.sortBy(_.name)
    }
  }

  def getById(lnId: Int): Future[Line] = Future {
    dataBase.db withSession { implicit session =>
      val lines = TableQuery[LinesTable]
      lines.filter(_.id === lnId).list.head
    }
  }

  def getByName(lnName: String): Future[Line] = Future {
    dataBase.db withSession { implicit session =>
      val lines = TableQuery[LinesTable]
      lines.filter(_.name === lnName).list.head
    }
  }
}
