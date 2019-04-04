package fintech.school.models

case class Station(id: Long, name: String, status: Status.Value, startWork: Integer, endWork: Integer, workloadId: Long, isTransition: Boolean)
case class Transition(fromStationId: Long, fromLineId: Long, toStationId: Long, toLineId: Long, time: Integer)
case class Span(fromStationId: Long, toStationId: Long, time: Integer)
case class Workload(levelPerHour: List[Integer])
case class Line(id: Long, metroId: Long, name: String, number: Integer, colour: String, amountOfStation: Integer)
case class Metro(id: Long, city: String, country: String)


object Status extends Enumeration{
  val Opened, Closed, Process = Value
}
