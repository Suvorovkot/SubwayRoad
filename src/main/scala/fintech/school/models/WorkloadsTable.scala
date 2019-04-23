package fintech.school.models

import java.sql.Time

import scala.slick.driver.PostgresDriver.simple._


class WorkloadsTable(tag: Tag) extends Table[Workload](tag, "Workload") {
  def lineId = column[Int]("Line_id")

  def time = column[Time]("time")

  def value = column[Int]("value")

  def * = (lineId, time, value) <> (Workload.tupled, Workload.unapply)
}
