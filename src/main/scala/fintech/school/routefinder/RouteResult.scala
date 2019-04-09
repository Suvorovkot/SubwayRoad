package fintech.school.routefinder

import scala.annotation.tailrec

class RouteResult(edgeTo: Seq[Option[Edge]], distTo: Seq[Double]) {

  def pathTo(v: Int): Seq[Edge] = {

    @tailrec
    def go(list: List[Edge], vv: Int): List[Edge] =
      edgeTo(vv) match {
        case Some(e) => go(e +: list, e.from)
        case None    => list
      }

    if (!hasPath(v)) Seq() else go(List(), v)
  }

  def hasPath(v: Int): Boolean =
    distTo.lift(v).map(_ < Double.PositiveInfinity).get

  def distToV(v: Int): Double =
    distTo.lift(v).get

}
