package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._

class LinesTable(tag: Tag) extends Table[Line](tag, "Line") {
  def name = column[String]("name")

  def number = column[Int]("number")

  def colour = column[String]("colour")

  def id = column[Int]("id")

  def * = (name, number, colour, id) <> (Line.tupled, Line.unapply)
}
