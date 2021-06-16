package controllers

import javax.inject._
import play.api.mvc._
import services.AsyncService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AsyncController @Inject()(cc: MessagesControllerComponents,
                                asyncService: AsyncService)(implicit exec: ExecutionContext) extends MessagesAbstractController(cc) {


  def serveAsyncResponse: Action[AnyContent] = Action.async {
      asyncService.heavyComputational.map {
        result => Ok(result.toString)
      }
  }

}
