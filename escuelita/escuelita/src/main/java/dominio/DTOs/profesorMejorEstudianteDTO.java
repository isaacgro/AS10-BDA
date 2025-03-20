/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.DTOs;

import exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import persistencia.conexion.Conexion;

/**
 *
 * @author isaac
 */
public class profesorMejorEstudianteDTO {
    
    private String nombreClase;
    private String nombreProfe;
    private double promedioMasAlto;
    private String nombreEstudiante;

    public profesorMejorEstudianteDTO(String nombreClase, String nombreProfe, double promedioMasAlto, String nombreEstudiante) {
        this.nombreClase = nombreClase;
        this.nombreProfe = nombreProfe;
        this.promedioMasAlto = promedioMasAlto;
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getNombreProfe() {
        return nombreProfe;
    }

    public void setNombreProfe(String nombreProfe) {
        this.nombreProfe = nombreProfe;
    }

    public double getPromedioMasAlto() {
        return promedioMasAlto;
    }

    public void setPromedioMasAlto(double promedioMasAlto) {
        this.promedioMasAlto = promedioMasAlto;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    @Override
    public String toString() {
        return "profesorMejorEstudianteDTO{" + "nombreClase=" + nombreClase + ", nombreProfe=" + nombreProfe + ", promedioMasAlto=" + promedioMasAlto + ", nombreEstudiante=" + nombreEstudiante + '}';
    }
    
    public profesorMejorEstudianteDTO obtenerMejorEstudiante(Long idClase) throws PersistenciaException {
        
        EntityManager em = Conexion.crearConexion();
        try {
            List<Object[] > clases = em.createQuery("SELECT DISTINCT c.nombre, p.nombre," 
            + "e.promedio, e.nombre FROM Clase c JOIN c.profesor p JOIN c.estudiantes e "
            + "WHERE c.id = :idClase AND e.promedio = (SELECT MAX (e2.promedio) "
            + "FROM Estudiante e2 JOIN e2.clases c2 WHERE c2.id = :idClase", Object[].class)
                    .setParameter("idClase", idClase)
                    .getResultList();
            
            if (clases.isEmpty()) {
                throw new PersistenciaException("Clase no encontrada o sin alumnos");
            }
            
            Object[] fila = clases.get(0);
            return new profesorMejorEstudianteDTO(
            (String) fila[0],
            (String) fila[1],
            (Double) fila[2], 
            (String) fila[3]
            );
        }catch (Exception e) {
            throw new PersistenciaException("Error al obtener datos de la clase" + e.getMessage());
         } finally {
            em.close();
        }
    }
    
}
