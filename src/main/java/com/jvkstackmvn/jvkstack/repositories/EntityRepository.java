package com.jvkstackmvn.jvkstack.repositories;

import com.jvkstackmvn.jvkstack.domains.entities.EntityA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntityRepository extends JpaRepository<EntityA, UUID> {
    Optional<EntityA> findById(UUID id);
}
