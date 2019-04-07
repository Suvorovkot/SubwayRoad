package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._

class LinesTable(tag: Tag) extends Table[Lines](tag, "Line") {
  def metroId = column[Int]("Metro_id")

  def name = column[String]("name")

  def number = column[Int]("number")

  def colour = column[String]("colour")

  def id = column[Int]("id")

  def * = (metroId, name, number, colour, id) <> (Lines.tupled, Lines.unapply)
}
