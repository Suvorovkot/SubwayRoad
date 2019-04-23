package fintech.school.routefinder

import fintech.school.models.{Span, Transition}

final case class Edge(from: Int, to: Int, weight: Double)

class EdgeList(val map: Map[Int, List[Edge]] = Map.empty) {

  def addEdge(e: Edge): EdgeList = {

    val listFrom = this.map.getOrElse(e.from, List.empty)

    val secondEdge = Edge(e.to, e.from, e.weight)
    val listTo = this.map.getOrElse(secondEdge.from, List.empty)

    val map: Map[Int, List[Edge]] = this.map + (e.from -> (listFrom :+ e)) + (secondEdge.from -> (listTo :+ secondEdge))

    new EdgeList(map)
  }

  def addListEdges(list: List[Edge]): EdgeList = {
    var edges = this
    for (i <- list){
      edges = edges.addEdge(i)
    }
    edges
  }

  def prepareSpans(spanList: List[Span]): EdgeList = {
    var edges = spanList.collect{
      case elem => Edge(elem.fromStationId, elem.toStationId, elem.value)
    }
    var edgeList = new EdgeList(this.map)
    edgeList.addListEdges(edges)
  }

  def prepareTransitions(transitionList: List[Transition]): EdgeList = {
    var edges = transitionList.collect{
      case elem => Edge(elem.fromStationId, elem.toStationId, elem.value)
    }
    var edgeList = new EdgeList(this.map)
    edgeList.addListEdges(edges)
  }
}
