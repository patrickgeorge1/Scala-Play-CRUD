package controllers

import services._
import play.api.libs.json._
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.ExecutionContext

import scala.language.postfixOps



class UsersController @Inject()(cc: MessagesControllerComponents,
                                userService: UserService)
                               (implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

    def index: Action[AnyContent] = Action {
        Ok("It s ok")
    }

    def getUsers = Action.async {
        userService.findAllUsers().map( user => Ok(Json.toJson(user)))
    }


    def getUser(id: Long): Action[AnyContent] = Action.async {
        // treat each response type
        val responseCases = userService.findUserById(id).map {
            case Some(user) => {
                Json.toJson(user)
            }
            case None => {
                JsObject(Seq("message" -> JsString("No user found")))
            }
        }
        // trow response
        responseCases.map {
            responseCase => Ok(responseCase)
        }
    }
}
