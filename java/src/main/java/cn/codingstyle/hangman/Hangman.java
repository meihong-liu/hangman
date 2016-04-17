package cn.codingstyle.hangman;

import static java.util.stream.Collectors.joining;

public final class Hangman {
  private static final String ALL_VOWELS = "AEIOU";
  private static final int MAX_TRIES = 12;

  private String solution;
  private String used = ALL_VOWELS;
  private int tries = MAX_TRIES;

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
    return !isCharUsed(ch) ? used + ch : used;
  }

  private int newTries(char ch) {
    return tryFailed(ch) ? tries - 1 : tries;
  }

  private boolean tryFailed(char ch) {
    return isCharUsed(ch) || !isCharContained(ch);
  }

  private boolean isCharUsed(char ch) {
    return used.indexOf(ch) != -1;
  }

  private boolean isCharContained(char ch) {
    return solution.indexOf(ch) != -1;
  }

  public String used() {
    return used;
  }

  public String problem() {
    return solution.chars()
      .mapToObj(ch -> mask((char)ch))
      .collect(joining());
  }

  private String mask(char ch) {
    return isCharUsed(ch) ? String.valueOf(ch) : "_";
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