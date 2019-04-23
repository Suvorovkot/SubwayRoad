package fintech.school.controllers

import scala.slick.driver.PostgresDriver.simple._

class DatabaseConnection(dbName: String){
  val dbConnectionUrl = s"jdbc:postgresql://localhost:5432/$dbName?user=postgres&password=postgres"
  val db = Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver")
}