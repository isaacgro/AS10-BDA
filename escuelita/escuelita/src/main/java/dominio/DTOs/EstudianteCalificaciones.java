/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.DTOs;

/**
 *
 * @author Ramon Valencia
 */
public class EstudianteCalificaciones {
    
    private String nombreEstudiante;
    private Double promedio;

    public EstudianteCalificaciones(String nombreEstudiante, Double promedio) {
        this.nombreEstudiante = nombreEstudiante;
        this.promedio = promedio;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "EstudianteCalificaciones{" + 
                "nombreEstudiante=" + nombreEstudiante + 
                ", promedio=" + promedio + 
                '}';
    }
    
    
}
