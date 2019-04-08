package fintech.school.models


//case class Metro(id: Long, city: String, country: String)

case class Spans(fromStationId: Int, toStationId: Int, value: Int)

case class Transitions(fromStationId: Int, toStationId: Int, value: Int)

case class Stations(lineId: Int, name: String, status: Int, startWork: Int, endWork: Int, id: Int)

case class Lines(metroId: Int, name: String, number: Int, colour: String, id: Int)

case class Workloads(lineId: Int, time: Int, value: Int)




