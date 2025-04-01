package com.nava.cooperfilmes_back.domain.roteiro;

public interface RoteiroStatusHandler {
    Roteiro handle(Roteiro roteiro);
    void setNext(RoteiroStatusHandler nextHandler);
}
