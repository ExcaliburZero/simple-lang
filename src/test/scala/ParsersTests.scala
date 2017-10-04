import org.scalatest.{Assertion, FlatSpec, Matchers}

import simplelang._

import scala.util.parsing.combinator._

class ParsersTests extends FlatSpec with Matchers {
  /*
   * Parsers
   */
  "Parsers" should "allow one to represent a simple program" in {
    List(
        PrintStatement(
          OperationExpression(
            Number(1)
          , NotEquals()
          , Number(2)
          )
        )
      , OperationExpression(
            BooleanValue(true)
          , And()
          , BooleanValue(false)
        )
    )

    1 shouldBe 1
  }

  /*
   * Number
   */
  "Parsers.number" should "parse a positive number" in {
    val input = "1"
    val expected = Number(1)

    val output = Parsers.parse(Parsers.number, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a negative number" in {
    val input = "-1"

    val output = Parsers.parse(Parsers.number, input)

    output.successful shouldBe false
  }

  it should "fail to parse a non-number" in {
    val input = "skh23irhkdfs"

    val output = Parsers.parse(Parsers.number, input)

    output.successful shouldBe false
  }
 
  /*
   * True
   */
  "Parsers.trueValue" should "parse a true" in {
    val input = "true"
    val expected = BooleanValue(true)

    val output = Parsers.parse(Parsers.trueValue, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-true" in {
    val input = "false"

    val output = Parsers.parse(Parsers.trueValue, input)

    output.successful shouldBe false
  }
 
  /*
   * False
   */
  "Parsers.falseValue" should "parse a false" in {
    val input = "false"
    val expected = BooleanValue(false)

    val output = Parsers.parse(Parsers.falseValue, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-false" in {
    val input = "true"

    val output = Parsers.parse(Parsers.falseValue, input)

    output.successful shouldBe false
  }
 
  /*
   * Boolean
   */
  "Parsers.booleanValue" should "parse a true" in {
    val input = "true"
    val expected = BooleanValue(true)

    val output = Parsers.parse(Parsers.booleanValue, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "parse a false" in {
    val input = "false"
    val expected = BooleanValue(false)

    val output = Parsers.parse(Parsers.booleanValue, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-boolean" in {
    val input = "maybe"

    val output = Parsers.parse(Parsers.falseValue, input)

    output.successful shouldBe false
  }
 
  /*
   * Value
   */
  "Parsers.booleanValue" should "parse a positive number" in {
    val input = "128"
    val expected = Number(128)

    val output = Parsers.parse(Parsers.value, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "parse a boolean" in {
    val input = "false"
    val expected = BooleanValue(false)

    val output = Parsers.parse(Parsers.value, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-value" in {
    val input = "kjdghksf3j3j"

    val output = Parsers.parse(Parsers.value, input)

    output.successful shouldBe false
  }

  /*
   * Equals
   */
  "Parsers.equals" should "parse a equals" in {
    val input = "=="
    val expected = Equals()

    val output = Parsers.parse(Parsers.equals, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-equals" in {
    val input = "kdjfsfj"

    val output = Parsers.parse(Parsers.equals, input)

    output.successful shouldBe false
  }

  /*
   * Not Equals
   */
  "Parsers.notEquals" should "parse a not equals" in {
    val input = "!="
    val expected = NotEquals()

    val output = Parsers.parse(Parsers.notEquals, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-not equals" in {
    val input = "loifdggf"

    val output = Parsers.parse(Parsers.notEquals, input)

    output.successful shouldBe false
  }

  /*
   * And
   */
  "Parsers.and" should "parse an and" in {
    val input = "&&"
    val expected = And()

    val output = Parsers.parse(Parsers.and, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-and" in {
    val input = "djkfi"

    val output = Parsers.parse(Parsers.and, input)

    output.successful shouldBe false
  }

  /*
   * Or
   */
  "Parsers.or" should "parse an or" in {
    val input = "||"
    val expected = Or()

    val output = Parsers.parse(Parsers.or, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-or" in {
    val input = "kosigsdf0"

    val output = Parsers.parse(Parsers.or, input)

    output.successful shouldBe false
  }

  /*
   * Operator
   */
  "Parsers.operator" should "parse an equals" in {
    val input = "=="
    val expected = Equals()

    val output = Parsers.parse(Parsers.operator, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "parse a not equals" in {
    val input = "!="
    val expected = NotEquals()

    val output = Parsers.parse(Parsers.operator, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "parse an and" in {
    val input = "&&"
    val expected = And()

    val output = Parsers.parse(Parsers.operator, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "parse an or" in {
    val input = "||"
    val expected = Or()

    val output = Parsers.parse(Parsers.operator, input)

    output.successful shouldBe true
    output.get shouldBe expected
  }

  it should "fail to parse a non-operator" in {
    val input = "sdfsdkfj23rjk"

    val output = Parsers.parse(Parsers.operator, input)

    output.successful shouldBe false
  }
}
