package fintech.school.routefinder

final case class Edge(from: Int, to: Int, weight: Double)

class EdgeList(val map: Map[Int, List[Edge]] = Map.empty){

  def addEdge(e: Edge): EdgeList ={

    val listFrom = this.map.getOrElse(e.from, List.empty)

    val listTo = this.map.getOrElse(e.to, List.empty)

    val map: Map[Int, List[Edge]] = this.map + (e.from -> (listFrom :+ e)) + (e.to -> (listTo :+ e))

    new EdgeList(map)
  }
}