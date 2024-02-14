package com.jvkstackmvn.jvkstack.controllers;

import com.jvkstackmvn.jvkstack.domains.dtos.EntityADto;
import com.jvkstackmvn.jvkstack.domains.entities.EntityA;
import com.jvkstackmvn.jvkstack.services.EntityAService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
//import io.github.resilience4j.retry.annotation.Retry;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/entities")
public class EntityController {
    @Autowired
    private EntityAService es;

    @GetMapping("/")
    public ResponseEntity<List<EntityADto>> getAllEntities(){
        List<EntityADto> result = es.getAllEntities();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityADto> getEntityById(@PathVariable UUID id){
        EntityADto result = es.getEntity(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveEntity(@RequestBody EntityADto e){
        es.saveEntity(e);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/timezone")
    public ResponseEntity<?> getTimeZone(){
        try {
            return new ResponseEntity<>(es.getTimezone().get(), HttpStatus.OK);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/circuit-breaker")
//    @CircuitBreaker(name = "CircuitBreakerService")
    public String circuitBreakerApi() {
        return "circuit breaker";
    }

//    @GetMapping("/retry")
//    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
//    public ResponseEntity<List<EntityA>> retryApi() {
//        return getAllEntities();
//    }

//    @GetMapping("/rate-limiter")
//    @RateLimiter(name = "rateLimiterApi")
//    public ResponseEntity<List<EntityA>> rateLimitApi() {
//        return getAllEntities();
//    }
}
