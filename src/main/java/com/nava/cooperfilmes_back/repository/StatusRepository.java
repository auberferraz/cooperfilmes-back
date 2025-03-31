package com.nava.cooperfilmes_back.repository;

import com.nava.cooperfilmes_back.domain.roteiro.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);
}
