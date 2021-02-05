package ch.fhnw.lecture;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class Test5 {

	public static void main(String[] args) throws Exception {
		Flux.just("one", "two", "three", "four")
			.log()
			.map(String::toUpperCase)
			.subscribeOn(Schedulers.parallel());
			//.subscribe(value -> log.info("Consumed: " + value));
		
		Thread.sleep(1000);
	}

}
