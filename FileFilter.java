package prax15;

import java.nio.file.Path;

@FunctionalInterface
public interface FileFilter {
  boolean accept(final Path path);
}
