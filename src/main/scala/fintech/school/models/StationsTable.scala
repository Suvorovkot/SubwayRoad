package fintech.school.models

import java.sql.Timestamp

import scala.slick.driver.PostgresDriver.simple._


class StationsTable(tag: Tag) extends Table[Station](tag, "Station") {
  def lineId = column[Int]("Line_id")

  def name = column[String]("name")

  def status = column[Int]("status")

  def startWork = column[Timestamp]("startWork")

  def endWork = column[Timestamp]("endWork")

  def id = column[Int]("id")

  def * = (lineId, name, status, startWork, endWork, id) <> (Station.tupled, Station.unapply)
}
