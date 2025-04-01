package com.nava.cooperfilmes_back.service;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.dto.RoteiroRequestDTO;
import com.nava.cooperfilmes_back.dto.RoteiroResponseDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import com.nava.cooperfilmes_back.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoteiroClientService {

    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    StatusRepository statusRepository;

    public ResponseEntity<String> sendMovieScript(RoteiroRequestDTO request){
        var status = statusRepository.findByName("AGUARDANDO_ANALISE");
        Roteiro newRoteiro = new Roteiro(request, status);
        roteiroRepository.save(newRoteiro);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity consultMovieScript(String email){
        var roteiros = roteiroRepository.findByEmail(email);
        var res = roteiros.stream()
                .map(roteiro -> new RoteiroResponseDTO(
                        roteiro.getId(),
                        roteiro.getEmail(),
                        roteiro.getName(),
                        roteiro.getPhoneNumber(),
                        roteiro.getMovieScript(),
                        roteiro.getStatus().getName()
                ));
        return ResponseEntity.ok(res);
    }

}
