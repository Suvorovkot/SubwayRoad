package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._

class Spans(tag: Tag) extends Table[Span](tag, "Span") {
  def fromStationId = column[Int]("From_id")

  def toStationId = column[Int]("To_id")

  def time = column[Int]("value")

  def * = (fromStationId, toStationId, time) <> (Span.tupled, Span.unapply)
}
