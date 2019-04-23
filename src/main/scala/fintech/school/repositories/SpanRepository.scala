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

  def getById(spId: Int): Future[List[Span]] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      val from = spans.filter(_.fromStationId === spId).list
      val to = spans.filter(_.toStationId === spId).list
      to ++ from
    }
  }

  def create(params: Span): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.insert(params) == 1
    }
  }

  def update(params: Span): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.filter(_.fromStationId === params.fromStationId).filter(_.toStationId === params.toStationId).update(params) == 1
    }
  }

  def delete(params: Span): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.filter(_.fromStationId === params.fromStationId).filter(_.toStationId === params.toStationId).delete == 1
    }
  }
}