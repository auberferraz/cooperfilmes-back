package com.nava.cooperfilmes_back.service;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.domain.roteiro.RoteiroStatusHandler;
import com.nava.cooperfilmes_back.domain.roteiro.Status;
import com.nava.cooperfilmes_back.domain.roteiro.handlers.*;
import com.nava.cooperfilmes_back.domain.user.User;
import com.nava.cooperfilmes_back.dto.NextStatusRequestDTO;
import com.nava.cooperfilmes_back.dto.RoteiroResponseDTO;
import com.nava.cooperfilmes_back.repository.RoteiroRepository;
import com.nava.cooperfilmes_back.repository.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public ResponseEntity consultAllMovieScript() {

        var filter = applyFilterRoleForQuery();

        var roteiros = roteiroRepository.findAllFilter(filter);

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

    private List<String> applyFilterRoleForQuery() {
        var role = getRoleUserAuthenticate();

        List<String> filterList = new ArrayList<>();

        switch (role) {
            case "ANALISTA":
                filterList.add("AGUARDANDO_ANALISE");
                filterList.add("EM_ANALISE");
                break;
            case "REVISOR":
                filterList.add("AGUARDANDO_REVISAO");
                filterList.add("EM_REVISAO");
                break;
            case "APROVADOR":
                filterList.add("AGUARDANDO_APROVACAO");
                filterList.add("EM_APROVACAO");
                filterList.add("APROVADO");
                break;
            default:
                // code block
        }
        return filterList;
    }

    private String getRoleUserAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return user.getRole().getName();
        }

        throw new IllegalStateException("Erro ao buscar usuário autenticado.");
    }

}
