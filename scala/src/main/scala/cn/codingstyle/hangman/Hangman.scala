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
    copy(used = newUsed(c), tries = newTries(c))

  private def newUsed(c: Char): String =
    if (!used.contains(c)) used + c else used

  private def newTries(c: Char): Int =
    if (tryFailed(c)) tries - 1 else tries

  private def tryFailed(c: Char): Boolean = 
    used.contains(c) || !solution.contains(c)
}