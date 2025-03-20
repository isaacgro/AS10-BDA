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

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public int getCantidadClases() {
        return cantidadClases;
    }

    public void setCantidadClases(int cantidadClases) {
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
