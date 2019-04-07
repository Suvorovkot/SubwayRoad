package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import fintech.school.routefinder._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class Span (implicit executionContext: ExecutionContext) extends Repository[Edge, String] {
  val dataBase = new DatabaseConnection

//  override def getAll: Future[List[Edge]] = Future {
//    dataBase.db withSession { implicit session =>
//      val spans = TableQuery[SpansTable]
//      val edgeList = new EdgeList()
//      spans.list.foldLeft(edgeList) {
//        case (list, element) => list.addEdge(Edge(element.fromStationId, element.toStationId, element.value))
//      }
//    }
//  }
  override def getAll(): Future[List[Edge]] = ???

  override def getById(id: Int): Future[Option[Edge]] = ???

  override def getByName(name: String): Future[Option[Edge]] = ???

  override def create(params: String): Future[Edge] = ???

  override def update(id: Int, params: String): Future[Option[Edge]] = ???

  override def delete(id: Int): Future[Boolean] = ???
}
