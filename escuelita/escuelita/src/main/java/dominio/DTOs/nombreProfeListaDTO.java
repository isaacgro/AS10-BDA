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
public class nombreProfeListaDTO {
    
    private String nombreProfesor;
    private List<String> clases;

    public nombreProfeListaDTO() {
    }
    
    

    public nombreProfeListaDTO(String nombreProfesor, List<String> clases) {
        this.nombreProfesor = nombreProfesor;
        this.clases = clases;
    }
    
    
    

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public List<String> getClases() {
        return clases;
    }

    public void setClases(List<String> clases) {
        this.clases = clases;
    }

    @Override
    public String toString() {
        return "nombreProfeListaDTO{" + "nombreProfesor=" + nombreProfesor + ", clases=" + clases + '}';
    }
    
    
    
  
}
