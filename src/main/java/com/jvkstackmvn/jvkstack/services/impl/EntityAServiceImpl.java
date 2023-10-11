package com.jvkstackmvn.jvkstack.services.impl;

import com.jvkstackmvn.jvkstack.domains.dtos.EntityADto;
import com.jvkstackmvn.jvkstack.domains.entities.EntityA;
import com.jvkstackmvn.jvkstack.repositories.EntityRepository;
import com.jvkstackmvn.jvkstack.services.EntityAService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntityAServiceImpl implements EntityAService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ModelMapper mapper = new ModelMapper();

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
}
