package com.jquispeg.cloudstreamexample;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class CloudstreamExampleApplication {

	Logger log = LoggerFactory.getLogger(CloudstreamExampleApplication.class);

	/**
	 * toUpperCase-in-0
	 * toUpperCase-out-0
	 * @return Function
	 */
	@Bean
	public Function<String, String> toUpperCase() {
		return String::toUpperCase;
	}

	/**
	 * producer-out-0
	 */
	@Bean
	public Supplier<Flux<Long>> producer() {
		return () -> Flux.interval(Duration.ofSeconds(1)).log();
	}

	/**
	 * processor-in-0
	 * processor-out-0
	 */
	@Bean
	public Function<Flux<Long>, Flux<Long>> processor() {
		return longFlux -> longFlux.map(value -> value * value);
	}

	/**
	 * consumer-in-0
	 */
	@Bean
	public Consumer<Long> consumer() {
		return (value) -> log.info("Message received {}", value);
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudstreamExampleApplication.class, args);
	}

}
