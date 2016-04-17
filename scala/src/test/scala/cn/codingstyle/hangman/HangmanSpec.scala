package cn.codingstyle.hangman

import org.scalatest._
import org.scalatest.prop._
import org.scalatest.Matchers._

class HangmanSpec extends FunSpec with TableDrivenPropertyChecks {
  describe("at initial") {
    val attemps = Table(
      ("word", "length", "problem"),
      ("APPLE", 5, "A___E"),
      ("GOOGLE",6, "_OO__E"),
      ("AEIOU", 5, "AEIOU"),
      ("",      0, "")
    )

    forAll(attemps) { (word, length, problem) =>
      val hangman = Hangman(word)
      hangman.length should be(length)
      hangman.problem should be(problem)
    }
  }

  describe("hangman game for APPLE") {
    val attemps = Table(
      ("chars",        "tries",  "used",    "problem", "won", "lost"),
      ("A",            11,       "AEIOU",   "A___E",   false, false),
      ("AA",           10,       "AEIOU",   "A___E",   false, false),
      ("P",            12,       "AEIOUP",  "APP_E",   false, false),
      ("PP",           11,       "AEIOUP",  "APP_E",   false, false),
      ("K",            11,       "AEIOUK",  "A___E",   false, false),
      ("KK",           10,       "AEIOUK",  "A___E",   false, false),
      ("PL",           12,       "AEIOUPL", "APPLE",   true,  false),
      ("KKKKKKKKKKKK", 0,        "AEIOUK",  "A___E",   false, true)
    )

    forAll(attemps) {(chars, tries, used, problem, won, lost) =>
      var hangman = Hangman("APPLE")

      def tryChars(chars: String) {
        chars.foreach(c => hangman = hangman.tryChar(c))
      }

      tryChars(chars)

      hangman.tries should be(tries)
      hangman.used should be(used)
      hangman.problem should be(problem)

      hangman.won should be(won)
      hangman.lost should be(lost)
    }
  }
}