/**
 *
 */
package com.lckymn.kevin.emailextractor.service;

import scala.util.matching.Regex
import models.EmailAddress
import models.EmailAddress
import models.EmailAddress
import com.lckymn.kevin.emailextractor.AbstractExtractor

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
case class SimpleEmailAddressExtractor(val pattern: Regex) extends AbstractExtractor[EmailAddress] {
  override def extract(value: String): Set[EmailAddress] = {
    extract(value, (email) => EmailAddress(email))
  }
}

object DefaultEmailAddressExtractor extends SimpleEmailAddressExtractor("([a-zA-Z0-9]+([-_\\.]+[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*(?:[.][a-zA-Z0-9]+([-_]+[a-zA-Z0-9]+)*)+)".r)

object EmailAddressExtractor {
  def apply() = DefaultEmailAddressExtractor

  def apply(pattern: Regex) = SimpleEmailAddressExtractor(pattern)
}
