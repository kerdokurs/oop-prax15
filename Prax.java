package prax15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Prax {
  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      final String dirPath = args[0];

      final Path path = Path.of(dirPath);

      printFiles(path, file ->
              file.getFileName().toString().endsWith(".java")
      );

      printFiles(path, file ->
              file.getFileName().toString().endsWith(".class")
      );

    } else System.err.println("please provide a directory path");
  }

  public static void printFiles(final Path directory, final FileFilter filter) throws IOException {
    try (final Stream<Path> walk = Files.walk(directory)) {
      walk.filter(filter::accept)
              .map(Path::getFileName)
              .forEach(System.out::println);
    }
  }
}
