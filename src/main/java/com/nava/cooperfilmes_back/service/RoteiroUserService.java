package com.nava.cooperfilmes_back.service;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.domain.roteiro.RoteiroStatusHandler;
import com.nava.cooperfilmes_back.domain.roteiro.Status;
import com.nava.cooperfilmes_back.domain.roteiro.handlers.*;
import com.nava.cooperfilmes_back.dto.NextStatusRequestDTO;
import com.nava.cooperfilmes_back.dto.RoteiroResponseDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import com.nava.cooperfilmes_back.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class RoteiroUserService {

    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    StatusRepository statusRepository;

    public ResponseEntity<String> setNextStatus(NextStatusRequestDTO request) {
        var roteiro = roteiroRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Objeto com ID " + request.id() + " não encontrado!"));


        RoteiroStatusHandler aguardandoAnaliseHandler = new AguardandoAnaliseHandler();
        RoteiroStatusHandler emAnaliseHandler = new EmAnaliseHandler();
        RoteiroStatusHandler aguardandoRevisaoHandler = new AguardandoRevisaoHandler();
        RoteiroStatusHandler emRevisaoHandler = new EmRevisaoHandler();
        RoteiroStatusHandler aguardandoAprovacaoHandler = new AguardandoAprovacaoHandler();
        RoteiroStatusHandler emAprovacaoHandler = new EmAprovacaoHandler();
        RoteiroStatusHandler aprovadoHandler = new AprovadoHandler();

        aguardandoAnaliseHandler.setNext(emAnaliseHandler);
        emAnaliseHandler.setNext(aguardandoRevisaoHandler);
        aguardandoRevisaoHandler.setNext(emRevisaoHandler);
        emRevisaoHandler.setNext(aguardandoAprovacaoHandler);
        aguardandoAprovacaoHandler.setNext(emAprovacaoHandler);
        emAprovacaoHandler.setNext(aprovadoHandler);

        Roteiro rote = aguardandoAnaliseHandler.handle(roteiro);

        roteiroRepository.save(rote);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity consultMovieScript(Long id) {
        var roteiro = roteiroRepository.findById(id);
        return ResponseEntity.ok(roteiro.map(r -> new RoteiroResponseDTO(
                                r.getId(),
                                r.getEmail(),
                                r.getName(),
                                r.getPhoneNumber(),
                                r.getMovieScript(),
                                r.getStatus().getName()
                        )
                )
        );

    }

}
