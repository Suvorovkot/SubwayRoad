package fintech.school.routefinder

import java.sql.Time

import fintech.school.models.{Span, Line, Transition}
import fintech.school.repositories.{SpanRepository, LineRepository, TransitionRepository}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{AsyncFlatSpec, Matchers}


class LineRepositoryTest extends AsyncFlatSpec with Matchers with ScalaFutures {

  behavior of "line repository"
  
  val lnRep = new LineRepository("Volgograd")

  val exisLn = Line("ST-1", 1, "red", 1)

  it should "get all lines from lines table" in {
    lnRep.getAll.map(res => res should contain(exisLn))
  }

  it should "get line by id from lines table" in {
    lnRep.getById(exisLn.id).map(res => res.name shouldBe exisLn.name)
  }

  it should "get line by name from lines table" in {
    lnRep.getByName(exisLn.name).map(res => res.name shouldBe exisLn.name)
  }

  it should "get line by color from lines table" in {
    lnRep.getByColor(exisLn.colour).map(res => res.colour shouldBe exisLn.colour)
  }

  it should "insert new row in lines table" in {
    val newLn = Line("Zipline", 239, "white", 566)
    lnRep.create(newLn).map(res => res shouldBe "1 row inserted")
  }

  it should "update row in lines table" in {
    val updLn = Line("Zipline", 239, "black", 566)
    lnRep.update(566, updLn).map(res => res shouldBe "1 row updated")
  }

  it should "delete row with given id in lines table" in {
    lnRep.delete(566).map(res => res shouldBe "1 row deleted")
  }

}
