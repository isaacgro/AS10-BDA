/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dominio.DTOs.EstudianteCalificaciones;
import dominio.entidades.Clase;
import dominio.entidades.Direccion;
import dominio.entidades.Estudiante;
import dominio.enums.Estatus;
import exception.PersistenciaException;
import java.util.List;
import persistencia.DAOs.ClaseDAO;
import persistencia.DAOs.EstudianteDAO;

/**
 *
 * @author maria
 */
public class InsertarEstudiantesClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        EstudianteDAO estudianteDAO = EstudianteDAO.getInstanceDAO();
        ClaseDAO claseDAO = ClaseDAO.getInstanceDAO();

        try {
            // Creamos tres clases
            Clase clase1 = claseDAO.buscarPorId(1L);
            Clase clase2 = claseDAO.buscarPorId(2L);
            Clase clase3 = claseDAO.buscarPorId(3L);

            // guardamos las clases
//            clase1 = claseDAO.guardar(clase1);
//            clase2 = claseDAO.guardar(clase2);
//            clase3 = claseDAO.guardar(clase3);

            // control para confirmar que se guardaron
//            System.out.println("Clases creadas:");
//
//            System.out.println("Clase 1: " + clase1.getNombre());
//            System.out.println("Clase 2: " + clase2.getNombre());
//            System.out.println("Clase 3: " + clase3.getNombre());

            // Creamos e insertamos estudiantes en las clases
            Estudiante estudiante1 = new Estudiante("Yeyoso Johnson", 9.0, Estatus.ACTIVO, new Direccion("Calle A", 123, "Centro"));
            estudiante1 = estudianteDAO.guardar(estudiante1);

            Estudiante estudiante2 = new Estudiante("Arielito Martinez", 7.5, Estatus.ACTIVO, new Direccion("Calle B", 456, "Norte"));
            estudiante2 = estudianteDAO.guardar(estudiante2);

            Estudiante estudiante3 = new Estudiante("Isaaquito Alpha", 8.2, Estatus.INACTIVO, new Direccion("Calle C", 789, "Sur"));
            estudiante3 = estudianteDAO.guardar(estudiante3);

            Estudiante estudiante4 = new Estudiante("Cesar Coronel", 6.8, Estatus.ACTIVO, new Direccion("Calle D", 159, "Este"));
            estudiante4 = estudianteDAO.guardar(estudiante4);
            
            Estudiante estudiante5 = new Estudiante("Choix Lopez", 7.8, Estatus.INACTIVO, new Direccion("Calle I", 405, "Oeste"));
            estudiante5 = estudianteDAO.guardar(estudiante4);

            // Inscribir estudiantes en clases usando el método inscribirEnClase()
            estudianteDAO.inscribirEnClase(estudiante1.getId(), clase1.getId());
            estudianteDAO.inscribirEnClase(estudiante2.getId(), clase2.getId());
            estudianteDAO.inscribirEnClase(estudiante3.getId(), clase3.getId());
            estudianteDAO.inscribirEnClase(estudiante4.getId(), clase1.getId());
            estudianteDAO.inscribirEnClase(estudiante4.getId(), clase3.getId());
            estudianteDAO.inscribirEnClase(estudiante5.getId(), clase2.getId());
            estudianteDAO.inscribirEnClase(estudiante3.getId(), clase1.getId());
            
            Double promedioMayor = 7.50;
            
            
            System.out.println(" ");
            System.out.println("--- Listado de promedios de alumnos ---");
            for(EstudianteCalificaciones e : estudianteDAO.obtenerPromedioMayor(promedioMayor)){
                System.out.println(" - " + e.getNombreEstudiante() + " promedio: " + e.getPromedio());
            }
            
            // Control, imprimimos los estudiantes inscritos en una clase
            System.out.println(" ");
            System.out.println("--- Listado de estudiantes por clase ---");

            System.out.println("Clase: " + clase1.getNombre());
            for (Estudiante e : claseDAO.obtenerEstudiantesPorClase(clase1.getId())) {
                System.out.println(" - " + e.getNombre());
            }

            System.out.println("\nClase: " + clase2.getNombre());
            for (Estudiante e : claseDAO.obtenerEstudiantesPorClase(clase2.getId())) {
                System.out.println(" - " + e.getNombre());
            }

            System.out.println("\nClase: " + clase3.getNombre());
            for (Estudiante e : claseDAO.obtenerEstudiantesPorClase(clase3.getId())) {
                System.out.println(" - " + e.getNombre());
            }

            System.out.println("Estudiantes inscritos en las clases correctamente.");

        } catch (PersistenciaException e) {
            System.err.println("Error en la persistencia: " + e.getMessage());
        }
    }

}
