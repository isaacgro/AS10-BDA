/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.DTOs;

/**
 *
 * @author Ramon Valencia
 */
public class ProfesorCantidadClasesDTO {
    
    private String nombreProfesor;
    private int cantidadClases;

    public ProfesorCantidadClasesDTO(String nombreProfesor, int cantidadClases) {
        this.nombreProfesor = nombreProfesor;
        this.cantidadClases = cantidadClases;
    }

    @Override
    public String toString() {
        return "ProfesorCantidadClasesDTO{" + 
                "nombreProfesor=" + nombreProfesor + 
                ", cantidadClases=" + cantidadClases + 
                '}';
    }
    
    
}
