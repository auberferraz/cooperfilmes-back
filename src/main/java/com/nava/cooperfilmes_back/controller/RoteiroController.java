package com.nava.cooperfilmes_back.controller;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.dto.RoteiroRequestDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roteiro")
public class RoteiroController {

    @Autowired
    RoteiroRepository roteiroRepository;

    @PostMapping(value = "/send-movie-script")
    public ResponseEntity<String> sendMovieScript(@RequestBody RoteiroRequestDTO request){
        Roteiro newRoteiro = new Roteiro(request);
        roteiroRepository.save(newRoteiro);
        return ResponseEntity.ok().build();
    }
}
