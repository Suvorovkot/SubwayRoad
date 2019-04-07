package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._

class Lines(tag: Tag) extends Table[Line](tag, "Line") {
  def metroId = column[Int]("Metro_id")

  def name = column[String]("name")

  def number = column[Int]("number")

  def colour = column[String]("colour")

  def id = column[Int]("id")

  def * = (metroId, name, number, colour, id) <> (Line.tupled, Line.unapply)
}
