package prax15;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class FileEditor {
  public static void main(String[] args) throws IOException {
    if (args.length > 2) {
      final Path input = Path.of(args[0]);
      final Path output = Path.of(args[1]);
      final String argument = args[2];

      final UnaryOperator<String> transformer = argument.equals("tabs") ?
          s -> s.replace("\t", " ".repeat(4)) :
          s -> s.replace(" ".repeat(4), "\t");

      edit(input, output, transformer);
    } else System.err.println("please provide input, output and tabs/spaces");
  }

  public static void edit(final Path input, final Path output, final UnaryOperator<String> transformer) throws IOException {
    try (final Stream<String> lines = Files.lines(input)) {
      lines
          .map(transformer)
          .map(l -> l + "\n")
          .forEach(l -> {
            try {
              Files.writeString(output, l, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (final IOException e) {
              throw new UncheckedIOException(e);
            }
          });
    }
  }
}
