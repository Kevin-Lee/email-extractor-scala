/**
 *
 */
package models

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
case class EmailAddress(val emailAddress: String) {
  override val hashCode = emailAddress.toLowerCase.##

  override def equals(obj: Any) = obj match {
    case EmailAddress(email) => emailAddress.toLowerCase == email.toLowerCase
    case _ => false
  }
}
