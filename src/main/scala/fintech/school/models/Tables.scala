package fintech.school.models


//case class Metro(id: Long, city: String, country: String)

case class Span(fromStationId: Int, toStationId: Int, value: Int)

case class Transition(fromStationId: Int, toStationId: Int, value: Int)

case class Station(lineId: Int, name: String, status: Int, startWork: String, endWork: String, id: Int)

case class Line(metroId: Int, name: String, number: Int, colour: String, id: Int)

case class Workload(lineId: Int, time: Int, value: Int)




