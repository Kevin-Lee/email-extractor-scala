/**
 *
 */
package controllers

import play.api._
import play.api.mvc._

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-10-14)
 */
trait JavascriptRoutable extends Controller {
  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        EmailAddressExtractorController.extract
      )
    ).as("text/javascript") 
  }
}