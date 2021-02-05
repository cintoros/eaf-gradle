package ch.fhnw.lecture;

import java.util.concurrent.atomic.AtomicLong;

import reactor.core.publisher.Flux;

public class Test7 {

	public static void main(String[] args) {
		Flux<Long> squares = Flux.generate(
			() -> new AtomicLong(),
			(state, sink) -> {
				long i = state.getAndIncrement();
				sink.next(i * i);
				if(i == 10) sink.complete();
				return state;
			}
		);
		squares.log().subscribe();
		
		Flux<Integer> numbers = Flux.push(sink -> sink.next(1).next(2).next(3).complete());
		numbers.log().subscribe();
	}
}
