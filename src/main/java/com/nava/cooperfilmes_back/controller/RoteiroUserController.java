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
@RequestMapping("/user/roteiro")
public class RoteiroUserController {

    @Autowired
    RoteiroRepository roteiroRepository;

    @Autowired
    StatusRepository statusRepository;

//    @PostMapping(value = "/send-movie-script")
//    public ResponseEntity<String> sendMovieScript(@RequestBody RoteiroRequestDTO request){
//        var status = statusRepository.findByName("AGUARDANDO_ANALISE");
//        Roteiro newRoteiro = new Roteiro(request, status);
//        roteiroRepository.save(newRoteiro);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping
    public ResponseEntity consultMovieScript(@RequestParam Long id) {
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
