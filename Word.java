package prax15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Word {
  public static void main(String[] args) throws IOException {
    final Map<String, Integer> map;

    final Path path = Path.of("ipsum.txt");

    try (final Stream<String> lines = Files.lines(path)) {
      map = lines
          .flatMap(s -> Arrays.stream(s
              .toLowerCase()
              .replaceAll("[.,?!:\\- ]", " ")
              .split(" "))
          )
          .filter(s -> !s.isEmpty())
          .collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
    }

    map.forEach((key, val) -> System.out.printf("%s: %d%n", key, val));
  }
}
