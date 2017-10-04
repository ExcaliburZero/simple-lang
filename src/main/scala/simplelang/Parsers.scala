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

object Parsers {
  type Program = List[Statement]

}
