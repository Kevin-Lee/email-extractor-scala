package controllers

import models.EmailAddresses
import com.lckymn.kevin.emailextractor.service._
import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import com.lckymn.kevin.emailextractor.Extractor
import com.lckymn.kevin.emailextractor.EmailAddress


/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
object Application extends Controller {

  val emailAddressExtractor: Extractor[EmailAddress] = EmailAddressExtractor()

  val emailAddressesForm: Form[EmailAddresses] = Form(mapping("inputValue" -> text) {
    (inputValue) => EmailAddresses(inputValue)
  } {
    emailAddresses => Some(emailAddresses.inputValue)
  })

  def index = Action {
    Ok(views.html.index(emailAddressesForm))
  }

  def extract = Action { implicit request =>

    val binded = emailAddressesForm.bindFromRequest
    val inputValue = binded.get.inputValue
    if (inputValue.isEmpty) {
      binded.fold(
        errors => Ok(views.html.index(emailAddressesForm, message = "ERROR")),
        emailAddresses =>
          Ok(views.html.index(emailAddressesForm,
            message = "You did not submit any text.")))
    }
    else {
      val resultSet = emailAddressExtractor.extract(inputValue)
        .map(email => email.emailAddress)
      val result = resultSet.mkString(", ")

      val size = resultSet.size

      val resultMessage =
        if (0 == size)
        "There is no email address found."
        else
          s"There ${if (1 == size) "is 1 email address" else s"are $size email addresses"} extracted from the given text."

      binded.fold(
        errors => Ok(views.html.index(emailAddressesForm, message = "ERROR")),
        emailAddresses =>
          Ok(views.html.index(emailAddressesForm,
            previousInput = inputValue,
            resultMessage = resultMessage,
            result = result)))
    }
  }
}