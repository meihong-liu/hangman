package cn.codingstyle.hangman

import org.scalatest._
import org.scalatest.Matchers._

class HangmanSpec extends FunSpec {
  describe("start game") {
    def expect(word: String, length: Int, problem: String) {
      val hangman = new Hangman(word)

      hangman.tries should be(12)
      hangman.used should be("AEIOU")

      hangman.length should be(length)
      hangman.problem should be(problem)
    }

    it("at initial") {
      expect(word = "APPLE", length = 5, problem = "A___E")
      expect(word = "GOOGLE",length = 6, problem = "_OO__E")
      expect(word = "AEIOU", length = 5, problem = "AEIOU")
      expect(word = "",      length = 0, problem = "")
    }
  }

  describe("play game") {
    val hangman = new Hangman("APPLE");

    it("guess success") {
      val newHangman = hangman.tryChar('P')

      newHangman.tries should be(12)
      newHangman.used should be("AEIOUP")
      newHangman.problem should be("APP_E")
    }

    it("guess fail") {
      val newHangman = hangman.tryChar('K')

      newHangman.tries should be(11)
      newHangman.used should be("AEIOUK")
      newHangman.problem should be("A___E")
    }
  }

  describe("final result") {
    var hangman = new Hangman("APPLE");

    def tryChars(chars: String) {
      chars.foreach(c => hangman = hangman.tryChar(c))
    }

    it("won") {
      tryChars("PL")
      hangman.won should be(true)
    }

    it("loss") {
      tryChars("KKKKKKKKKKKK")
      hangman.lost should be(true)
    }
  }
}
