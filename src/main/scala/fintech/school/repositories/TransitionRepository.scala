package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class TransitionRepository(city: String)(implicit executionContext: ExecutionContext){
  val dataBase = new DatabaseConnection(city)

  def getAll: Future[List[Transition]] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.list
    }
  }

  def getById(spId: Int): Future[List[Transition]] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      val from = transitions.filter(_.fromStationId === spId).list
      val to = transitions.filter(_.toStationId === spId).list
      to ++ from
    }
  }

  def create(params: Transition): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.insert(params) == 1
    }
  }

  def update(params: Transition): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.filter(_.fromStationId === params.fromStationId).filter(_.toStationId === params.toStationId).update(params) == 1
    }
  }

  def delete(params: Transition): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.filter(_.fromStationId === params.fromStationId).filter(_.toStationId === params.toStationId).delete == 1
    }
  }
}
