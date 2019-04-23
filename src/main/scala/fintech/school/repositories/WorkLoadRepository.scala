package fintech.school.repositories

import fintech.school.controllers.DatabaseConnection
import fintech.school.models._
import scala.concurrent.{ExecutionContext, Future}
import scala.slick.driver.PostgresDriver.simple._

class WorkLoadRepository(implicit executionContext: ExecutionContext){
  val dataBase = new DatabaseConnection

  def getById(wdLineId: Int): Future[Workload] = Future {
    dataBase.db withSession { implicit session ⇒
      val workloads = TableQuery[WorkloadsTable]
      workloads.filter(_.lineId === wdLineId).list.head
    }
  }

  def getWithFilters(wdLineId: Int, wdTime: Int): Future[Workload] = Future {
    dataBase.db withSession { implicit session ⇒
      val workloads = TableQuery[WorkloadsTable]
      workloads.filter(_.lineId === wdLineId).filter(_.time === wdTime).list.head
    }
  }

  def create(params: Workload): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val workloads = TableQuery[WorkloadsTable]
      workloads.insert(params) == 1
    }
  }

  def update(params: Workload): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val workloads = TableQuery[WorkloadsTable]
      workloads.filter(_.lineId === params.lineId).filter(_.time === params.time).update(params) == 1
    }
  }

  def delete(wdLineId: Int, wdTime: Int): Future[Boolean] = Future {
    dataBase.db withSession { implicit session =>
      val workloads = TableQuery[WorkloadsTable]
      workloads.filter(_.lineId === wdLineId).filter(_.time === wdTime).delete == 1
    }
  }
}
