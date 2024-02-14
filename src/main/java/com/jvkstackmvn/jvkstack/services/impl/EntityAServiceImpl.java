package com.jvkstackmvn.jvkstack.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvkstackmvn.jvkstack.domains.dtos.EntityADto;
import com.jvkstackmvn.jvkstack.domains.entities.EntityA;
import com.jvkstackmvn.jvkstack.repositories.EntityRepository;
import com.jvkstackmvn.jvkstack.services.EntityAService;
import com.jvkstackmvn.jvkstack.services.external.TimezoneApi;
import org.json.JSONArray;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class EntityAServiceImpl implements EntityAService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ModelMapper mapper = new ModelMapper();

    @Autowired
    private TimezoneApi timezoneApi;

    @Autowired
    private EntityRepository er;

    public List<EntityADto> getAllEntities(){
        logger.info("### Getting All Entities");
        return er.findAll().stream()
                .map(item -> mapper.map(item, EntityADto.class))
                .collect(Collectors.toList());
    }

    public void saveEntity(EntityADto entityA) {
        EntityA e = mapper.map(entityA,EntityA.class);
        er.save(e);
    }

    public EntityADto getEntity(UUID id) {
        Optional<EntityA> e = er.findById(id);
        return mapper.map(e.get(), EntityADto.class);
    }

    @Async
    @Override
    public CompletableFuture<String> getTimezone() {
        Map<String,Object> asiaTimezone = null;
        Map<String,Object> europeTimezone = null;
        try {
            asiaTimezone = CompletableFuture.completedFuture(getAsiaTimezone()).get();
            europeTimezone = CompletableFuture.completedFuture(getEuropeTimezone()).get();
            return CompletableFuture.completedFuture(new JSONArray(List.of(asiaTimezone,europeTimezone)).toString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String,Object> getEuropeTimezone() {
        logger.info("### Europe Timezone Executed by user: {}", Thread.currentThread().getName());
        Map<String,Object> europeTimezone = convertResultToMap(timezoneApi.getTimezone("Europe/Berlin"));
        return europeTimezone;
    }

    public Map<String,Object> getAsiaTimezone() {
        logger.info("### Asia Timezone Executed by user: {}", Thread.currentThread().getName());
        Map<String,Object> asiaTimezone = convertResultToMap(timezoneApi.getTimezone("Asia/Bangkok"));
        return asiaTimezone;
    }

    private Map<String, Object> convertResultToMap(String input){
        try {
            HashMap<String, Object> map = new ObjectMapper().readValue(input, HashMap.class);
            return map;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
