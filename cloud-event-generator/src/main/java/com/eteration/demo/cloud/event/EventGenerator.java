package com.eteration.demo.cloud.event;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventBuilder;
import io.cloudevents.http.reactivex.vertx.VertxCloudEvents;
import io.cloudevents.json.Json;
import io.quarkus.scheduler.Scheduled;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.http.HttpClientRequest;

@ApplicationScoped
public class EventGenerator {

	@Inject
	Vertx vertx;

	private AtomicInteger counter = new AtomicInteger();

	@Inject
	@ConfigProperty(name = "SINK", defaultValue = "http://default-broker.kn.svc.cluster.localllll")
	protected String sinkUrl;

	public int get() {
		return counter.get();
	}

	@SuppressWarnings("deprecation")
	@Scheduled(every = "10s")
	void sendHeartBeat() throws URISyntaxException, JsonProcessingException, MalformedURLException {
		info("Sending Event");
		
		CustomEvent eventData = new CustomEvent("Hellooooo" + counter.incrementAndGet());
		Jsonb jsonb = JsonbBuilder.create();
		String eventDataJson = jsonb.toJson(eventData);

		info("event data => "+eventDataJson);
		URL sink = new  URL(sinkUrl);
		final HttpClientRequest request = vertx.createHttpClient().post(80,sink.getHost(),"/");

		request.handler(event -> {
			info("Response => " + event.statusCode());
			info("ResponseMessage => " + event.statusMessage());

		});

		final CloudEvent<String> event = new CloudEventBuilder<String>().type("dev.knative.eventing.samples.heartbeat")
				.source(new URI("dev.knative.example")).id(UUID.randomUUID().toString()).data(eventDataJson).build();

		VertxCloudEvents.create().writeToHttpClientRequest(event, request);
		request.end();

	}

	private void info(String msg) {
		System.out.println(msg);
	}

}