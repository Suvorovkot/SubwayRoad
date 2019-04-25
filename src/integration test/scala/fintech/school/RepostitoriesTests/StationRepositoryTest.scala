package fintech.school.RepostitoriesTests

import java.sql.Time

import fintech.school.models.Station
import fintech.school.repositories.StationRepository
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}

class StationRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  val stTime  = Time.valueOf("06:00:00")
  val endTime = Time.valueOf("00:00:00")

  behavior of "station repository"

  val stRep = new StationRepository("Volgograd")

  val exisSt = Station(1, "Chekistov square", 1, stTime, endTime, 1)

  it should "get all stations from stations table" in {
    stRep.getAll.map(res => res should contain(exisSt))
  }

  it should "get all stations on chosen line from stations table" in {
    stRep.getAllByLine(1).map(res => res should contain(exisSt))
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
    val newSt = Station(1, "Space station", 1, stTime, endTime, 239)
    stRep.create(newSt).map(res => res shouldBe "1 row inserted")
  }

  it should "update row in stations table" in {
    val updSt = Station(1, "Space station", 0, stTime, endTime, 239)
    stRep.update(239, updSt).map(res => res shouldBe "1 row updated")
  }

  it should "delete row with given id in stations table" in {
    stRep.delete(239).map(res => res shouldBe "1 row deleted")
  }

}
