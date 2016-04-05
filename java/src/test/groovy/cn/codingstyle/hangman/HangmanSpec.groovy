package cn.codingstyle.hangman

import spock.lang.Specification

class HangmanSpec extends Specification {
  def "start game"() {
    when:
    Hangman hangman = new Hangman(start)

    then:
    hangman.tries() == tries
    hangman.used() == used
    hangman.length() == length
    hangman.problem() == problem

    where:
    start     | tries | used     | length | problem
    "APPLE"   | 12    | "AEIOU"  | 5      | "A___E"
    "GOOGLE"  | 12    | "AEIOU"  | 6      | "_OO__E"
    ""        | 12    | "AEIOU"  | 0      | ""
    "AEIOU"   | 12    | "AEIOU"  | 5      | "AEIOU"
  }

  def "play game"() {
    given:
    Hangman hangman = new Hangman("APPLE")

    when:
    Hangman newHangman = hangman.tryChar(ch as char)

    then:
    newHangman.tries() == tries
    newHangman.used() == used
    newHangman.problem() == problem

    where:
    ch  | tries | used      | problem
    'P' | 12    | "AEIOUP"  | "APP_E"
    'K' | 11    | "AEIOUK"  | "A___E"
  }

  def "final result"() {
    given:
    Hangman hangman = new Hangman("APPLE")

    when:
    tries.each { hangman = hangman.tryChar(it as char) }

    then:
    hangman.won() == won
    hangman.lost() == lost

    where:
    tries          | won   | lost
    "KKKKKKKKKKKK" | false | true
    "PL"           | true  | false
  }
}
