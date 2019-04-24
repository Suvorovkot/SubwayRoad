package fintech.school.routefinder.RouteTest

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{MessageEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import fintech.school.models.Span
import fintech.school.routes.Router
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import fintech.school.tools.Json4sSupport._

class SpanRouteTest extends FlatSpec with Matchers with ScalatestRouteTest with MockFactory with ScalaFutures{
  val router = new Router

  behavior of "get"

  "Router" should "return span" in {
    Get("/metro/StPetersburg/spans/1") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[List[Span]] shouldBe List[Span](Span(7,1,2),Span(1,8,2))
    }
  }

  behavior of "getAll"

  "Router" should "check spans list" in {
    Get("/metro/StPetersburg/spans") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[List[Span]] should not be empty
    }
  }

  behavior of "create"

  "Router" should "post new span" in {
    val newSpan = Span(15,22,2)
    val todoEntity = Marshal(newSpan).to[MessageEntity].futureValue
    val request = Post(uri = "/metro/StPetersburg/spans").withEntity(todoEntity)
    request ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "1 row inserted"
    }
  }

//  behavior of "update"
//
//  "Router" should "update span" in {
//    val spanEntity = Marshal(Span(24,25,3)).to[MessageEntity].futureValue
//    val request = Patch(uri = "/metro/StPetersburg/spans").withEntity(spanEntity)
//    request ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//      responseAs[String] shouldBe "1 row updated"
//    }
//  }

  behavior of "delete"

  "Router" should "delete span" in {
    val newSpan = Span(15,22,2)
    val todoEntity = Marshal(newSpan).to[MessageEntity].futureValue
    val request = Delete(uri = "/metro/StPetersburg/spans").withEntity(todoEntity)
    request ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
    }
    Get("/metro/StPetersburg/spans") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[List[Span]].filter(elem => elem.fromStationId == 15 && elem.toStationId == 22) shouldBe empty
    }
  }
}
