package com.proyectoarqui.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

/** Cliente / tomador.
 *  Atributos confirmados con la ERS (glosario 3.2.3), NRM-AC y NRM-MC,
 *  y con la documentación técnica del Sistema Dueño (secciones 3.2 y 2.5):
 *  nombres, primer apellido, segundo apellido (opcional), dirección,
 *  CURP (identificador del registro) y fecha de nacimiento. */
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombres (ERS 3.2.3; NRM-AC pide el nombre completo). */
    private String nombres;

    /** Primer apellido (ERS 3.2.3; NRM-AC). */
    private String primerApellido;

    /** Segundo apellido (ERS 3.2.3). Opcional: el Sistema Dueño lo
     *  admite como nulo en sus rutas (descripcion-sistpolizas.pdf 2.5). */
    private String segundoApellido;

    /** Dirección / domicilio (ERS 3.2.3; NRM-AC). */
    private String direccion;

    /** CURP. Identificador del registro: NRM-MC identifica al cliente
     *  por su CURP (ERS requisito 5) y no se modifica. */
    private String curp;

    /** Fecha de nacimiento (ERS 3.2.3; NRM-AC). */
    private LocalDate fechaNacimiento;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
