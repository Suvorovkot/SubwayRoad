package fintech.school.RoutesTests

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import fintech.school.routes.Router
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FlatSpec, Matchers}

class ServiceRouteTest extends FlatSpec with Matchers with ScalatestRouteTest with MockFactory with ScalaFutures{
  val router = new Router

  behavior of "ServiceRoute"

  "ServiceRoute" should "return route" in {
    Get("/metro/StPetersburg/route?from=Admiralteyskaya&to=Novosherkasskaya") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe """{"route":[{"from":"Admiralteyskaya","to":"Sadovaya","time":2.0,"action":"Ride"},{"from":"Sadovaya","to":"Spasskaya","time":5.0,"action":"Change Line"},{"from":"Spasskaya","to":"Dostoyevskaya","time":2.0,"action":"Ride"},{"from":"Dostoyevskaya","to":"Ligovskiy Prospekt","time":2.0,"action":"Ride"},{"from":"Ligovskiy Prospekt","to":"Ploshad Alexandra Nevskogo 2","time":2.0,"action":"Ride"},{"from":"Ploshad Alexandra Nevskogo 2","to":"Novosherkasskaya","time":2.0,"action":"Ride"}],"duration":15.0,"massage":"Success"}"""
    }
  }

  "ServiceRoute" should "return empty route" in {
    Get("/metro/StPetersburg/route?from=Admiralteyskaya&to=Admiralteyskaya") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe """{"route":[],"duration":0.0,"massage":"Success"}"""
    }
  }
}
