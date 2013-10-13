package com.lckymn.kevin.emailextractor.service

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import models.EmailAddress
import models.EmailAddress

class EmailAddressExtractorSuite extends Specification {

  trait Emails {
    val value1 = "test@test.com, tere@some.com, sdjsdjk@blah.com"
    val expectedEmailSet = Set(EmailAddress("test@test.com"),
      EmailAddress("tere@some.com"),
      EmailAddress("sdjsdjk@blah.com"))

    val value2 = "test@test.com dskljsdkjsd;dskjd another@email.com tere@some.com, sdkjdasiuodas sdjsdjk@blah.com. sdkljsdlkjasd"
    val expectedEmailSet2 = Set(EmailAddress("test@test.com"),
      EmailAddress("another@email.com"),
      EmailAddress("tere@some.com"),
      EmailAddress("sdjsdjk@blah.com"))

    val value3 = "test@test.com dskljsdkjsd;dskjd TEST@TEST.COM another@email.com tere@some.com, sdkjdasiuodas sdjsdjk@blah.com. sdkljsdlkjasd"
    val expectedEmailSet3 = Set(EmailAddress("test@test.com"),
      EmailAddress("another@email.com"),
      EmailAddress("tere@some.com"),
      EmailAddress("sdjsdjk@blah.com"))

    val empty = Set.empty

  }

  new Emails {
    """EmailAddressExtractor().extract("")""" should {
      """return Set()""" in {
        EmailAddressExtractor()
          .extract("") === empty
      }
    }
  }

  new Emails {
    s"""EmailAddressExtractor().extract("$value1")""" should {
      s"""return $expectedEmailSet""" in {
        EmailAddressExtractor()
          .extract(value1) === expectedEmailSet
      }
    }
  }

  new Emails {
    s"""EmailAddressExtractor()
  .extract("$value2")""" should {
      s"""return $expectedEmailSet2""" in {
        EmailAddressExtractor()
          .extract(value2) === expectedEmailSet2
      }
    }
  }

  new Emails {
    s"""EmailAddressExtractor("@".r)
  .extract("$value2")""" should {
      """return Set(EmailAddress("@"))""" in {
        EmailAddressExtractor("@".r)
          .extract(value2) === Set(EmailAddress("@"))
      }
    }
  }

  new Emails {
    s"""EmailAddressExtractor()
    .extract("$value3")""" should {
      s"""return $expectedEmailSet3""" in {
        EmailAddressExtractor()
          .extract(value3) === expectedEmailSet3
      }
    }
  }

}