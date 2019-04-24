package fintech.school.models



case class Span(fromStationId: Int, toStationId: Int, value: Int)

case class Transition(fromStationId: Int, toStationId: Int, value: Int)

case class Station(lineId: Int, name: String, status: Int, startWork: String, endWork: String, id: Int)

case class Line(name: String, number: Int, colour: String, id: Int)

case class Workload(lineId: Int, time: String, value: Int)

case class StationParams(lineId: Int, name: String, status: Int, startWork: String, endWork: String)

case class LineParams(name: String, number: Int, colour: String)



