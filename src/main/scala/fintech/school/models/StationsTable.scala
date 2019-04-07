package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._


class StationsTable(tag: Tag) extends Table[Stations](tag, "Station") {
  def lineId = column[Int]("Line_id")

  def name = column[String]("name")

  def status = column[Int]("status")

  def startWork = column[Int]("startWork")

  def endWork = column[Int]("endWork")

  def id = column[Int]("id")

  def * = (lineId, name, status, startWork, endWork, id) <> (Stations.tupled, Stations.unapply)
}
