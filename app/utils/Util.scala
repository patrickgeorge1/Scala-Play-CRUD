package utils

import models.User

object Util {

    def randomString(length: Int): String = {
        val r = new scala.util.Random
        val sb = new StringBuilder
        for (i <- 1 to length) {
            sb.append(r.nextPrintableChar())
        }
        sb.toString
    }

    def randomEmail(): String = {
        val length = 3 + scala.util.Random.nextInt(10)
        val email = randomString(length) + "@domain.com"
        email
    }

    def randomUser(): User = {
        val length = 5 + scala.util.Random.nextInt(10)
        val id = scala.util.Random.nextInt(1000)
        val name = randomString(length)
        val email = randomEmail()
        val password = randomString(length + 13)
        val status = scala.util.Random.nextInt(2)
        new User(id, name, email, password, status)
    }


    def randomUsers(): Seq[User] = {
        val length = 2 + scala.util.Random.nextInt(14)


        val ceva = for (_ <- 1 until length)
                yield randomUser()
        ceva
    }


}
