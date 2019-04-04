package fintech.school.models


import scala.slick.driver.PostgresDriver.simple._

//case class Metro(id: Long, city: String, country: String)

case class Span(fromStationId: Int, toStationId: Int, value: Int)

case class Transition(fromStationId: Int, toStationId: Int, value: Int)

case class Station(lineId: Int, name: String, status: Int, startWork: Int, endWork: Int, id: Int)

case class Line(metroId: Int, name: String, number: Int, colour: String, id: Int)

case class Workload(lineId: Int, time: Int, value: Int)


class Spans(tag: Tag) extends Table[Span](tag, "Span") {
  def fromStationId = column[Int]("From_id")

  def toStationId = column[Int]("To_id")

  def time = column[Int]("value")

  def * = (fromStationId, toStationId, time) <> (Span.tupled, Span.unapply)
}

class Transitions(tag: Tag) extends Table[Transition](tag, "Transition") {
  def fromStationId = column[Int]("From_id")

  def toStationId = column[Int]("To_id")

  def time = column[Int]("value")

  def * = (fromStationId, toStationId, time) <> (Transition.tupled, Transition.unapply)
}

class Stations(tag: Tag) extends Table[Station](tag, "Station") {
  def lineId = column[Int]("Line_id")

  def name = column[String]("name")

  def status = column[Int]("status")

  def startWork = column[Int]("startWork")

  def endWork = column[Int]("endWork")

  def id = column[Int]("id")

  def * = (lineId, name, status, startWork, endWork, id) <> (Station.tupled, Station.unapply)
}

class Lines(tag: Tag) extends Table[Line](tag, "Line") {
  def metroId = column[Int]("Metro_id")

  def name = column[String]("name")

  def number = column[Int]("number")

  def colour = column[String]("colour")

  def id = column[Int]("id")

  def * = (metroId, name, number, colour, id) <> (Line.tupled, Line.unapply)
}

class Workloads(tag: Tag) extends Table[Workload](tag, "Workload") {
  def lineId = column[Int]("Line_id")

  def time = column[Int]("time")

  def value = column[Int]("value")

  def * = (lineId, time, value) <> (Workload.tupled, Workload.unapply)
}



