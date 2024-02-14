package com.jvkstackmvn.jvkstack.repositories;

import com.jvkstackmvn.jvkstack.domains.entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDB, String> {
}
