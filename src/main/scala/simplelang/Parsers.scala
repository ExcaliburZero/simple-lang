package simplelang

import scala.util.parsing.combinator._

sealed trait Statement
case class PrintStatement(expression: Expression) extends Statement

sealed trait Expression extends Statement
case class OperationExpression(ex1: Expression, op: Operation, ex2: Expression) extends Expression

sealed trait Operation
case class Equals() extends Operation
case class NotEquals() extends Operation
case class And() extends Operation
case class Or() extends Operation

sealed trait Value extends Expression
case class Number(number: Int) extends Value
case class BooleanValue(boolean: Boolean) extends Value

object Parsers extends RegexParsers {
  type Program = List[Statement]

  def number: Parser[Number] = {
    "[0-9]+".r ^^ { str => Number(str.toInt) }
  }

  def trueValue: Parser[BooleanValue] = {
    "true".r ^^ { _ => BooleanValue(true) }
  }

  def falseValue: Parser[BooleanValue] = {
    "false".r ^^ { _ => BooleanValue(false) }
  }

  def booleanValue: Parser[BooleanValue] = {
    trueValue | falseValue
  }

  def value: Parser[Value] = {
    number | booleanValue
  }
}
