package fintech.school.routefinder

case class RouteResponse(route: List[RouteResponseElement], duration: Double, massage: String)
case class RouteResponseElement(from: String, to: String, time: Double, action: String)
