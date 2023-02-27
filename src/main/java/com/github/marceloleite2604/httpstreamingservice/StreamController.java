package com.github.marceloleite2604.httpstreamingservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("stream")
public class StreamController {

    @GetMapping(value = "dates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LocalDateTime> date() {
        return Flux.interval(Duration.ofSeconds(1))
            .map(value -> LocalDateTime.now());
    }

    @GetMapping(value = "values", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> values() {
        return Flux.range(0, 4)
            .delayElements(Duration.ofMillis(200));
    }

    @GetMapping(value = "error", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Void> error() {
        return Mono.error(new IllegalStateException());
    }
}
