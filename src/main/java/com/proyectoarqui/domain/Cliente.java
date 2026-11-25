package com.proyectoarqui.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** Cliente / tomador. */
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO [confirmar con ERS]: atributos del cliente / tomador,
    // y su relación con Poliza (tomador/asegurado).
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
