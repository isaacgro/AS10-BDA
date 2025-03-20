/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dominio.DTOs.ProfesorCantidadClasesDTO;
import dominio.entidades.Clase;
import dominio.entidades.Profesor;
import exception.PersistenciaException;
import persistencia.DAOs.ClaseDAO;
import persistencia.DAOs.ProfesorDAO;

/**
 *
 * @author maria
 */
public class AsignarDEsasignarProfesorAClase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClaseDAO claseDAO = ClaseDAO.getInstanceDAO();
        ProfesorDAO profesorDAO = ProfesorDAO.getInstanceDAO();

        try {
            // Crear profesores
            Profesor profesor1 = new Profesor("Gibran Duran", "gibran.duran@universidad.com");
            Profesor profesor2 = new Profesor("Felipe Cabada", "felipe.cabada@universidad.com");
            Profesor profesor3 = new Profesor("Dr Baltazar", "dr.baltazar@universidad.com");


            profesor1 = profesorDAO.guardar(profesor1);
            profesor2 = profesorDAO.guardar(profesor2);
            profesor3 = profesorDAO.guardar(profesor3);

            System.out.println("Profesores creados:");
            System.out.println("Profesor 1: " + profesor1.getNombre());
            System.out.println("Profesor 2: " + profesor2.getNombre());
            System.out.println("Profesor 3: " + profesor3.getNombre());
            

            // Crear una clase sin profesor
            Clase clase1 = new Clase("Programación Avanzada", null);
            Clase clase2 = new Clase("Redes", null);
            Clase clase3 = new Clase("Bases de Datos", null);
            Clase clase4 = new Clase("Seguridad Informatica", null);
            Clase clase5 = new Clase("Diseño de Software", null);
            
            clase1 = claseDAO.guardar(clase1);
            clase2 = claseDAO.guardar(clase2);
            clase3 = claseDAO.guardar(clase3);
            clase4 = claseDAO.guardar(clase4);
            clase5 = claseDAO.guardar(clase5);
            
            System.out.println("Clase creada: " + clase1.getNombre());
            System.out.println("Clase creada: " + clase2.getNombre());
            System.out.println("Clase creada: " + clase3.getNombre());
            System.out.println("Clase creada: " + clase4.getNombre());
            System.out.println("Clase creada: " + clase5.getNombre());

            // Asignar un profesor a la clase
            if (claseDAO.asignarProfesor(clase1.getId(), profesor1.getId())) {
                System.out.println("Profesor " + profesor1.getNombre() + " asignado a " + clase1.getNombre());
            }
            if (claseDAO.asignarProfesor(clase2.getId(), profesor2.getId())) {
                System.out.println("Profesor " + profesor2.getNombre() + " asignado a " + clase2.getNombre());
            }
            if (claseDAO.asignarProfesor(clase3.getId(), profesor1.getId())) {
                System.out.println("Profesor " + profesor1.getNombre() + " asignado a " + clase3.getNombre());
            }
            if (claseDAO.asignarProfesor(clase4.getId(), profesor2.getId())) {
                System.out.println("Profesor " + profesor2.getNombre() + " asignado a " + clase4.getNombre());
            }
            if (claseDAO.asignarProfesor(clase5.getId(), profesor3.getId())) {
                System.out.println("Profesor " + profesor3.getNombre() + " asignado a " + clase5.getNombre());
            }
            if (claseDAO.asignarProfesor(clase5.getId(), profesor1.getId())) {
                System.out.println("Profesor " + profesor1.getNombre() + " asignado a " + clase5.getNombre());
            }

            // Verificar que el profesor tiene asignada la clase
//            imprimirClasesDeProfesor(profesor1.getId(), profesorDAO);

            // Desasignar el profesor de la clase
//            if (claseDAO.desasignarProfesor(clase.getId())) {
//                System.out.println("Profesor desasignado de la clase " + clase.getNombre());
//            }

            // Verificar que el profesor ya no tiene la clase
//            imprimirClasesDeProfesor(profesor1.getId(), profesorDAO);

            // Cambiar a otro profesor
//            if (claseDAO.cambiarProfesor(clase.getId(), profesor2.getId())) {
//                System.out.println("Profesor cambiado a " + profesor2.getNombre() + " en la clase " + clase.getNombre());
//            }

            // Verificar que el nuevo profesor tiene asignada la clase
//            imprimirClasesDeProfesor(profesor2.getId(), profesorDAO);
            ProfesorCantidadClasesDTO clasesProfesor1 = profesorDAO.obtenerClasesProfesor(profesor1.getId());
            System.out.println("El Profesor " + clasesProfesor1.getNombreProfesor() + " imparte " + clasesProfesor1.getCantidadClases() + " clases.");
            
            ProfesorCantidadClasesDTO clasesProfesor2 = profesorDAO.obtenerClasesProfesor(profesor2.getId());
            System.out.println("El Profesor " + clasesProfesor2.getNombreProfesor() + " imparte " + clasesProfesor2.getCantidadClases() + " clases.");
            
            ProfesorCantidadClasesDTO clasesProfesor3 = profesorDAO.obtenerClasesProfesor(profesor3.getId());
            System.out.println("El Profesor " + clasesProfesor3.getNombreProfesor() + " imparte " + clasesProfesor3.getCantidadClases() + " clases.");
            

        } catch (PersistenciaException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Método auxiliar para imprimir las clases asignadas a un profesor
    private static void imprimirClasesDeProfesor(Long idProfesor, ProfesorDAO profesorDAO) throws PersistenciaException {
        Profesor profesor = profesorDAO.buscarPorId(idProfesor);
        System.out.println("Clases asignadas al Profesor " + profesor.getNombre() + ":");
        if (profesor.getClases().isEmpty()) {
            System.out.println("   - No tiene clases asignadas.");
        } else {
            for (Clase c : profesor.getClases()) {
                System.out.println("   - " + c.getNombre());
            }
        }
    }

}
