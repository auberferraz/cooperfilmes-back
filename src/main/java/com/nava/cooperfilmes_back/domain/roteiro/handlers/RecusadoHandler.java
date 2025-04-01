package com.nava.cooperfilmes_back.domain.roteiro.handlers;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.domain.roteiro.RoteiroStatusHandler;
import com.nava.cooperfilmes_back.domain.roteiro.Status;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class RecusadoHandler implements RoteiroStatusHandler {
    private RoteiroStatusHandler nextHandler;

    @Override
    public Roteiro handle(Roteiro roteiro) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            roteiro.setStatus(new Status(Status.Values.RECUSADO.getRoleId(), "RECUSADO"));
            return roteiro;
        }

        if (nextHandler != null) {
            return nextHandler.handle(roteiro);
        }

        throw new IllegalStateException("Status não tratado ou usuário sem permissão.");
    }

    @Override
    public void setNext(RoteiroStatusHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
