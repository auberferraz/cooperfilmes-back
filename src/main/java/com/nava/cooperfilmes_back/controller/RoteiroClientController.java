package com.nava.cooperfilmes_back.controller;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.dto.RoteiroRequestDTO;
import com.nava.cooperfilmes_back.dto.RoteiroResponseDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import com.nava.cooperfilmes_back.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/roteiro")
public class RoteiroClientController {

    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    StatusRepository statusRepository;

    @PostMapping(value = "/send-movie-script")
    public ResponseEntity<String> sendMovieScript(@RequestBody RoteiroRequestDTO request){
        var status = statusRepository.findByName("AGUARDANDO_ANALISE");
        Roteiro newRoteiro = new Roteiro(request, status);
        roteiroRepository.save(newRoteiro);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/consult-movie-script")
    public ResponseEntity consultMovieScript(@RequestParam String email){
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
