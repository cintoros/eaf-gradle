package ch.fhnw.lecture.demo;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

public class ClientEcho2 {

	public static void main(String[] args) throws Exception {
		WebClient client = WebClient.create("http://localhost:8888");
		Flux<String> stringflux = 
				Flux.fromStream(Stream.iterate(0, i -> i + 1))
				.map(i -> "Hello " + i + "\r\n")
				.delayElements(Duration.ofSeconds(1)).take(10);

		client.post().uri("echo").contentType(MediaType.APPLICATION_STREAM_JSON).accept(MediaType.APPLICATION_STREAM_JSON).body(stringflux, String.class).retrieve().bodyToFlux(String.class).log().subscribe();
		
		System.in.read();
	}
}
