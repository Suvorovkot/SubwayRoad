package fintech.school.routefinder.ServiceTest

import fintech.school.routefinder.{Edge, EdgeList, RouteMaker}
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

  val testList1 = new EdgeList()
    .addEdge(Edge(0,1,7.0))
    .addEdge(Edge(1,2,10.0))
    .addEdge(Edge(3,4,6.0))
    .addEdge(Edge(4,5,9.0))



  behavior of "routes"

  it should "return shortest way from 0 to 1" in {
    RouteMaker.calculate(testList,0).pathTo(1) shouldBe List(Edge(0,1,7.0))
  }

  it should "return distance of shortest way from 0 to 3" in {
    RouteMaker.calculate(testList,0).distToV(3) shouldBe 20.0
  }

  it should "return empty List for the same station" in {
    RouteMaker.calculate(testList,0).pathTo(0) shouldBe List()
  }

  it should "return false for no existing path" in {
    RouteMaker.calculate(testList1,0).hasPath(5) shouldBe false
  }
}
