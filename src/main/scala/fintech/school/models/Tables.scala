package fintech.school.models


import scala.slick.driver.PostgresDriver.simple._

//case class Station(id: Long, name: String, status: Status.Value, startWork: Integer, endWork: Integer, workloadId: Long, isTransition: Boolean)
//case class Workload(levelPerHour: List[Integer])
//case class Line(id: Long, metroId: Long, name: String, number: Integer, colour: String, amountOfStation: Integer)
//case class Metro(id: Long, city: String, country: String)

case class Span(fromStationId: Int, toStationId: Int, value: Int)
case class Transition(fromStationId: Int, toStationId: Int, value: Int)

class Spans(tag: Tag) extends Table[Span](tag, "Span"){
  def fromStationId = column[Int]("From_id")
  def toStationId = column[Int]("To_id")
  def time = column[Int]("value")
  def * = (fromStationId, toStationId, time) <> (Span.tupled,Span.unapply)
}

class Transitions(tag: Tag) extends Table[Transition](tag, "Transition"){
  def fromStationId = column[Int]("From_id")
  def toStationId = column[Int]("To_id")
  def time = column[Int]("value")
  def * = (fromStationId,toStationId,time) <> (Transition.tupled,Transition.unapply)
}

