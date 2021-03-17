package moneydsl

import org.specs2.Specification
import instances.Interpreter._
import instances.Syntax._
import moneydsl.dsl.Number
import org.specs2.matcher.MatchResult


class MoneySpec extends Specification{
  override def is =
    s2"""
      Test for Money DSL
      10.rub + 20.rub == 30.rub must be true                    $e1
      20.rub - 10.rub == 10.rub must be true                    $e2
      2 * 5.rub == 10.rub must be true                          $e3
      5.rub * 2 == 10.rub must be true                          $e4
      10.rub / 2 == 5.rub must be true                          $e5
      10.rub / 5.rub == 2 must be true                          $e6
      2 * 3.rub + 4.rub == 10.rub must be true                  $e7
      2 * 3.rub + 4.rub == '((2 * 3 rub) + 4 rub)' must be true $e8
      """

  def e1: MatchResult[Boolean] = (10.rub + 20.rub).eval == 30.rub must beTrue
  def e2: MatchResult[Boolean] = (20.rub - 10.rub).eval == 10.rub must beTrue
  def e3: MatchResult[Boolean] = (2 * 5.rub).eval == 10.rub must beTrue
  def e4: MatchResult[Boolean] = (5.rub * 2).eval == 10.rub must beTrue
  def e5: MatchResult[Boolean] = (10.rub / 2).eval == 5.rub must beTrue
  def e6: MatchResult[Boolean] = (10.rub / 5.rub).eval == Number(2) must beTrue
  def e7: MatchResult[Boolean] = (2 * 3.rub + 4.rub).eval == 10.rub must beTrue
  def e8: MatchResult[Boolean] = (2 * 3.rub + 4.rub).show == "((2 * 3 rub) + 4 rub)" must beTrue

}
