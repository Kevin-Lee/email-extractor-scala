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
import java.util.concurrent.TimeUnit

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
object EmailAddressExtractorController extends Controller {

  val emailAddressExtractor: Extractor[EmailAddress] = EmailAddressExtractor()

  implicit val rds = (
    (__ \ 'inputValue).read[String])

  def extract = Action(parse.json) { implicit request =>
    request.body.validate[String].map {
      case (inputValue) => {
        inputValue match {
          case "" =>
            Ok(toResultJson("You did not submit any text."))
          case _ =>
            val resultSet = emailAddressExtractor.extract(inputValue)
              .map(email => email.emailAddress)
            val size = resultSet.size
            Ok(toResultJson(true, makeMessage(size), size, resultSet.mkString(", ")))
        }
      }
    }.recoverTotal {
      e =>
        {
          BadRequest("Detected error:" + JsError.toFlatJson(e))
        }
    }
  }
}