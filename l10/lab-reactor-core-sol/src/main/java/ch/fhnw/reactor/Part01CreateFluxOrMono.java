package ch.fhnw.reactor;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn about creating Mono or Flux publishers.
 * <p>
 * Mono: https://raw.githubusercontent.com/reactor/reactor-core/v3.1.3.RELEASE/src/docs/marble/mono.png
 * <p>
 * Flux: https://raw.githubusercontent.com/reactor/reactor-core/v3.1.3.RELEASE/src/docs/marble/flux.png
 */
public class Part01CreateFluxOrMono {

    /**
     * TODO 01.01
     * <p>
     * Create a sequence that emits a single value from the provided Optional<String> parameter.
     */
    Publisher<String> createFromOptional(Optional<String> foo) {
        return Mono.justOrEmpty(foo);
    }

    /**
     * TODO 01.02
     * <p>
     * Create a sequence that emits a single value from the provided potentially null value.
     */
    Publisher<String> createFromPotentiallyNull(String foo) {
        return Mono.justOrEmpty(foo);
    }

    /**
     * TODO 01.03
     * <p>
     * Create a sequence that emits a single value from the provided lazily captured value.
     */
    Publisher<String> createFromSupplier(Supplier<String> foo) {
        return Mono.fromSupplier(foo);
    }

    /**
     * TODO 01.04
     * <p>
     * Create a sequence producing its value using the provided CompletableFuture parameter.
     */
    Publisher<String> createFromFuture(CompletableFuture<String> future) {
        return Mono.fromFuture(future);
    }

    /**
     * TODO 01.05
     * <p>
     * Create a sequence producing its value using the provided Callable.
     */
    Publisher<String> createFromCallable(Callable<String> callable) {
        return Mono.fromCallable(callable);
    }

    /**
     * TODO 01.06
     * <p>
     * Create a sequence that emits "foo" and "bar" strings.
     */
    Publisher<String> createFooBar() {
        return Flux.just("foo", "bar");
    }

    /**
     * TODO 01.07
     * <p>
     * Create a sequence from the provided array.
     */
    Publisher<String> createFromArray(String[] array) {
        return Flux.fromArray(array);
    }

    /**
     * TODO 01.08
     * <p>
     * Create a sequence from the provided list.
     */
    Publisher<String> createFromList(List<String> values) {
        return Flux.fromIterable(values);
    }

    /**
     * TODO 01.09
     * <p>
     * Create a sequence that emits the items contained in the provided stream.
     */
    Publisher<String> createFromStream(Stream<String> stream) {
        return Flux.fromStream(stream);
    }

    /**
     * TODO 01.10
     * <p>
     * Create a sequence that completes without emitting any item.
     */
    Publisher<Void> createEmpty() {
        return Mono.empty(); // or Flux.empty()
    }

    /**
     * TODO 01.11
     * <p>
     * Create a sequence that terminates with {@link IllegalStateException} error immediately after being subscribed to.
     */
    Publisher<Void> createError() {
        return Mono.error(new IllegalStateException()); // or Flux.error(new IllegalStateException())
    }

    /**
     * TODO 01.12
     * <p>
     * Create a sequence that will never signal any data, error or completion signal.
     */
    Publisher<Void> neverEmit() {
        return Mono.never(); // or Flux.never();
    }

    /**
     * TODO 01.13
     * <p>
     * Lazily supply the provided publisher every time a subscription is made on the resulting Flux.
     * Hint: Flux.defer
     */
    Flux<Integer> lazy(Supplier<Publisher<Integer>> supplier) {
        return Flux.defer(supplier);
    }

    /**
     * TODO 01.14
     * <p>
     * Create a sequence that emits increasing values from 0 to 5 each 100ms.
     * Hint: Flux.interval
     */
    Publisher<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(5);
    }

    /**
     * TODO 01.15
     * <p>
     * Create a sequence that will emit integers from 5 till 10 inclusive.
     * Hint: Flux.range
     */
    Publisher<Integer> fromRange() {
        return Flux.range(5, 6);
    }

}
