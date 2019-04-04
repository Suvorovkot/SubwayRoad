package fintech.school.models


import scala.slick.driver.PostgresDriver.simple._



case class Station(id: Long, name: String, status: Status.Value, startWork: Integer, endWork: Integer, workloadId: Long, isTransition: Boolean)
case class Workload(levelPerHour: List[Integer])
case class Line(id: Long, metroId: Long, name: String, number: Integer, colour: String, amountOfStation: Integer)
case class Metro(id: Long, city: String, country: String)

class Transition(tag: Tag) extends Table[(Int, Int, Int, Int, Int)](tag, "Transition"){
  //(fromStationId: Long, fromLineId: Long, toStationId: Long, toLineId: Long, time: Integer)
  def fromStationId = column[Int]("From_id")
  def fromLineId = column[Int]("From_id")
  def toStationId = column[Int]("To_id")
  def toLineId = column[Int]("From_id")
  def time = column[Int]("value")
  def * = (fromStationId, fromLineId, toStationId, toLineId, time)
}

class Span(tag: Tag) extends Table[(Int, Int, Int)](tag, "Span"){
  def fromStationId = column[Int]("From_id")
  def toStationId = column[Int]("To_id")
  def time = column[Int]("value")
  def * = (fromStationId, toStationId, time)
}


object Status extends Enumeration{
  val Opened, Closed, Process = Value
}
