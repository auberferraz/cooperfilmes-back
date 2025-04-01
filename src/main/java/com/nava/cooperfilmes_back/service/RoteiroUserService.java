package com.nava.cooperfilmes_back.service;

import com.nava.cooperfilmes_back.domain.roteiro.Status;
import com.nava.cooperfilmes_back.dto.NextStatusRequestDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import com.nava.cooperfilmes_back.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoteiroUserService {

    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    StatusRepository statusRepository;

    public ResponseEntity<String> setNextStatus(NextStatusRequestDTO request){
        var roteiro = roteiroRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Objeto com ID " + request.id() + " não encontrado!"));

        var statusList = Status.Values.values();

        if ((roteiro.getStatus().getStatusId() == 7) || roteiro.getStatus().getStatusId() == 8) {
            return ResponseEntity.badRequest().build();
        }

        var nextId = roteiro.getStatus().getStatusId() + 1;
        Optional<Status> newStatus = statusRepository.findById(nextId);
        roteiro.setStatus(newStatus.get());

        roteiroRepository.save(roteiro);

        return ResponseEntity.ok().build();
    }

}
