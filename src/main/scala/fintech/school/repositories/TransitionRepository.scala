package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class TransitionRepository(implicit executionContext: ExecutionContext) extends Repository[Transition] {
  val dataBase = new DatabaseConnection

  def getAll: Future[List[Transition]] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.list
    }
  }

  override def getById(id: Int): Future[Option[Transition]] = ???

  override def getByName(name: String): Future[Option[Transition]] = ???

  override def create(params: Transition): Future[Transition] = ???

  override def update(id: Int, params: Transition): Future[Option[Transition]] = ???

  override def delete(id: Int): Future[Boolean] = ???
}
