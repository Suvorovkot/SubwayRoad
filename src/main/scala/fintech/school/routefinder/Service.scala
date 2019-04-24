package fintech.school.routefinder

import scala.concurrent.duration._
import fintech.school.models.{Span, Station, Transition}
import fintech.school.repositories.{SpanRepository, StationRepository, TransitionRepository}
import org.postgresql.util.PSQLException

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class Service(city: String)(implicit executionContext: ExecutionContext) {
  private val stationRepository    = new StationRepository(city)
  private val spanRepository       = new SpanRepository(city)
  private val transitionRepository = new TransitionRepository(city)

  def getRoute(from: String, to: String): Future[RouteResponse] = {
    val result = for (
      spanList <- spanRepository.getAll;
      transitionList <- transitionRepository.getAll;
      fromStation    <- stationRepository.getByName(from);
      toStation      <- stationRepository.getByName(to);
      stationList    <- stationRepository.getAll()
    ) yield {
      if (fromStation.status != 1){
        Future.successful(RouteResponse(List[RouteResponseElement](), 0.0, s"${fromStation.name} is closed"))
      }else if (toStation.status != 1){
        Future.successful(RouteResponse(List[RouteResponseElement](), 0.0, s"${toStation.name} is closed"))
      } else {
        val edges = new EdgeList().prepareSpans(spanList).prepareTransitions(transitionList)
        val result = RouteMaker.calculate(edges, fromStation.id)
        prepareResponse(result, toStation.id, transitionList, stationList)
      }
    }
    result.flatten
  }

  private def prepareResponse(result: RouteResult, toId: Int, transitionList: List[Transition], stationList: List[Station]): Future[RouteResponse] = {
    var list     = List[RouteResponseElement]()
    val edgeList = result.pathTo(toId)
    for (edge <- edgeList) {
      val fromStation    = stationList.filter(_.id == edge.from).head.name
      val toStation      = stationList.filter(_.id == edge.to).head.name
      val action: String = getAction(edge.from, edge.to, transitionList)
      val element        = RouteResponseElement(fromStation, toStation, edge.weight, action)
      list = list :+ element
    }
    val res = RouteResponse(list, result.distToV(toId), "Success")
    Future.successful(res)
  }
  private def getAction(from: Int, to: Int, transitionList: List[Transition]): String =
    if (transitionList.exists(elem => elem.fromStationId == from && elem.toStationId == to || elem.toStationId == from && elem.fromStationId == to)) {
      "Change Line"
    } else {"Ride"}
}
