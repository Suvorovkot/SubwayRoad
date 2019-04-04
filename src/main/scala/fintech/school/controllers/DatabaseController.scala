package fintech.school.controllers

import fintech.school.models._
import fintech.school.routefinder._
import scala.slick.driver.PostgresDriver.simple._

class DatabaseController {
  val dbConnectionUrl = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver") withSession {
    implicit session =>

//            // SELECT * FROM spans
//            spans.list foreach { row =>
//              println("Span from id " + row._1 + " to " + row._2)
//            }

      //      // SELECT * FROM spans WHERE fromStationId = '1'
      //      spans.filter(_.fromStationId === "1").list foreach { row =>
      //        println("Span which fromStationId is '1' has id "+row._1 )
      //      }
//      val spans = TableQuery[Span]
//      val edgeList = new EdgeList()
//      spans.list foreach { row =>
//        edgeList.addEdge(Edge(row._1, row._2, row._3))
//      }
//      println(edgeList)
      val trans = TableQuery[Transition]
      val transList = new EdgeList()
      trans.list foreach { row =>
        transList.addEdge(Edge(row._1, row._2, row._3))
      }
      println(transList)
  }
}
