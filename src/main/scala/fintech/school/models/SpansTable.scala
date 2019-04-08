package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._

class SpansTable(tag: Tag) extends Table[Spans](tag, "Span") {
  def fromStationId = column[Int]("From_id")

  def toStationId = column[Int]("To_id")

  def time = column[Int]("value")

  def * = (fromStationId, toStationId, time) <> (Spans.tupled, Spans.unapply)
}
