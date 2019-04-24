package fintech.school.routefinder

import java.sql.Timestamp

import fintech.school.models.{Span, Station, Transition}
import fintech.school.repositories.{SpanRepository, StationRepository, TransitionRepository}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}


class RepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  val stTime = Timestamp.valueOf("1970-01-01 06:00:00.0")
  val endTime = Timestamp.valueOf("1970-01-01 00:00:00.0")
  behavior of "station repository"

  val stRep = new StationRepository("Volgograd")

  val exisSt = new Station(1, "Chekistov square", 1, stTime, endTime, 1)

  it should "get all stations from stations table" in {
    stRep.getAll.map(res => res should contain (exisSt))
  }

  it should "get all stations on chosen line from stations table" in {
    stRep.getAllByLine(1).map(res => res should contain (exisSt))
  }

  it should "get return no stations on chosen line from stations table" in {
    stRep.getAllByLine(999).map(res => res shouldBe List())
  }

  it should "get station by id from stations table" in {
    stRep.getById(exisSt.id).map(res => res.name shouldBe exisSt.name)
  }

  it should "get station by name from stations table" in {
    stRep.getByName(exisSt.name).map(res => res.name shouldBe exisSt.name)
  }

  it should "insert new row in stations table" in {
    val newSt = new Station(1, "Space station", 1, stTime, endTime, 239)
    stRep.create(newSt).map(res => res shouldBe "1 row inserted")
  }

  it should "update row in stations table" in {
    val updSt = new Station(1, "Space station", 0, stTime, endTime, 239)
    stRep.update(239, updSt).map(res => res shouldBe "1 row updated")
  }

  it should "delete row with given id in stations table" in {
    stRep.delete(239).map(res => res shouldBe "1 row deleted")
  }


  behavior of "transition repository"

  val trRep = new TransitionRepository("Volgograd")

  val exisTr = new Transition(2, 7, 5)

  it should "get all transitions from transitions table" in {
    trRep.getAll.map(res => res should contain (exisTr))
  }

  it should "get transition by fromId from transitions table" in {
    trRep.getById(exisTr.fromStationId).map(res => res should contain (exisTr))
  }

  it should "insert new row in transitions table" in {
    val newTr = new Transition(1,2, 10)
    trRep.create(newTr).map(res => res shouldBe "1 row inserted")
  }

  val updTr = new Transition(1,2, 20)

  it should "update row in transitions table" in {
    trRep.update(updTr).map(res => res shouldBe "1 row updated")
  }

  it should "delete row in transitions table" in {
    trRep.delete(updTr).map(res => res shouldBe "1 row deleted")
  }

  behavior of "span repository"

  val spRep = new SpanRepository("Volgograd")

  val exisSp = new Span(1, 2, 2)

  it should "get all spans from spans table" in {
    spRep.getAll.map(res => res should contain (exisSp))
  }

  it should "get span by fromId from spans table" in {
    spRep.getById(exisSp.fromStationId).map(res => res should contain (exisSp))
  }

  it should "insert new row in spans table" in {
    val newSp = new Span(1,9, 10)
    spRep.create(newSp).map(res => res shouldBe "1 row inserted")
  }

  val updSp = new Span(1,9, 3)

  it should "update row in spans table" in {
    spRep.update(updSp).map(res => res shouldBe "1 row updated")
  }

  it should "delete row in spans table" in {
    spRep.delete(updSp).map(res => res shouldBe "1 row deleted")
  }

}