package fintech.school.RepostitoriesTests

import fintech.school.models.Span
import fintech.school.repositories.SpanRepository
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}

class SpanRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {
  behavior of "span repository"

  val spRep = new SpanRepository("Volgograd")

  val exisSp = new Span(1, 2, 2)

  it should "get all spans from spans table" in {
    spRep.getAll.map(res => res should contain(exisSp))
  }

  it should "get span by fromId from spans table" in {
    spRep.getById(exisSp.fromStationId).map(res => res should contain(exisSp))
  }

  it should "insert new row in spans table" in {
    val newSp = new Span(1, 9, 10)
    spRep.create(newSp).map(res => res shouldBe "1 row inserted")
  }

  val updSp = new Span(1, 9, 3)

  it should "update row in spans table" in {
    spRep.update(updSp).map(res => res shouldBe "1 row updated")
  }

  it should "delete row in spans table" in {
    spRep.delete(updSp).map(res => res shouldBe "1 row deleted")
  }
}
