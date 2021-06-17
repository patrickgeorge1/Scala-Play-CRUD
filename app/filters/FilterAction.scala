package filters

import javax.inject.Inject
import play.api.mvc._

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}


class FilterableRequest[A] (request: Request[A]) extends WrappedRequest[A](request) {
    val filteredData: mutable.HashMap[String, String] = new mutable.HashMap()
}


// Action Builder that transform A request into a Request with a data hashmap "FilterableRequest
class FilterAction @Inject()(val parser: BodyParsers.Default)(implicit val executionContext: ExecutionContext)
    extends ActionBuilder[FilterableRequest, AnyContent]
    with ActionTransformer[Request, FilterableRequest]
{
    def transform[A](request: Request[A]): Future[FilterableRequest[A]] = Future.successful {
        new FilterableRequest(request)
    }
}



