/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.DTOs;

/**
 *
 * @author maria
 */
public class EstudianteCantidadClasesDTO {

    private String nombreEstudiante;
    private int cantidadClases;

    public EstudianteCantidadClasesDTO(String nombreEstudiante, int cantidadClases) {
        this.nombreEstudiante = nombreEstudiante;
        this.cantidadClases = cantidadClases;
    }

    @Override
    public String toString() {
        return "EstudianteCantidadClasesDTO{"
                + "nombreEstudiante='" + nombreEstudiante + '\''
                + ", cantidadClases=" + cantidadClases
                + '}';
    }
}
