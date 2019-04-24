package fintech.school.routefinder.RouteTest

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import fintech.school.routes.Router
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

class StationRouteTest extends FlatSpec with Matchers with ScalatestRouteTest with MockFactory with ScalaFutures{
  val router = new Router

  behavior of "getById"

  "Router" should "return Station" in {
    Get("/metro/StPetersburg/stations/1") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "{\"lineId\":1,\"name\":\"Technologichesky Institut 1\",\"status\":1,\"startWork\":{},\"endWork\":{},\"id\":1}"
    }
  }
}
