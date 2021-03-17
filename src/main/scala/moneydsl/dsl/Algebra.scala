package moneydsl.dsl

sealed trait Algebra
trait Value extends Algebra

final case class Const(value: BigDecimal) extends Value
final case class Data(value: Money) extends Value
final case class Sum(e1: Algebra, e2: Algebra) extends Algebra
final case class Sub(e1: Algebra, e2: Algebra) extends Algebra
final case class Prod(e1: Algebra, e2: Algebra) extends Algebra
final case class Div(e1: Algebra, e2: Algebra) extends Algebra