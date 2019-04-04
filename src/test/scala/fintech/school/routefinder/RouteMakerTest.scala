package fintech.school.routefinder

import org.scalatest.{AsyncFlatSpec, Matchers}

class RouteMakerTest extends AsyncFlatSpec with Matchers {

  val testList = new EdgeList()
    .addEdge(Edge(0,5,14.0))
    .addEdge(Edge(0,2,9.0))
    .addEdge(Edge(0,1,7.0))
    .addEdge(Edge(1,2,10.0))
    .addEdge(Edge(1,3,15.0))
    .addEdge(Edge(2,3,11.0))
    .addEdge(Edge(2,5,2.0))
    .addEdge(Edge(3,4,6.0))
    .addEdge(Edge(4,5,9.0))

  behavior of "calculate"

  it should "return shortest way from 0 to 1" in {
    RouteMaker.calculate(testList,0).pathTo(1) should be List(Edge(0,1,7.0))
  }
}
