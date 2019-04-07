package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._

class Transitions(tag: Tag) extends Table[Transition](tag, "Transition") {
  def fromStationId = column[Int]("From_id")

  def toStationId = column[Int]("To_id")

  def time = column[Int]("value")

  def * = (fromStationId, toStationId, time) <> (Transition.tupled, Transition.unapply)
}
