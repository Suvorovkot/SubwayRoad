package fintech.school.models


import scala.slick.driver.PostgresDriver.simple._

import java.sql.Time

class StationsTable(tag: Tag) extends Table[Station](tag, "Station") {
  def lineId = column[Int]("Line_id")

  def name = column[String]("name")

  def status = column[Int]("status")

  def startWork = column[Time]("startWork")

  def endWork = column[Time]("endWork")

  def id = column[Int]("id")

  def * = (lineId, name, status, startWork, endWork, id) <> (Station.tupled, Station.unapply)
}
