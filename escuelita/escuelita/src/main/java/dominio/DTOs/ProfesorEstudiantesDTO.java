/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.DTOs;

import java.util.List;

/**
 *
 * @author isaac
 */
public class ProfesorEstudiantesDTO {
    private String nombreProfesor;
    private List<String> estudiantes;

    public ProfesorEstudiantesDTO(String nombreProfesor, List<String> estudiantes) {
        this.nombreProfesor = nombreProfesor;
        this.estudiantes = estudiantes;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public List<String> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<String> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "ProfesorEstudiantesDTO{" + "nombreProfesor=" + nombreProfesor + ", estudiantes=" + estudiantes + '}';
    }
    
    
    
    
}
