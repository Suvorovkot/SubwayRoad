package fintech.school.controllers

import fintech.school.models._
import fintech.school.routefinder._
import scala.slick.driver.PostgresDriver.simple._

class DatabaseController {
  val dbConnectionUrl = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val db = Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver")

  def getTransitions: EdgeList = {
    db withSession { implicit session =>
      val transitions = TableQuery[Transitions]
      val edgeList = new EdgeList()
      transitions.list.foldLeft(edgeList) {
        case (list, element) => list.addEdge(Edge(element.fromStationId, element.toStationId, element.value))
      }
    }
  }

  def getSpans: EdgeList = {
    db withSession { implicit session =>
      val spans = TableQuery[Spans]
      val edgeList = new EdgeList()
      spans.list.foldLeft(edgeList) {
        case (list, element) => list.addEdge(Edge(element.fromStationId, element.toStationId, element.value))
      }
    }
  }
  def getStationById(stId: Int): Station = {
    db withSession { implicit session =>
      val stations = TableQuery[Stations]
      stations.filter(_.id === stId).list.head
    }
  }
  def getStationByName(stName: String): Station = {
    db withSession { implicit session =>
      val stations = TableQuery[Stations]
      stations.filter(_.name === stName).list.head
    }
  }
  //      // SELECT * FROM spans WHERE fromStationId = '1'
  //      spans.filter(_.fromStationId === "1").list foreach { row =>
  //        println("Span which fromStationId is '1' has id "+row._1 )
  //      }

}