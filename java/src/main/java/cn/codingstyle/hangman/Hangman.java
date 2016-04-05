package cn.codingstyle.hangman;

import static cn.codingstyle.hangman.Strings.contains;
import static cn.codingstyle.hangman.Strings.map;

public class Hangman {
  private String solution;
  private String used = "AEIOU";
  private int tries = 12;

  public Hangman(String solution) {
    this.solution = solution;
  }

  public Hangman tryChar(char ch) {
    return new Hangman(solution, newUsed(ch), newTries(ch));
  }

  private Hangman(String solution, String used, int tries) {
    this.solution = solution;
    this.used = used;
    this.tries = tries;
  }

  private String newUsed(char ch) {
    return used + ch;
  }

  private int newTries(char ch) {
    return contains(solution, ch) ? tries : tries - 1;
  }

  public String used() {
    return used;
  }

  public String problem() {
    return map(solution, ch -> contains(used, ch) ? ch : '_');
  }

  public int tries() {
    return tries;
  }

  public int length() {
    return solution.length();
  }

  public boolean won() {
    return problem().equals(solution);
  }

  public boolean lost() {
    return tries == 0;
  }
}
