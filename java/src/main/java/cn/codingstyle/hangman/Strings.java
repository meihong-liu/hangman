package cn.codingstyle.hangman;

import java.util.function.Function;

public final class Strings {
  private Strings() {
  }

  public static boolean contains(String str, char c) {
    return str.contains(String.valueOf(c));
  }

  public static String map(String str, Function<Character, Character> f) {
    StringBuilder buf = new StringBuilder();
    for (char c : str.toCharArray()) {
      buf.append(f.apply(c));
    }
    return buf.toString();
  }
}
