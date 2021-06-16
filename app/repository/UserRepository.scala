package repository


import models.User
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import configuration.Contexts.{simpleDbLookupsContext, expensiveDbLookupsContext, expensiveCpuOperationsContext, dbWriteOperationsContext}


class UserRepository  @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

    protected val dbConfig = dbConfigProvider.get[JdbcProfile]

    import dbConfig._
    import profile.api._

    // Table, for ORM
    protected class UserTable(tag: Tag) extends Table[User](tag, "users") {
      def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
      def name = column[String]("name")
      def email = column[String]("email", O.Unique)
      def password = column[String]("password")
      def status = column[Int]("status")

      // It defines how the columns are converted to and from the Person object.
      def * = (id, name, email, password, status) <> ((User.apply _).tupled, User.unapply)
    }


    private val users = TableQuery[UserTable]

    // create user
    def create(name: String, email: String, password: String, status: Int): Future[User] = db.run {
      (users.map(user => (user.name, user.email, user.password, user.status))
        returning users.map(_.id)
        into ((data, id) => User(id, data._1, data._2, data._3, data._4))
          ) += (name, email, password, status)
    }

    // auxiliar
    def filterQuery(id: Long): Query[UserTable, User, Seq] = users.filter(_.id === id)

    def findById(id: Long): Future[Option[User]] = (db.run(filterQuery(id).result.headOption))
    def insert(user: User): Future[Int] = db.run(users += user)
    def update(id: Long, user: User): Future[Int] = db.run(filterQuery(id).update(user))
    def delete(id: Long): Future[Int] = db.run(filterQuery(id).delete)
    def list(): Future[Seq[User]] = db.run {users.result}
}
