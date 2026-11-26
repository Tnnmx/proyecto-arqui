package com.proyectoarqui.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;

/** Beneficiario de una póliza.
 *  Atributos confirmados con la ERS (glosario 3.2.5), NRM-AB
 *  y con la documentación técnica del Sistema Dueño (secciones 3.4 y 2.5):
 *  nombres, primer apellido, segundo apellido (opcional), fecha de
 *  nacimiento, porcentaje de beneficio y la póliza de la que es
 *  beneficiario. La suma de los porcentajes de una misma póliza es 100%. */
@Entity
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombres (ERS 3.2.5; NRM-AB pide el nombre completo). */
    private String nombres;

    /** Primer apellido (ERS 3.2.5; NRM-AB). */
    private String primerApellido;

    /** Segundo apellido (ERS 3.2.5). Opcional: el Sistema Dueño lo
     *  admite como nulo en sus rutas (descripcion-sistpolizas.pdf 2.5). */
    private String segundoApellido;

    /** Fecha de nacimiento (ERS 3.2.5; NRM-AB). */
    private LocalDate fechaNacimiento;

    /** Porcentaje de beneficio (ERS 3.2.5; NRM-AB). La suma de los
     *  porcentajes de todos los beneficiarios de una misma póliza
     *  siempre debe ser del 100%. */
    private Integer porcentajeBeneficio;

    @JsonBackReference
    @ManyToOne
    private Poliza poliza;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getPorcentajeBeneficio() {
        return porcentajeBeneficio;
    }

    public void setPorcentajeBeneficio(Integer porcentajeBeneficio) {
        this.porcentajeBeneficio = porcentajeBeneficio;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }
}
