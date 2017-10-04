package simplelang

import org.scalatest.{Assertion, FlatSpec, Matchers}

class ParsersTests extends FlatSpec with Matchers {
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
}
