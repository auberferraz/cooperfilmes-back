package com.nava.cooperfilmes_back.domain.roteiro;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;
    private String name;

    public enum Values {

        AGUARDANDO_ANALISE(1L),
        EM_ANALISE(2L),
        AGUARDANDO_REVISAO(3L),
        EM_REVISAO(4L),
        AGUARDANDO_APROVACAO(5L),
        EM_APROVACAO(6L),
        APROVADO(7L),
        RECUSADO(8L);


        long statusId;

        Values(long statusId) {
            this.statusId = statusId;
        }

        public long getRoleId() {
            return statusId;
        }
    }
}
