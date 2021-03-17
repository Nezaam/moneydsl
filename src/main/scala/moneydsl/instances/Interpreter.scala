package moneydsl.instances

import moneydsl.dsl._

object Interpreter {
  implicit class AlgebraInterpreter(exp: Algebra) {
    protected def simplify: Value = exp match {
      case c: Const => c
      case d: Data => d

      case Sum(e1: Data, e2: Data) => Data(e1.value.init(e1.value.amount + e2.value.amount))
      case Sum(e1: Algebra, e2: Algebra) => Sum(e1.simplify, e2.simplify).simplify

      case Sub(e1: Data, e2: Data) => Data(e1.value.init(e1.value.amount - e2.value.amount))
      case Sub(e1: Algebra, e2: Algebra) => Sub(e1.simplify, e2.simplify).simplify

      case Prod(e1: Const, e2: Const) => Const(e1.value * e2.value)
      case Prod(e1: Const, e2: Data) => Data(e2.value.init(e1.value * e2.value.amount))
      case Prod(e1: Data, e2: Const) => Data(e1.value.init(e1.value.amount * e2.value))
      case Prod(e1: Data, e2: Data) => Data(e1.value.init(e1.value.amount * e2.value.amount))
      case Prod(e1: Algebra, e2: Algebra) => Prod(e1.simplify, e2.simplify).simplify

      case Div(e1: Const, e2: Const) => Const(e1.value / e2.value)
      case Div(e1: Const, e2: Data) => Data(e2.value.init(e1.value / e2.value.amount))
      case Div(e1: Data, e2: Const) => Data(e1.value.init(e1.value.amount / e2.value))
      case Div(e1: Data, e2: Data) => Const(e1.value.amount / e2.value.amount)
      case Div(e1: Algebra, e2: Algebra) => Div(e1.simplify, e2.simplify).simplify
    }

    def extract(algebra: Value): Money = algebra match {
      case Const(value) => Number(value)
      case Data(value) => value
    }

    def eval: Money = extract(simplify)

    def show: String = exp match {
      case Const(value) => value.toString
      case Data(value) => value.toString
      case Sum(e1, e2)   => s"(${e1.show} + ${e2.show})"
      case Sub(e1, e2)   => s"(${e1.show} - ${e2.show})"
      case Prod(e1, e2)  => s"(${e1.show} * ${e2.show})"
      case Div(e1, e2)   => s"(${e1.show} / ${e2.show})"
    }
  }
}
