package fintech.school.routefinder

import scala.concurrent.duration._
import fintech.school.models.{Span, Station, Transition}
import fintech.school.repositories.{SpanRepository, StationRepository, TransitionRepository}
import org.postgresql.util.PSQLException

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class Service(implicit executionContext: ExecutionContext) {
  private val stationRepository    = new StationRepository()
  private val spanRepository       = new SpanRepository()
  private val transitionRepository = new TransitionRepository()

  private val spanList: List[Span] = Await.result(spanRepository.getAll, Duration.Inf)

  private val transitionList = Await.result(transitionRepository.getAll, Duration.Inf)

  def getRoute(from: String, to: String): RouteResponse = {
    val fromStation: Station = Await.result(stationRepository.getByName(from), Duration.Inf).get
    val toStation: Station   = Await.result(stationRepository.getByName(to), Duration.Inf).get

    var edges = new EdgeList().prepareSpans(spanList).prepareTransitions(transitionList)

    val routeMaker = RouteMaker
    val result     = routeMaker.calculate(edges, fromStation.id)

    prepareResponse(result, toStation.id)
  }

  private def prepareResponse(result: RouteResult, toId: Int): RouteResponse = {
    var list     = List[RouteResponseElement]()
    val edgeList = result.pathTo(toId)
    for (edge <- edgeList) {
      val fromStation    = Await.result(stationRepository.getById(edge.from), Duration.Inf).get.name
      val toStation      = Await.result(stationRepository.getById(edge.to), Duration.Inf).get.name
      val action: String = if (isTransition(edge.from, edge.to)) "Change Line" else "Ride"
      val element        = RouteResponseElement(fromStation, toStation, edge.weight, action)
      list = list :+ element
    }
    RouteResponse(list, result.distToV(toId))
  }
  private def isTransition(from: Int, to: Int): Boolean =
    transitionList.exists(elem => elem.fromStationId == from && elem.toStationId == to || elem.toStationId == from && elem.fromStationId == to)
}
