package ch.fhnw.lecture.ws;

import java.net.URI;
import java.time.Duration;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import reactor.core.publisher.Flux;

public class WebFluxClient {

	public static void main(String[] args) {
		WebSocketClient client = new ReactorNettyWebSocketClient();
		URI uri = URI.create("ws://localhost:8888/ws");

		Flux<String> stringflux = 
				Flux.interval(Duration.ofSeconds(1)).map(n -> "Hello " + n);

		client.execute(uri, webSocketSession ->
		    // send msg
		    webSocketSession.send(
		    		stringflux.map(txt -> webSocketSession.textMessage(txt))
		    ).and(
		            // receive message
		            webSocketSession.receive()
		                    .map(WebSocketMessage::getPayloadAsText)
		                    .doOnNext(System.out::println)
		    ).then()
		).block(Duration.ofSeconds(50));
	}

}