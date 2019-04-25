package fintech.school.RepostitoriesTests

import java.sql.Time

import fintech.school.models.Workload
import fintech.school.repositories.WorkloadRepository
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}

class WorkloadRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  behavior of "workload repository"

  val wdRep = new WorkloadRepository("Volgograd")

  val chTime  = Time.valueOf("12:00:00")
  val exisWd = Workload(1, chTime, 40)

  it should "get workload by line id from workloads table" in {
    wdRep.getById(exisWd.lineId).map(res => res.value shouldBe exisWd.value)
  }

  it should "get workload by line and time" in {
    wdRep.getWithFilters(exisWd.lineId, exisWd.time).map(res => res.value shouldBe exisWd.value)
  }
  
  it should "insert new row in workloads table" in {
    val newWd = Workload(2, chTime, 90)
    wdRep.create(newWd).map(res => res shouldBe "1 row inserted")
  }

  val updWd = Workload(2, chTime, 10)

  it should "update row in workloads table" in {
    wdRep.update(updWd).map(res => res shouldBe "1 row updated")
  }

  it should "delete row with given id in workloads table" in {
    wdRep.delete(updWd.lineId, updWd.time).map(res => res shouldBe "1 row deleted")
  }
}
