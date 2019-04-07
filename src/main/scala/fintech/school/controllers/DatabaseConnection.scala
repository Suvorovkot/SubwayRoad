package fintech.school.controllers

import scala.slick.driver.PostgresDriver.simple._

class DatabaseConnection {
  val dbConnectionUrl = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val db = Database.forURL(dbConnectionUrl, driver = "org.postgresql.Driver")
}