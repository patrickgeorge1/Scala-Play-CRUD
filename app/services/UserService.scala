package services

import repository._
import models._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}




class UserService @Inject() (userRepository: UserRepository, ec: ExecutionContext) {

    def findUserById(id: Long): Future[Option[User]] = {
        userRepository.findById(id)
    }

    def findAllUsers(): Future[Seq[User]] = userRepository.list()
}
