package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import fintech.school.routefinder._

import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class Transition (implicit executionContext: ExecutionContext) extends Repository[Transition, String] {
  val dataBase = new DatabaseConnection

//  def getAll: Future[List[Transition]] = Future {
//    dataBase.db withSession { implicit session =>
//      val transitions = TableQuery[TransitionsTable]
//      val transList = ListBuffer[Transitions]
//      transitions.list.foldLeft(transList) {
//        case (list, element) => list += (element.fromStationId, element.toStationId, element.value)
//      }
//
//    }
//  }
  override def getAll(): Future[List[Transition]] = ???

  override def getById(id: Int): Future[Option[Transition]] = ???

  override def getByName(name: String): Future[Option[Transition]] = ???

  override def create(params: String): Future[Transition] = ???

  override def update(id: Int, params: String): Future[Option[Transition]] = ???

  override def delete(id: Int): Future[Boolean] = ???
}
