package filters


import akka.util.ByteString
import play.api.http.HttpEntity
import play.api.mvc.{ActionRefiner, ResponseHeader, Result}

import scala.concurrent.{ExecutionContext, Future}

// Filter definition, can add data to request.filteredData or to stop the chain request validation
class Filters {
    def AddDateAndTimeFilter (implicit ec: ExecutionContext) = new ActionRefiner[FilterableRequest, FilterableRequest] {
        def executionContext: ExecutionContext = ec
        def refine[A](request: FilterableRequest[A]) = Future.successful {
            request.filteredData += ("ceva" -> "altceva")

//            Left(Filters.createResponse(200, "[Access denied]", "application/json"))
            Right(request)

        }
    }


    def AddBullshitFilter (implicit ec: ExecutionContext) = new ActionRefiner[FilterableRequest, FilterableRequest] {
        def executionContext: ExecutionContext = ec
        def refine[A](request: FilterableRequest[A]) = Future.successful {
            request.filteredData += ("bull" -> "shit")

            //            Left(Filters.createResponse(200, "[Access denied]", "application/json"))
            Right(request)

        }
    }

    // todo Authentication

}

object Filters {
    def createResponse(resposeStatus: Int, responseData: String, responseType: String) = {
        new Result(new ResponseHeader(resposeStatus), new HttpEntity.Strict(ByteString.apply(responseData), Some(responseType)))
    }
}