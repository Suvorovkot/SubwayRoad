package fintech.school.repositories

import fintech.school.models._

import scala.concurrent.Future

trait SpanRepository {

  def getAll(): Future[List[Span]]

  def getFrom(idTo: Long): Future[Option[Span]]

  def getTo(idFrom: Long): Future[Option[Span]]

  def create(params: Span): Future[Span]

  def update(id: Long, params: Span): Future[Option[Span]]

  def delete(id: Long): Future[Boolean]
}
