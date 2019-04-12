package fintech.school.routefinder

import scala.collection.mutable

object RouteMaker {

  def calculate(V: EdgeList, startPoint: Int): RouteResult = {
    val size = V.map.size + 1

    val edgeTo = mutable.ArrayBuffer.fill[Option[Edge]](size)(None)
    val distTo = mutable.ArrayBuffer.fill(size)(Double.PositiveInfinity)

    distTo(startPoint) = 0.0
    val sourceDist = (startPoint, distTo(startPoint))
    val sortByWeight: Ordering[(Int, Double)] = (a, b) => a._2.compareTo(b._2)
    val queue = mutable.PriorityQueue[(Int, Double)](sourceDist)(sortByWeight)

    while (queue.nonEmpty) {

      val (minDestV, _) = queue.dequeue()
      val edges = V.map.getOrElse(minDestV, List.empty)

      edges.foreach { e =>
        if (distTo(e.to) > distTo(e.from) + e.weight) {
          distTo(e.to) = distTo(e.from) + e.weight
          edgeTo(e.to) = Some(e)
          if (!queue.exists(_._1 == e.to)) queue.enqueue((e.to, distTo(e.to)))
        }
      }
    }

    new RouteResult(edgeTo.toList, distTo)
  }
}
