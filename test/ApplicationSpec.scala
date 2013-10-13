package test

import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.Json
import scala.collection.immutable.Set
import com.lckymn.kevin.emailextractor.util.CommonUtil._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in {
      running(FakeApplication()) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

    "render the index page" in {
      running(FakeApplication()) {
        val home = route(FakeRequest(GET, "/email-extractor-scala")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")
        contentAsString(home) must contain("<title>Extract Email Addresses</title>")
      }
    }

    "post no input JSON at all" in {
      running(FakeApplication()) {
        val result = route(FakeRequest(POST, "/email-extractor-scala")).get

        status(result) must equalTo(BAD_REQUEST)
        contentType(result) must beSome.which(_ == "text/html")
      }
    }

    "post with empty input JSON value" in {
      running(FakeApplication()) {
        val result = route(FakeRequest(POST, "/email-extractor-scala")
          .withJsonBody(Json.obj("inputValue" -> ""))).get

        status(result) must equalTo(OK)
        contentType(result) must beSome.which(_ == "application/json")
        Json.parse(contentAsString(result)) must be equalTo (
          Json.parse(
            s"""
              {
                "success": false,
                "message": "You did not submit any text.",
                "howMany": 0,
                "result": ""
              }
              """))
      }
    }

    "post with JSON containing no email address" in {
      running(FakeApplication()) {
        val result = route(FakeRequest(POST, "/email-extractor-scala")
          .withJsonBody(Json.obj("inputValue" -> "This doesn't contain any email addresses."))).get

        status(result) must equalTo(OK)
        contentType(result) must beSome.which(_ == "application/json")
        val resultMessage = "There is no email address found."
        val size = 0
        Json.parse(contentAsString(result)) must be equalTo (
          Json.parse(
            s"""
              {
                "success": true,
                "message": "${resultMessage}",
                "howMany": ${size},
                "result": ""
              }
              """))
      }
    }

    "post with JSON containing some email addresses" in {
      running(FakeApplication()) {
        val first = "kevin.lee@some.fake.testemailaddress.com"
        val second = "another@email.address.cc"
        val third = "the.third.one@test.com"
        val result = route(FakeRequest(POST, "/email-extractor-scala")
          .withJsonBody(Json.obj("inputValue" -> s"This contains some email addresses. ${first}, ${second} blah blah ${third}"))).get

        status(result) must equalTo(OK)
        contentType(result) must beSome.which(_ == "application/json")

        val resultSet = Set(first, second, third)
        val size = resultSet.size
        
        Json.parse(contentAsString(result)) must be equalTo (
          Json.parse(
            s"""
                {
                "success": true,
                "message": "${makeMessage(size)}",
                "howMany": ${size},
                "result": "${resultSet.mkString(", ")}"
                }
                """))
      }
    }
  }
}