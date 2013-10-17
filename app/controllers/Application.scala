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
import com.lckymn.kevin.web.JavascriptRoutable

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
object Application extends Controller with JavascriptRoutable {
  val javascriptReverseRoute = List(controllers.routes.javascript.EmailAddressExtractorController.extract)

  val emailAddressExtractor: Extractor[EmailAddress] = EmailAddressExtractor()
  
  def index = Action {
    Ok(views.html.index("0.0.6"))
  }
}