package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class SpanRepository(implicit executionContext: ExecutionContext) extends Repository[Span] {
  val dataBase = new DatabaseConnection

  override def getAll: Future[List[Span]] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.list
    }
  }

  override def getById(id: Int): Future[Option[Span]] = ???

  override def getByName(name: String): Future[Option[Span]] = ???

  override def create(params: Span): Future[Span] = ???

  override def update(id: Int, params: Span): Future[Option[Span]] = ???

  override def delete(id: Int): Future[Boolean] = ???

}