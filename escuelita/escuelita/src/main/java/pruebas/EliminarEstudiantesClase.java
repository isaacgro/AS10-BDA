/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dominio.entidades.Clase;
import dominio.entidades.Estudiante;
import exception.PersistenciaException;
import persistencia.DAOs.ClaseDAO;
import persistencia.DAOs.EstudianteDAO;

/**
 *
 * @author maria
 */
public class EliminarEstudiantesClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = EstudianteDAO.getInstanceDAO();
        ClaseDAO claseDAO = ClaseDAO.getInstanceDAO();

        try {
            System.out.println("--- Eliminar estudiante de una clase ---");

            // Elegimos un estudiante y una clase de la que lo eliminaremos
            Estudiante estudianteAEliminar = estudianteDAO.buscarPorId(4L);// David Williams se supone
            Clase clase1 = claseDAO.buscarPorId(1L);// Progra II se supone
        
            boolean eliminado = estudianteDAO.eliminarDeClase(estudianteAEliminar.getId(), clase1.getId());

            if (eliminado) {
                System.out.println("Estudiante eliminado correctamente de la clase.");
            } else {
                System.out.println("No se pudo eliminar al estudiante de la clase.");
            }

            // Verificar nuevamente los estudiantes en cada clase después de la eliminación
            System.out.println(" ");
            System.out.println("--- Listado de estudiantes por clase después de la eliminación ---");

            System.out.println("Clase: " + clase1.getNombre());
            for (Estudiante e : claseDAO.obtenerEstudiantesPorClase(clase1.getId())) {
                System.out.println(" - " + e.getNombre());
            }

            

        } catch (PersistenciaException e) {
            System.err.println("Error al eliminar estudiante de la clase: " + e.getMessage());
        }
    }

}
