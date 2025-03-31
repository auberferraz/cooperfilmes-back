package com.nava.cooperfilmes_back.repository;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoteiroRepository extends JpaRepository<Roteiro, Long> {
    List<Roteiro> findByEmail(String email);
}
