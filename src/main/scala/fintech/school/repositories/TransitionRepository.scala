package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class TransitionRepository(city: String)(implicit executionContext: ExecutionContext) {
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
      val from        = transitions.filter(_.fromStationId === spId).list
      val to          = transitions.filter(_.toStationId === spId).list
      to ++ from
    }
  }

  def create(params: Transition): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      val n           = transitions.insert(params)
      s"$n row inserted"
    }
  }

  def update(params: Transition): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      val n = transitions
        .filter(_.fromStationId === params.fromStationId)
        .filter(_.toStationId === params.toStationId)
        .update(params) == 1
      s"$n row updated"
    }
  }

  def delete(params: Transition): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      val n = transitions
        .filter(_.fromStationId === params.fromStationId)
        .filter(_.toStationId === params.toStationId)
        .delete
      s"$n row deleted"
    }
  }
}
