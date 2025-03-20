/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dominio.DTOs.EstudianteCantidadClasesDTO;
import dominio.DTOs.EstudianteClasesDTO;
import dominio.DTOs.ProfesorCantidadClasesDTO;
import dominio.DTOs.ProfesorEstudiantesDTO;
import dominio.DTOs.profesorMejorEstudianteDTO;
import dominio.entidades.Profesor;
import exception.PersistenciaException;
import java.util.List;
import persistencia.DAOs.EstudianteDAO;
import persistencia.DAOs.ProfesorDAO;

/**
 *
 * @author maria
 */
public class ObtenerDTOs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = EstudianteDAO.getInstanceDAO();
        ProfesorDAO profesorDAO = ProfesorDAO.getInstanceDAO();
        
        // prueba obtener estudiante y sus clases
//        try {
//            EstudianteClasesDTO estudianteDTO = estudianteDAO.obtenerEstudianteConClases(1L);
//            System.out.println("Estudiante con sus clases:");
//            System.out.println(estudianteDTO);
//        } catch (PersistenciaException e) {
//            System.err.println("Error: " + e.getMessage());
//        }

        // pruba onbtener cantidad clases
        try {
            // ID del estudiante que queremos consultar 
            Long idEstudiante = 1L; 

            // Llamar al método para obtener el DTO
//            EstudianteCantidadClasesDTO estudianteDTO = estudianteDAO.obtenerCantidadClases(idEstudiante);
            ProfesorEstudiantesDTO profesorEstudiantesDTO = profesorDAO.obtenerEstudiantesProfesor(2L);
            // Imprimir el resultado
//            System.out.println("Información del estudiante:");
//            System.out.println(estudianteDTO);
            System.out.println("Estudiantes del Profesor " + profesorEstudiantesDTO.getNombreProfesor() + ":");
            List<String> nombresEstudiantes = profesorEstudiantesDTO.getEstudiantes();
            for (int i = 0; i < nombresEstudiantes.size(); i++) {
                System.out.println("Estudiante: " + nombresEstudiantes.get(i));
            }
            
            profesorMejorEstudianteDTO mejorEstudiante = profesorDAO.obtenerMejorEstudiante(1L);
            System.out.println("El mejor estudiante del profesor " + mejorEstudiante.getNombreProfe()+ " es: ");
            System.out.println(mejorEstudiante.getNombreEstudiante() + 
                    " de la clase " + mejorEstudiante.getNombreClase() + 
                    " con un promedio de: " + mejorEstudiante.getPromedioMasAlto());

        } catch (PersistenciaException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

}
