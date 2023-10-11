package com.jvkstackmvn.jvkstack.repositories;

import com.jvkstackmvn.jvkstack.domains.entities.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SystemLogRepository extends JpaRepository<SystemLog, UUID> {
}
