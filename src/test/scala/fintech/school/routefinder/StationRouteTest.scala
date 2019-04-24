package fintech.school.routefinder

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{MessageEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import fintech.school.models.Station
import fintech.school.routes.Router
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import fintech.school.tools.Json4sSupport._

class StationRouteTest extends FlatSpec with Matchers with ScalatestRouteTest with MockFactory with ScalaFutures{
  val router = new Router

  behavior of "getById"

  "Router" should "return Station" in {
    Get("/metro/StPetersburg/stations/1") ~> router.routes ~> check {
      status shouldBe StatusCodes.OK
      response shouldBe """{"lineId":1,"name":"Technologichesky Institut 1","status":1,"startWork":"1970-01-01T03:00:00Z","endWork":"1970-01-01T03:00:00Z","id":1}"""
    }
  }

//  behavior of "getAll"
//
//  "Router" should "return list of Stations" in {
//    Get("/metro/StPetersburg/stations") ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//      response shouldBe a List[Station]
//    }
//  }
//
//  behavior of "create"
//
//  "Router" should "post new Station" in {
//    val newStation = Station(1,"Berliozovskay",1,Timestamp("1970-01-01T03:00:00Z","1970-01-01T03:00:00Z",26)
//    val todoEntity = Marshal(newStation).to[MessageEntity].futureValue
//    val request = Post(uri = "/api/todos").withEntity(todoEntity)
//    request ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//      responseAs[Todo] shouldBe newTodo
//    }
//  }
//
//  behavior of "getByOwner"
//
//  "Router" should "return todo with certain userId" in {
//    val userId = 666
//    Get(s"/api/todos?userId=$userId") ~> router.routes ~> check {
//      responseAs[List[Todo]].forall(_.userId == userId) shouldBe true
//    }
//  }
//
//  behavior of "put"
//
//  "Router" should "return put todo" in {
//    val putTodo = Todo(id = 123, userId = 1, title = "put", completed = true)
//    val TodoEntity = Marshal(putTodo).to[MessageEntity].futureValue
//    Put(uri = "/api/todos/1").withEntity(TodoEntity) ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//      responseAs[Todo] shouldBe putTodo.copy(id = 1)
//    }
//  }
//
//  behavior of "update"
//
//  "Router" should "update todo" in {
//    val TodoEntity = Marshal(TodoParams(completed = Some(true))).to[MessageEntity].futureValue
//    val request = Patch(uri = "/api/todos/1").withEntity(TodoEntity)
//    request ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//      responseAs[Todo].completed shouldBe true
//    }
//  }
//
//  behavior of "delete"
//
//  "Router" should "delete todo" in {
//    Delete("/api/todos/2") ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//    }
//    Get("/api/todos") ~> router.routes ~> check {
//      status shouldBe StatusCodes.OK
//      responseAs[List[Todo]].forall(_.id != 2) shouldBe true
//    }
//  }

}
