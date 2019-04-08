package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class Span(implicit executionContext: ExecutionContext) extends Repository[Spans, String] {
  val dataBase = new DatabaseConnection

  override def getAll: Future[List[Spans]] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.list
    }
  }

  override def getById(id: Int): Future[Option[Spans]] = ???

  override def getByName(name: String): Future[Option[Spans]] = ???

  override def create(params: String): Future[Spans] = ???

  override def update(id: Int, params: String): Future[Option[Spans]] = ???

  override def delete(id: Int): Future[Boolean] = ???

}
