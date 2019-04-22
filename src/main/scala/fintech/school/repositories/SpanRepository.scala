package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class SpanRepository(implicit executionContext: ExecutionContext){
  val dataBase = new DatabaseConnection

  def getAll: Future[List[Span]] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.list
    }
  }

  def getById(id: Int): Future[List[Span]] = ??? // выдавать как совпадения по fromId, так и по byId

  def create(params: Span): Future[Span] = ???

  def update(params: Span): Future[Span] = ??? // найти по двум id и обновить значение

  def delete(params: Span): Future[Span] = ???
}