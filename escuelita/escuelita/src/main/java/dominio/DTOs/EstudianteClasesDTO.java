/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.DTOs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class EstudianteClasesDTO {

    private String nombreEstudiante;
    private List<String> nombresClases;

    public EstudianteClasesDTO(String nombreEstudiante, List<String> nombresClases) {
        this.nombreEstudiante = nombreEstudiante;
        this.nombresClases = nombresClases;
    }
    
    public EstudianteClasesDTO(String nombreEstudiante, String nombreClase) {
        this.nombreEstudiante = nombreEstudiante;
        nombresClases = new ArrayList<>();
        this.nombresClases.add(nombreClase);
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public List<String> getNombresClases() {
        return nombresClases;
    }

    public void setNombresClases(List<String> nombresClases) {
        this.nombresClases = nombresClases;
    }

    @Override
    public String toString() {
        return "EstudianteClasesDTO{"
                + "nombreEstudiante='" + nombreEstudiante + '\''
                + ", nombresClases=" + nombresClases
                + '}';
    }
}
