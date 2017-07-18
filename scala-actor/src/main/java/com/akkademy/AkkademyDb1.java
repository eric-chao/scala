package com.akkademy;

import java.util.HashMap;
import java.util.Map;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

import com.akkademy.messages.SetRequest;

public class AkkademyDb1 extends AbstractActor {

	protected final LoggingAdapter log = Logging.getLogger(context().system(),this);
	protected final Map<String, Object> map = new HashMap<>();

	private AkkademyDb1() {
		receive(ReceiveBuilder.match(SetRequest.class,
						message -> {
							log.info("Received set request -key: {} value: {}", message.getKey(), message.getValue());
							map.put(message.getKey(), message.getValue());
						})
				.matchAny(o -> log.info("received unknown message {}", o))
				.build());
	}
}
