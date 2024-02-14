package com.jvkstackmvn.jvkstack.services;

import com.jvkstackmvn.jvkstack.domains.dtos.EntityADto;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface EntityAService {
    List<EntityADto> getAllEntities();

    void saveEntity(EntityADto entityA);

    EntityADto getEntity(UUID id);

    CompletableFuture<String> getTimezone();
}
