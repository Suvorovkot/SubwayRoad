package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import fintech.school.routefinder._

import scala.slick.driver.PostgresDriver.simple._


trait TransitionRepository {
  val dataBase = new DatabaseConnection

  def getAll: EdgeList = {
    dataBase.db withSession { implicit session =>
      val transitions = TableQuery[Transitions]
      val edgeList = new EdgeList()
      transitions.list.foldLeft(edgeList) {
        case (list, element) => list.addEdge(Edge(element.fromStationId, element.toStationId, element.value))
      }
    }
  }
}
