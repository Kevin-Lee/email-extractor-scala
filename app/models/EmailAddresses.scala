/**
 *
 */
package models

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
case class EmailAddresses (
  inputValue: String
)

object EmailAddresses {
  def apply = new EmailAddresses("")
}