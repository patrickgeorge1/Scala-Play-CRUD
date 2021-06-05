package controllers

import akka.util.ByteString

import javax.inject._
import play.api.mvc._
import play.api.http.HttpEntity

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    val ok = Ok("You will survive ")
    ok
//    Ok(views.html.index("Your new application is ready."))
  }

  def hello(name: String) = Action {
//    Result(
//      header = ResponseHeader(200, Map.empty),
//      body = HttpEntity.Strict(ByteString("Hello " + name), Some("text/plain"))
//    )
    Ok(<h1>"Hello, " + name + " !"</h1>).as("text/html")
  }

}
