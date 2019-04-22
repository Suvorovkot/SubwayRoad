package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class TransitionRepository(implicit executionContext: ExecutionContext){
  val dataBase = new DatabaseConnection

  def getAll: Future[List[Transition]] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.list
    }
  }

  def getById(id: Int): Future[List[Transition]] = ??? // выдавать как совпадения по fromId, так и по byId

  def create(params: Transition): Future[Transition] = ???

  def update(params: Transition): Future[Transition] = ??? // найти по двум id и обновить значение

  def delete(params: Transition): Future[Boolean] = ???
}
