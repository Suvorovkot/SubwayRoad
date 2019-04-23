package fintech.school.models

import java.sql.Timestamp


case class Span(fromStationId: Int, toStationId: Int, value: Int)

case class Transition(fromStationId: Int, toStationId: Int, value: Int)

case class Station(lineId: Int, name: String, status: Int, startWork: Timestamp, endWork: Timestamp, id: Int)

case class Line(name: String, number: Int, colour: String, id: Int)

case class Workload(lineId: Int, time: Timestamp, value: Int)

case class StationParams(lineId: Int, name: String, status: Int, startWork: Timestamp, endWork: Timestamp)

case class LineParams(name: String, number: Int, colour: String)



