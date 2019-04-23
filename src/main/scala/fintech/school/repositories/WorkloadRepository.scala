package fintech.school.repositories

import java.sql.Timestamp

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._

import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class WorkloadRepository(city: String)(implicit executionContext: ExecutionContext) {
  val dataBase = new DatabaseConnection(city)

  def getById(wdLineId: Int): Future[Workload] = Future {
    dataBase.db withSession { implicit session ⇒
      val workloads = TableQuery[WorkloadsTable]
      workloads.filter(_.lineId === wdLineId).list.head
    }
  }

  def getWithFilters(wdLineId: Int, wdTime: Timestamp): Future[Workload] = Future {
    dataBase.db withSession { implicit session ⇒
      val workloads = TableQuery[WorkloadsTable]
      workloads
        .filter(_.lineId === wdLineId)
        .filter(_.time === wdTime)
        .list
        .head
    }
  }

  def create(params: Workload): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val workloads = TableQuery[WorkloadsTable]
      val n         = workloads.insert(params)
      s"$n row inserted"
    }
  }

  def update(params: Workload): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val workloads = TableQuery[WorkloadsTable]
      val n = workloads
        .filter(_.lineId === params.lineId)
        .filter(_.time === params.time)
        .update(params)
      s"$n row updated"
    }
  }

  def delete(wdLineId: Int, wdTime: Timestamp): Future[String] = Future {
    dataBase.db withSession { implicit session =>
      val workloads = TableQuery[WorkloadsTable]
      val n = workloads
        .filter(_.lineId === wdLineId)
        .filter(_.time === wdTime)
        .delete
      s"$n row deleted"
    }
  }
}
