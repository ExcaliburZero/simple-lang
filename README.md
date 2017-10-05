# Simple Lang
A parser for a simple language written in Scala.

## Language Grammar
```
program ::= <statement> "\n" <program> ; NIL

statement ::= "print " <expression> ; <expression>

expression ::= "(" <expression> " " <operator> " " <expression> ")" ; <value>

operator ::= "==" ; "!=" ; "&&" ; "||"

value ::= <number> ; <boolean>

number ::= "[0-9]+"

boolean ::= "true" ; "false"
```
