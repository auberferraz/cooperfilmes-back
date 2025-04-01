package com.nava.cooperfilmes_back.domain.roteiro.handlers;

import com.nava.cooperfilmes_back.domain.roteiro.Roteiro;
import com.nava.cooperfilmes_back.domain.roteiro.RoteiroStatusHandler;
import com.nava.cooperfilmes_back.domain.roteiro.Status;
import com.nava.cooperfilmes_back.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class EmAnaliseHandler implements RoteiroStatusHandler {
    private RoteiroStatusHandler nextHandler;

    @Override
    public Roteiro handle(Roteiro roteiro) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            boolean isRoleValid = user.getRole().getName().equals("ANALISTA");
            boolean isStatusValid = (roteiro.getStatus().getStatusId() == Status.Values.EM_ANALISE.getRoleId());

            if (isRoleValid && isStatusValid) {
                roteiro.setStatus(new Status(Status.Values.AGUARDANDO_REVISAO.getRoleId(), "AGUARDANDO_REVISAO"));
                return roteiro;
            }
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
