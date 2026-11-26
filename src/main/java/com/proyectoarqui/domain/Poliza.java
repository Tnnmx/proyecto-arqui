package com.proyectoarqui.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Póliza. Tipo verificado: 0 Auto, 1 Vida, 2 Médico.
 *  Atributos confirmados con la ERS (glosario 3.2.4), NRM-AP y NRM-MP,
 *  y con la documentación técnica del Sistema Dueño (secciones 3.3 y 2.5):
 *  clave única de registro, tipo, monto asegurado, descripción del seguro
 *  y cliente asegurado titular. */
@Entity
public class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 0 = Auto, 1 = Vida, 2 = Médico. */
    private int tipo;

    /** Clave única de registro de la póliza (ERS 3.2.4; NRM-AP la pide en
     *  el alta; NRM-MP la usa como identificador y no se modifica). */
    private String clave;

    /** Monto asegurado (ERS 3.2.4; NRM-AP). */
    private BigDecimal montoAsegurado;

    /** Descripción del seguro (ERS 3.2.4; NRM-AP). */
    private String descripcion;

    /** Cliente asegurado titular de la póliza (ERS 3.2.4; NRM-AP pide
     *  su CURP para el alta). */
    @ManyToOne
    private Cliente cliente;

    @JsonManagedReference
    @OneToMany(mappedBy = "poliza")
    private List<Beneficiario> beneficiarios = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public BigDecimal getMontoAsegurado() {
        return montoAsegurado;
    }

    public void setMontoAsegurado(BigDecimal montoAsegurado) {
        this.montoAsegurado = montoAsegurado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }
}
