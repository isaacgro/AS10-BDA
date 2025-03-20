/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.entidades;

import dominio.enums.Estatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author maria
 */
@Entity
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "promedio")
    private Double promedio;

    @Column(name = "estatus_estudiante", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Estatus estatus;

    // por defecto JPA usa EAGER para la relación @OneToOne, pero en relaciones como @OneToMany y @ManyToMany, el tipo que utiliza por defecto es LAZY
    @OneToOne(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) // unidireccional o bidireccional?
    @JoinColumn(name = "id_direccion", nullable = true)
    private Direccion direccion;

    // para probar M:N
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "estudiante_inscrito_clase",
            joinColumns = @JoinColumn(name = "id_estudiante"),
            inverseJoinColumns = @JoinColumn(name = "id_clase")
    )
    private List<Clase> clases = new ArrayList<>();

    public Estudiante() {
    }

    public Estudiante(String nombre, Double promedio, Estatus estatus, Direccion direccion) {
        this.nombre = nombre;
        this.promedio = promedio;
        this.estatus = estatus;
        this.direccion = direccion;
    }

    public Estudiante(Long id, String nombre, Double promedio, Estatus estatus, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.promedio = promedio;
        this.estatus = estatus;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ", nombre=" + nombre + ", promedio=" + promedio + ", estatus=" + estatus + ", direcciones=" + direccion + '}';
    }

}
