package com.proyectoarqui.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

/** Beneficiario de una póliza. */
@Entity
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO [confirmar con ERS]: atributos del beneficiario.
    @JsonBackReference
    @ManyToOne
    private Poliza poliza;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Poliza getPoliza() { return poliza; }
    public void setPoliza(Poliza poliza) { this.poliza = poliza; }
}
