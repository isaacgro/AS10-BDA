/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dominio.DTOs.EstudianteCantidadClasesDTO;
import dominio.DTOs.EstudianteClasesDTO;
import exception.PersistenciaException;
import persistencia.DAOs.EstudianteDAO;

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

        // prueba obtener estudiante y sus clases
        try {
            EstudianteClasesDTO estudianteDTO = estudianteDAO.obtenerEstudianteConClases(1L);
            System.out.println("Estudiante con sus clases:");
            System.out.println(estudianteDTO);
        } catch (PersistenciaException e) {
            System.err.println("Error: " + e.getMessage());
        }

        // pruba onbtener cantidad clases
        try {
            // ID del estudiante que queremos consultar 
            Long idEstudiante = 1L; 

            // Llamar al método para obtener el DTO
            EstudianteCantidadClasesDTO estudianteDTO = estudianteDAO.obtenerCantidadClases(idEstudiante);

            // Imprimir el resultado
            System.out.println("Información del estudiante:");
            System.out.println(estudianteDTO);

        } catch (PersistenciaException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

}
