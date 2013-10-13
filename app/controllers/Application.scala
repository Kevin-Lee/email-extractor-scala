package controllers

import models.EmailAddress
import com.lckymn.kevin.emailextractor.Extractor
import com.lckymn.kevin.emailextractor.service._
import models.EmailAddresses
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import com.lckymn.kevin.emailextractor.util.CommonUtil._

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
object Application extends Controller {

  val emailAddressExtractor: Extractor[EmailAddress] = EmailAddressExtractor()

  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        EmailAddressExtractorController.extract
      )
    ).as("text/javascript") 
  }
  
  def index = Action {
    Ok(views.html.index("0.0.3"))
  }
}