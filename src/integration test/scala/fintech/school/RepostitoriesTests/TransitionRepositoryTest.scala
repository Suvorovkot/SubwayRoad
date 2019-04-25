package fintech.school.RepostitoriesTests

import fintech.school.models.Transition
import fintech.school.repositories.TransitionRepository
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}


class TransitionRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  behavior of "transition repository"

  val trRep = new TransitionRepository("Volgograd")

  val exisTr = Transition(2, 7, 5)

  it should "get all transitions from transitions table" in {
    trRep.getAll.map(res => res should contain(exisTr))
  }

  it should "get transition by fromId from transitions table" in {
    trRep.getById(exisTr.fromStationId).map(res => res should contain(exisTr))
  }

  it should "insert new row in transitions table" in {
    val newTr = Transition(1, 2, 10)
    trRep.create(newTr).map(res => res shouldBe "1 row inserted")
  }

  val updTr = Transition(1, 2, 20)

  it should "update row in transitions table" in {
    trRep.update(updTr).map(res => res shouldBe "1 row updated")
  }

  it should "delete row in transitions table" in {
    trRep.delete(updTr).map(res => res shouldBe "1 row deleted")
  }

}
