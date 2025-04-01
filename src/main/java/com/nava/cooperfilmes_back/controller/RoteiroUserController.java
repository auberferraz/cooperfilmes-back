package com.nava.cooperfilmes_back.controller;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.dto.NextStatusRequestDTO;
import com.nava.cooperfilmes_back.dto.RoteiroRequestDTO;
import com.nava.cooperfilmes_back.dto.RoteiroResponseDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import com.nava.cooperfilmes_back.repository.StatusRepository;
import com.nava.cooperfilmes_back.service.RoteiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/roteiro")
public class RoteiroUserController {

    @Autowired
    RoteiroUserService roteiroUserService;

    @PostMapping(value = "/set-next-status")
    public ResponseEntity<String> setNextStatus(@RequestBody NextStatusRequestDTO request){
        return roteiroUserService.setNextStatus(request);
    }

    @GetMapping
    public ResponseEntity consultMovieScript(@RequestParam Long id) {
        return roteiroUserService.consultMovieScript(id);
    }
}
