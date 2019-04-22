package fintech.school.repositories

import fintech.school.models.Workload
import scala.concurrent.{ExecutionContext, Future}

class WorkLoadRepository { // запара в том, что получать/удалять/обновлять надо по двум параметрам: id, time

  def getById(id: Int): Future[List[Workload]] = ???

  def getWithFilters(name: String): Future[Workload] = ??? // get by id and time

  def create(params: Workload): Future[Workload] = ???

  def update(params: Workload): Future[Workload] = ??? // получить по id и time и заменить значение

  def delete(params: Workload): Future[Boolean] = ???


}
