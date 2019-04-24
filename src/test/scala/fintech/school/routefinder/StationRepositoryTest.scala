package fintech.school.routefinder

import fintech.school.models.Station
import fintech.school.repositories.StationRepository
import org.scalatest.{AsyncFlatSpec, Matchers}
import java.sql.Time

import org.scalatest.concurrent.ScalaFutures


class StationRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  val stRep = new StationRepository("Volgograd")

  val stTime = new Time(23900000)
  val endTime = new Time(56600000)
  val st = new Station(1, "Space station", 1, stTime, endTime, 239)

  it should "get station from stations table" in {
    stRep.getById(1).map(res => res.name shouldBe "Chekistov square")
  }

  it should "insert create new row in stations table" in {
    stRep.create(st).map(res => res shouldBe "1 row inserted")
  }

  it should "delete row with given id in stations table" in {
    stRep.delete(239).map(res => res shouldBe "1 row deleted")
  }

}