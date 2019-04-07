package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.slick.driver.PostgresDriver.simple._

trait StationRepository {
  val dataBase = new DatabaseConnection

  def getById(stId: Int): Station = {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[Stations]
      stations.filter(_.id === stId).list.head
    }
  }

  def getByName(stName: String): Station = {
    dataBase.db withSession { implicit session =>
      val stations = TableQuery[Stations]
      stations.filter(_.name === stName).list.head
    }
  }


}
