package controllers

import com.lckymn.kevin.emailextractor.EmailAddress
import com.lckymn.kevin.emailextractor.Extractor
import com.lckymn.kevin.emailextractor.service._

import models.EmailAddresses
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
object Application extends Controller {

  val emailAddressExtractor: Extractor[EmailAddress] = EmailAddressExtractor()

  def index = Action {
    Ok(views.html.index("0.0.2"))
  }

  implicit val rds = (
    (__ \ 'inputValue).read[String])

  def extract = Action(parse.json) { implicit request =>
    request.body.validate[String].map {
      case (inputValue) => {
        inputValue match {
          case "" =>
            Ok(Json.parse(s"""
              {
                "success": false,
                "message": "You did not submit any text."
              }
              """))
          case _ =>

            val resultSet = emailAddressExtractor.extract(inputValue)
              .map(email => email.emailAddress)
            val result = resultSet.mkString(", ")

            val size = resultSet.size

            val resultMessage =
              if (0 == size)
                "There is no email address found."
              else
                s"There ${if (1 == size) "is 1 email address" else s"are $size email addresses"} extracted from the given text."

            Ok(Json.parse(s"""
              {
                "success": true,
                "message": "${resultMessage}",
                "result": "${result}"
              }
              """))
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