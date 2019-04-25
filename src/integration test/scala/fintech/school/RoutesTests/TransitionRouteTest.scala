package fintech.school.RoutesTests

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{MessageEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import fintech.school.models.Transition
import fintech.school.routes.Router
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import fintech.school.tools.Json4sSupport._

class TransitionRouteTest extends FlatSpec with Matchers with ScalatestRouteTest with MockFactory with ScalaFutures{
  val router = new Router

  behavior of "get"

  "Router" should "return transition" in {
    Get("/metro/StPetersburg/transitions/1") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[List[Transition]] shouldBe List[Transition](Transition(1,2,5))
    }
  }

  behavior of "getAll"

  "Router" should "check transitions list" in {
    Get("/metro/StPetersburg/transitions") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[List[Transition]] should not be empty
    }
  }

  behavior of "create"

  "Router" should "post new transition" in {
    val newTransition = Transition(7,3,5)
    val transitionEntity = Marshal(newTransition).to[MessageEntity].futureValue
    val request = Post(uri = "/metro/StPetersburg/transitions").withEntity(transitionEntity)
    request ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[String] shouldBe "1 row inserted"
    }
  }

  //  behavior of "update"
  //
  //  "Router" should "update transition" in {
  //    val transitionEntity = Marshal(Transition(17,24,6)).to[MessageEntity].futureValue
  //    val request = Patch(uri = "/metro/StPetersburg/spans").withEntity(transitionEntity)
  //    request ~> router.routes ~> check {
  //      status shouldBe StatusCodes.OK
  //      responseAs[String] shouldBe "1 row updated"
  //    }
  //  }

  behavior of "delete"

  "Router" should "delete transition" in {
    val newTransition = Transition(7,3,5)
    val transitionEntity = Marshal(newTransition).to[MessageEntity].futureValue
    val request = Delete(uri = "/metro/StPetersburg/transitions").withEntity(transitionEntity)
    request ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
    }
    Get("/metro/StPetersburg/transitions") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      responseAs[List[Transition]].filter(elem => elem.fromStationId == 7 && elem.toStationId == 3) shouldBe empty
    }
  }

}
