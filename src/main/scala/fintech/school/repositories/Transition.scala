package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._


class Transition(implicit executionContext: ExecutionContext) extends Repository[Transitions, String] {
  val dataBase = new DatabaseConnection

  def getAll: Future[List[Transitions]] = Future {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[TransitionsTable]
      transitions.list
    }
  }

  override def getById(id: Int): Future[Option[Transitions]] = ???

  override def getByName(name: String): Future[Option[Transitions]] = ???

  override def create(params: String): Future[Transitions] = ???

  override def update(id: Int, params: String): Future[Option[Transitions]] = ???

  override def delete(id: Int): Future[Boolean] = ???
}
