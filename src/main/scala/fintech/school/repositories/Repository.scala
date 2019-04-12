package fintech.school.repositories

import scala.concurrent.Future

trait Repository[A] {
  def getAll(): Future[List[A]]

  def getById(id: Int): Future[Option[A]]

  def getByName(name: String): Future[Option[A]]

  def create(params: A): Future[A]

  def update(id: Int, params: A): Future[Option[A]]

  def delete(id: Int): Future[Boolean]
}

