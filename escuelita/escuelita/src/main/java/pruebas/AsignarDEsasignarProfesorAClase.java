/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

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
            Profesor profesor2 = new Profesor("Felipe Cabada", "ernesto.navarro@universidad.com");

            profesor1 = profesorDAO.guardar(profesor1);
            profesor2 = profesorDAO.guardar(profesor2);

            System.out.println("Profesores creados:");
            System.out.println("Profesor 1: " + profesor1.getNombre());
            System.out.println("Profesor 2: " + profesor2.getNombre());

            // Crear una clase sin profesor
            Clase clase = new Clase("Programación Avanzada", null);
            clase = claseDAO.guardar(clase);
            System.out.println("Clase creada: " + clase.getNombre());

            // Asignar un profesor a la clase
            if (claseDAO.asignarProfesor(clase.getId(), profesor1.getId())) {
                System.out.println("Profesor " + profesor1.getNombre() + " asignado a " + clase.getNombre());
            }

            // Verificar que el profesor tiene asignada la clase
            imprimirClasesDeProfesor(profesor1.getId(), profesorDAO);

            // Desasignar el profesor de la clase
            if (claseDAO.desasignarProfesor(clase.getId())) {
                System.out.println("Profesor desasignado de la clase " + clase.getNombre());
            }

            // Verificar que el profesor ya no tiene la clase
            imprimirClasesDeProfesor(profesor1.getId(), profesorDAO);

            // Cambiar a otro profesor
            if (claseDAO.cambiarProfesor(clase.getId(), profesor2.getId())) {
                System.out.println("Profesor cambiado a " + profesor2.getNombre() + " en la clase " + clase.getNombre());
            }

            // Verificar que el nuevo profesor tiene asignada la clase
            imprimirClasesDeProfesor(profesor2.getId(), profesorDAO);

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
