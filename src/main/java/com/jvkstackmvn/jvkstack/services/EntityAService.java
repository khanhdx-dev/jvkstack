package com.jvkstackmvn.jvkstack.services;

import com.jvkstackmvn.jvkstack.domains.dtos.EntityADto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface EntityAService {
    List<EntityADto> getAllEntities();

    void saveEntity(EntityADto entityA);

    EntityADto getEntity(UUID id);

    CompletableFuture<String> getTimezone();
}
