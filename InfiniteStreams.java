package prax15;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TASK 8.
 * <p>
 * Your task is to implement some infinite streams.
 * These are relatively obscure and impractical but great brain teasers.
 */
public class InfiniteStreams {
  /**
   * Returns an infinite stream of all Fibonacci numbers.
   */
  private static IntStream fibonacci() {
    return Stream
        .iterate(new Fib(0, 1), f -> new Fib(f.b, f.a + f.b))
        .mapToInt(Fib::a);
  }

  /**
   * Returns an infinite stream of random integers (1…100).
   */
  private static IntStream randomInts() {
    return IntStream
        .generate(() -> ThreadLocalRandom.current().nextInt(1, 101));
  }

  /**
   * Returns an infinite stream of all positive integers.
   */
  private static IntStream positiveInts() {
//    final AtomicInteger i = new AtomicInteger(1);
//    return Stream.generate(i::getAndIncrement);
    return IntStream.iterate(0, s -> s + 1);
  }

  /**
   * Returns an infinite stream of squares of all positive integers.
   */
  private static IntStream positiveIntSquares() {
    return positiveInts().map(i -> i * i);
  }

  /**
   * Returns an infinite stream of all prime numbers.
   */
  private static IntStream primes() {
    return positiveInts()
        .parallel()
        .filter(i ->
            BigInteger.valueOf(i).isProbablePrime(100)
        );
  }

  public static void main(String[] args) {
    outputInfinite("Random integers", InfiniteStreams::randomInts);
    outputInfinite("Positive integers", InfiniteStreams::positiveInts);
    outputInfinite("Positive integer squares", InfiniteStreams::positiveIntSquares);
    outputInfinite("Primes", InfiniteStreams::primes);
    outputInfinite("Fibonacci", InfiniteStreams::fibonacci);

    // TODO: Refactor this class to use the more efficient IntStream instead of Stream<Integer>.
  }

  private static void outputInfinite(String prompt, Supplier<IntStream> streamSupplier) {
    final IntStream stream = streamSupplier.get();
    final String elemStr = stream
        .limit(100)
        .mapToObj(Integer::toString)
        .collect(Collectors.joining(", "));

    System.out.println(prompt + ":");
    System.out.println(elemStr + ", ...");
    System.out.println();
  }

  static record Fib(int a, int b) {
  }
}
