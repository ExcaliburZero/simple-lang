package simplelang

import scala.util.parsing.combinator._

sealed trait Statement
case class PrintStatement(expression: Expression) extends Statement

sealed trait Expression extends Statement
case class OperationExpression(ex1: Expression, op: Operator, ex2: Expression) extends Expression

sealed trait Operator
case class Equals() extends Operator
case class NotEquals() extends Operator
case class And() extends Operator
case class Or() extends Operator

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

  def equals: Parser[Operator] = {
    "==".r ^^ { _ => Equals() }
  }

  def notEquals: Parser[Operator] = {
    "!=".r ^^ { _ => NotEquals() }
  }

  def and: Parser[Operator] = {
    "&&".r ^^ { _ => And() }
  }

  def or: Parser[Operator] = {
    "\\|\\|".r ^^ { _ => Or() }
  }

  def operator: Parser[Operator] = {
    equals | notEquals | and | or
  }

  def operationExpression: Parser[Expression] = {
    "(" ~ expression ~ operator ~ expression ~ ")" ^^
      { case _ ~ ex1 ~ op ~ ex2 ~ _ => OperationExpression(ex1, op, ex2) }
  }

  def expression: Parser[Expression] = {
    operationExpression | value
  }

  def printStatement: Parser[Statement] = {
    "print ".r ~ expression ^^ { case _ ~ ex => PrintStatement(ex) }
  }

  def statement: Parser[Statement] = {
    printStatement | expression
  }
}
