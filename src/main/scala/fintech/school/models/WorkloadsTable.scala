package fintech.school.models

import scala.slick.driver.PostgresDriver.simple._


class WorkloadsTable(tag: Tag) extends Table[Workloads](tag, "Workload") {
  def lineId = column[Int]("Line_id")

  def time = column[Int]("time")

  def value = column[Int]("value")

  def * = (lineId, time, value) <> (Workloads.tupled, Workloads.unapply)
}
