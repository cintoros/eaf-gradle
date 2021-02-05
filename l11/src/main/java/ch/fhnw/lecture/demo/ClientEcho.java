package ch.fhnw.lecture.demo;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

public class ClientEcho {

	public static void main(String[] args) throws Exception {
		WebClient client = WebClient.create("http://localhost:8080");
		Flux<String> stringflux = Flux.just("one", "two", "three", "four").map(s -> s +  "\n");

		client.post().uri("echo").contentType(MediaType.APPLICATION_STREAM_JSON).accept(MediaType.TEXT_EVENT_STREAM).body(stringflux, String.class).retrieve().bodyToFlux(String.class).log().subscribe();
		
		System.in.read();
	}
}
