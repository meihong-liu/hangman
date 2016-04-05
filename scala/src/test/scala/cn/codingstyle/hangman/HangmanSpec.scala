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

    it("should be initialiazed") {
      expect(word = "APPLE", length = 5, problem = "A___E")
      expect(word = "GOOGLE",length = 6, problem = "_OO__E")
      expect(word = "AEIOU", length = 5, problem = "AEIOU")
      expect(word = "",      length = 0, problem = "")
    }

    it("try guess GOOGLE") {
      val hangman = new Hangman("GOOGLE");
      hangman.tries should be(12)
      hangman.used should be("AEIOU")
      hangman.length should be(6)
      hangman.problem should be("_OO__E")
    }

    it("try guess AEIOU") {
      val hangman = new Hangman("AEIOU");
      hangman.tries should be(12)
      hangman.used should be("AEIOU")
      hangman.length should be(5)
      hangman.problem should be("AEIOU")
    }

    it("try guess empty world") {
      val hangman = new Hangman("");
      hangman.tries should be(12)
      hangman.used should be("AEIOU")
      hangman.length should be(0)
      hangman.problem should be("")
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