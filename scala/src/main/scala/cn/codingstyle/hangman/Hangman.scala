package cn.codingstyle.hangman

case class Hangman
  ( solution: String,
    used: String = "AEIOU",
    tries: Int = 12) {

  def length = solution.length
  def won = solution == problem
  def lost = tries == 0

  def problem = {
    def mask(c: Char) = if (used.contains(c)) c else '_'
    solution.map(mask).mkString
  }

  def tryChar(c: Char): Hangman =
    copy(used = used + c, tries = newTries(c))

  private def newTries(c: Char): Int =
    if (solution.contains(c)) tries else tries - 1
}