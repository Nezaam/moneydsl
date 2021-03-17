package moneydsl.instances

import moneydsl.dsl._

object Syntax {
  implicit class IntToRub(x: Int) {
    def rub: RUB = RUB(x)
  }
  implicit def intToNumber(x: Int): Number = Number(x)

  implicit class NumberSyntax(amount: Int) {
    def +(that: Int): Algebra = Sum(Const(amount), Const(that))

    def -(that: Int): Algebra = Sub(Const(amount), Const(that))

    def *(that: Int): Algebra = Prod(Const(amount), Const(that))
    def *(that: Money): Algebra = Prod(Const(amount), Data(that))
    def *(that: Algebra): Algebra = Prod(Const(amount), that)

    def /(that: Int): Algebra = Div(Const(amount), Const(that))
    def /(that: Money): Algebra = Div(Const(amount), Data(that))
    def /(that: Algebra): Algebra = Div(Const(amount), that)
  }

  implicit class MoneySyntax(money: Money) {
    def +(that: Money): Algebra = Sum(Data(money), Data(that))
    def +(that: Algebra): Algebra = Sum(Data(money), that)

    def -(that: Money): Algebra = Sub(Data(money), Data(that))
    def -(that: Algebra): Algebra = Sub(Data(money), that)

    def *(that: Number): Algebra = Prod(Data(money), Const(that.amount))
    def *(that: Money): Algebra = Prod(Data(money), Data(that))
    def *(that: Algebra): Algebra = Prod(Data(money), that)

    def /(that: Number): Algebra = Div(Data(money), Const(that.amount))
    def /(that: Money): Algebra = Div(Data(money), Data(that))
    def /(that: Algebra): Algebra = Div(Data(money), that)
  }

  implicit class AlgebraSyntax(val algebra: Algebra) {
    def +(that: Money): Algebra = Sum(algebra, Data(that))
    def +(that: Algebra): Algebra = Sum(algebra, that)

    def -(that: Money): Algebra = Sub(algebra, Data(that))
    def -(that: Algebra): Algebra = Sub(algebra, that)

    def *(that: Number): Algebra = Prod(algebra, Const(that.amount))
    def *(that: Money): Algebra = Prod(algebra, Data(that))
    def *(that: Algebra): Algebra = Prod(algebra, that)

    def /(that: Number): Algebra = Div(algebra, Const(that.amount))
    def /(that: Money): Algebra = Div(algebra, Data(that))
    def /(that: Algebra): Algebra = Div(algebra, that)
  }
}
