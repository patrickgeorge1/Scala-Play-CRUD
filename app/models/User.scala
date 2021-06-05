package models

import play.api.libs.json._

case class User(id: Long, name:String, email:String, password:String, status:Int)

object User {
  implicit val userFormat: OFormat[User] = Json.format[User]
}
