package services

import models.User
import org.mockito.ArgumentMatchers.{any, anyLong}
import org.scalatestplus.play._
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import services.UserServiceSpecTest.userRepository
import repository.UserRepository

import scala.concurrent.{Await, Future}
import utils.Util

import scala.concurrent.duration.Duration
import scala.language.postfixOps


// mocking the dataSouce
object UserServiceSpecTest {
    // choose the target to be mocked
    val userRepository: UserRepository = mock(classOf[repository.UserRepository])

    // describe how the mocked target should behave
    when(userRepository.list()).thenReturn(
        Future.successful(Util.randomUsers())
    )
    when(userRepository.findById(anyLong())).thenReturn({
        val randomUser: Option[User] = scala.util.Random.nextInt(3) match {
            case i if i % 2 == 0 => Some(Util.randomUser())
            case _ => None
        }
        Future.successful(randomUser)
    })
    when(userRepository.insert(any[User])).thenReturn(Future.successful(scala.util.Random.nextInt(1000)))
    when(userRepository.update(anyLong(), any[User])).thenReturn(Future.successful(scala.util.Random.nextInt(1000)))
    when(userRepository.delete(anyLong())).thenReturn(Future.successful(scala.util.Random.nextInt(1000)))
}

// Tests that use a mocked data source
class UserServiceSpecTest
    extends PlaySpec with MockitoSugar {

    // test subject
    "UserService" must {
        // test 1
        "find user by id must be a future" in {
            val randomId = scala.util.Random.nextInt(1000)
            val randomUserFromDatasouce = userRepository.findById(randomId)
            randomUserFromDatasouce mustBe a[Future[_]]
        }

        // test 2
        " return all users as a future valid user list" in {
            val allUsersPromise = userRepository.list()
            val allUsers = Await.result(allUsersPromise, Duration.Inf)
            allUsers mustBe a[Seq[_]]
            allUsers must  not be equal(0)
        }
    }
}
