package ch.fhnw.lecture;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Test3 {

	public static void main(String[] args) throws Exception {
		Mono.just("Hello")
			.flatMap(s -> Mono.just(s + " world"))
			.subscribe(s -> System.out.println(s));
		
		System.out.println();
		Mono.just("Hello")
			.concatWith(Flux.just(",", "World", "!"))
			.subscribe(s -> System.out.println(s));
		
		System.out.println();
		Flux.interval(Duration.ofMillis(1L))
			.take(10)
			.map(l -> Long.toString(l))
			.subscribe(s -> System.out.println(s));

		Thread.sleep(1000);
	}

}
