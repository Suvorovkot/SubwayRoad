package fintech.school.controllers

import scala.slick.driver.PostgresDriver.simple._

class DatabaseConnection {
  val dbConnectionUrl = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val db = Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver")


  //      // SELECT * FROM spans WHERE fromStationId = '1'
  //      spans.filter(_.fromStationId === "1").list foreach { row =>
  //        println("Span which fromStationId is '1' has id "+row._1 )
  //      }

}