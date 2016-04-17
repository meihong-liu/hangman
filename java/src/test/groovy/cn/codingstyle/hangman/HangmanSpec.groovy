package cn.codingstyle.hangman

import spock.lang.Specification

class HangmanSpec extends Specification {
  def "at initial"() {
    when:
    Hangman hangman = new Hangman(start)

    then:
    hangman.length() == length
    hangman.problem() == problem

    where:
    start    | length | problem
    "APPLE"  | 5      | "A___E"
    "GOOGLE" | 6      | "_OO__E"
    ""       | 0      | ""
    "AEIOU"  | 5      | "AEIOU"
  }

  def "hangman game"() {
    given:
    Hangman hangman = new Hangman("APPLE")

    when:
    chars.each { hangman = hangman.tryChar(it as char) }

    then:
    hangman.tries() == tries
    hangman.used() == used
    hangman.problem() == problem

    hangman.won() == won
    hangman.lost() == lost

    where:
    chars          | tries | used      | problem   | won   | lost
    "A"            | 11    | "AEIOU"   | "A___E"   | false | false
    "AA"           | 10    | "AEIOU"   | "A___E"   | false | false
    "P"            | 12    | "AEIOUP"  | "APP_E"   | false | false
    "PP"           | 11    | "AEIOUP"  | "APP_E"   | false | false
    "K"            | 11    | "AEIOUK"  | "A___E"   | false | false
    "KK"           | 10    | "AEIOUK"  | "A___E"   | false | false
    "PL"           | 12    | "AEIOUPL" | "APPLE"   | true  | false
    "KKKKKKKKKKKK" | 0     | "AEIOUK"  | "A___E"   | false | true
  }
}