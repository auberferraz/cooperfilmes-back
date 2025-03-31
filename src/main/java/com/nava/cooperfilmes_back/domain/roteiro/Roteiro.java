package com.nava.cooperfilmes_back.domain.roteiro;

import com.nava.cooperfilmes_back.dto.RoteiroRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_roteiros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roteiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String phoneNumber;

    private String movieScript;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    public Roteiro(RoteiroRequestDTO request, Status status) {
        this.email = request.email();
        this.name = request.name();
        this.phoneNumber = request.phoneNumber();
        this.movieScript = request.movieScript();
        this.status = status;
    }
}
