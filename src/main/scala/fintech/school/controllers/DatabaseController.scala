package fintech.school.controllers

import fintech.school.models._
import fintech.school.routefinder._
import scala.slick.driver.PostgresDriver.simple._

class DatabaseController {
  val dbConnectionUrl = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver") withSession {
    implicit session =>
      val spans = TableQuery[Spans]
      val transitions = TableQuery[Transitions]

      //            // SELECT * FROM spans
      //            spans.list foreach { row =>
      //              println("Span from id " + row._1 + " to " + row._2)
      //            }

      //      // SELECT * FROM spans WHERE fromStationId = '1'
      //      spans.filter(_.fromStationId === "1").list foreach { row =>
      //        println("Span which fromStationId is '1' has id "+row._1 )
      //      }
      def getTransitions: EdgeList ={
        val edgeList = new EdgeList()
        transitions.list.foldLeft(edgeList){case (list, element) => list.addEdge(Edge(element.fromStationId,element.toStationId,element.value))}
      }

      def getSpans: EdgeList ={
        val edgeList = new EdgeList()
        spans.list.foldLeft(edgeList){case (list, element) => list.addEdge(Edge(element.fromStationId,element.toStationId,element.value))}
      }



  }
}