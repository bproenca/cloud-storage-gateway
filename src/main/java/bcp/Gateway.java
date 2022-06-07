package bcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Gateway {

    @Autowired
    private Config uriConfiguration;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(uriConfiguration.getHttpbin()))
                .route(p -> p
                        .host("*.circuitbreaker.com")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(uriConfiguration.getHttpbin()))
                .route(p -> p
                        .path("/s3/list")
                        .filters(f -> f.rewritePath("/s3/list", uriConfiguration.getPreauthreq()))
                        .uri(uriConfiguration.getEndpoint()))
                .route(p -> p
                        .path("/s3/download/*")
                        .filters(f -> f.rewritePath("/s3/download/(?<segment>.*)", uriConfiguration.getPreauthreq() + "${segment}"))
                        .uri(uriConfiguration.getEndpoint()))
                .route(p -> p
                        .path("/s3/upload/*")
                        .filters(f -> {
                            f.rewritePath("/s3/upload/(?<segment>.*)", uriConfiguration.getPreauthreq() + "${segment}");
                            f.addRequestHeader("opc-meta-integration-id", "A" + System.currentTimeMillis());
                            return f;
                        })
                        .uri(uriConfiguration.getEndpoint()))
                .route(p -> p
                        .path("/s3/metadata/*")
                        .filters(f -> f.rewritePath("/s3/metadata/(?<segment>.*)", uriConfiguration.getPreauthreq() + "${segment}"))
                        .uri(uriConfiguration.getEndpoint()))
                .build();
    }
}
