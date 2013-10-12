/**
 *
 */
package com.lckymn.kevin.emailextractor

import scala.util.matching.Regex

/**
 *
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2013-03-20)
 */
trait Extractor[T] {
  def extract(value: String): Set[T]
}

abstract trait AbstractExtractor[T] extends Extractor[T] {
  val pattern: Regex
  protected def extract(value: String, mapper: String => T): Set[T] = {
    pattern.findAllIn(value)
      .toSet[String]
      .map(mapper)
  }
}