/**
 *
 */
package com.lckymn.kevin.emailextractor.util

import play.api.libs.json.Json
import play.api.libs.json.JsValue

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-10-14)
 */
object CommonUtil {

  def makeMessage(size: Int): String = {
    if (0 == size)
      "There is no email address found."
    else
      s"There ${if (1 == size) "is 1 email address" else s"are $size email addresses"} extracted from the given text."
  }

  def toResultJson(isSuccess: Boolean, message: String, howMany: Int, result: String): JsValue = {
    Json.parse(s"""
              {
                "success": ${isSuccess},
                "message": "${message}",
                "howMany": ${howMany},
                "result": "${result}"
              }
              """)
  }

  def toResultJson(message: String): JsValue =
    toResultJson(false, message, 0, "")
}