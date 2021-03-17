package moneydsl.dsl

sealed trait Money {
  def amount: BigDecimal
  def init(value: BigDecimal): Money
}

final case class Number(amount: BigDecimal)  extends Money {
  override def toString: String = amount.toString
  override def init(value: BigDecimal): Money = Number(value)
}

final case class RUB(amount: BigDecimal) extends Money {
  override def toString: String = s"$amount rub"
  override def init(value: BigDecimal): Money = RUB(value)
}