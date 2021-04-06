package prax15;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Handcraft {
  public static void main(String[] args) {
    final List<String> words = List.of("test", "tere", "ok");

    final List<String> mappedWords = map(words, s -> new StringBuilder(s).reverse().toString());
    final List<String> filteredWords = filter(mappedWords, s -> s.length() >= 3);

    filteredWords.forEach(System.out::println);
  }

  public static List<String> filter(final List<String> list, final Predicate<String> predicate) {
    return list
        .stream()
        .filter(predicate)
        .collect(Collectors.toList());
  }

  public static List<String> map(final List<String> list, final UnaryOperator<String> func) {
    return list
        .stream()
        .map(func)
        .collect(Collectors.toList());
  }
}
