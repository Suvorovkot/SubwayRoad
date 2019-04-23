package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class SpanRepository(city: String)(implicit executionContext: ExecutionContext) {
  val dataBase = new DatabaseConnection(city)

  def getAll: Future[List[Span]] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      spans.list
    }
  }

  def getById(spId: Int): Future[List[Span]] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      val from  = spans.filter(_.fromStationId === spId).list
      val to    = spans.filter(_.toStationId === spId).list
      to ++ from
    }
  }

  def create(params: Span): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      val n     = spans.insert(params)
      s"$n row inserted"
    }
  }

  def update(params: Span): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      val n = spans
        .filter(_.fromStationId === params.fromStationId)
        .filter(_.toStationId === params.toStationId)
        .update(params)
      s"$n row updated"
    }
  }

  def delete(params: Span): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val spans = TableQuery[SpansTable]
      val n = spans
        .filter(_.fromStationId === params.fromStationId)
        .filter(_.toStationId === params.toStationId)
        .delete
      s"$n row deleted"
    }
  }
}
