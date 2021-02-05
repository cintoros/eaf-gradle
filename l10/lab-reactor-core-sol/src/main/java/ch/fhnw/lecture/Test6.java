package ch.fhnw.lecture;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Test6 {

	public static void main(String[] args) throws Exception {
		Flux.just("one", "two", "three", "four")
			.flatMap(
					value -> Mono.just(value.toUpperCase())
								.subscribeOn(Schedulers.parallel())
								.log()
			)
			//.subscribe(value -> log.info("Consumed: " + value));
			.subscribe(value -> System.out.println("Consumed: " + value));

		Thread.sleep(1000);
	}

}
