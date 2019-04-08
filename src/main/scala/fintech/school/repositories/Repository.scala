package fintech.school.repositories

import scala.concurrent.Future

trait Repository[A, B] {
  def getAll(): Future[List[A]]

  def getById(id: Int): Future[Option[A]]

  def getByName(name: String): Future[Option[A]]

  def create(params: B): Future[A]

  def update(id: Int, params: B): Future[Option[A]]

  def delete(id: Int): Future[Boolean]
}

