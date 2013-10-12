/**
 *
 */
package com.lckymn.kevin.emailextractor.service;

import scala.util.matching.Regex
import com.lckymn.kevin.emailextractor.EmailAddress
import com.lckymn.kevin.emailextractor.EmailAddress
import com.lckymn.kevin.emailextractor.EmailAddress
import com.lckymn.kevin.emailextractor.AbstractExtractor

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
case class SimpleEmailAddressExtractor(val pattern: Regex) extends AbstractExtractor[EmailAddress] {
  override def extract(value: String): Set[EmailAddress] = {
    // # first
    //    (for {
    //      s <- value.split("[\\s]+").toSet[String]
    //      matcher = pattern.matcher(s)
    //      if (matcher.find)
    //      email = Option(matcher.group(0)).getOrElse("").trim
    //      if (!email.isEmpty())
    //    } yield EmailAddress(email)).toSet

    // # second
    //    (for {
    //      email <- pattern.findAllIn(value)
    //      if (!email.isEmpty())
    //    } yield EmailAddress(email)).toSet

    // # third
    //    pattern.findAllIn(value)
    //      .toSet[String]
    //      .map((email) => EmailAddress(email))

    extract(value, (email) => EmailAddress(email))
  }
}

object DefaultEmailAddressExtractor extends SimpleEmailAddressExtractor("([\\w-_.]+@[\\w-_]+(?:[.][\\w-_]+)+)".r)

object EmailAddressExtractor {
  def apply() = DefaultEmailAddressExtractor

  def apply(pattern: Regex) = SimpleEmailAddressExtractor(pattern)
}
