/**
 *
 */
package controllers

import play.api._
import play.api.mvc._
import play.core.Router.JavascriptReverseRoute

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-10-14)
 */
trait JavascriptRoutable extends Controller {
  
  val javascriptReverseRoute: List[JavascriptReverseRoute]

  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        javascriptReverseRoute:_*
      )
    ).as("text/javascript") 
  }
}