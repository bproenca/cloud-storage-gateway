package bcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        logger.info("Fallback method");
        return Mono.just("fallback");
    }

    @GetMapping("hello/{name}")
    private Mono<String> hello(@PathVariable String name) {
        return Mono.just("Hello " + name);
    }
}
