/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import dominio.entidades.Direccion;
import dominio.entidades.Estudiante;
import dominio.enums.Estatus;
import exception.PersistenciaException;
import persistencia.DAOs.EstudianteDAO;

/**
 *
 * @author maria
 */
public class InsertarEstudiantes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = EstudianteDAO.getInstanceDAO();

        try {
            // Insertar un estudiante con dirección
            Direccion direccion = new Direccion("Av. Siempre Viva", 742, "Springfield");
            Estudiante estudiante = new Estudiante("Barsinso", 8.5, Estatus.ACTIVO, direccion);
            estudiante = estudianteDAO.guardar(estudiante);
            System.out.println("Estudiante insertado: " + estudiante);

            // Buscar el estudiante por ID
            Estudiante estudianteEncontrado = estudianteDAO.buscarPorId(estudiante.getId());
            System.out.println("Estudiante encontrado: " + estudianteEncontrado);

            // Quitar la dirección al estudiante (ver efecto del orphanRemoval)
            estudianteEncontrado.setDireccion(null);
            estudianteDAO.actualizar(estudianteEncontrado);
            System.out.println("Dirección eliminada del estudiante.");

            // Buscar el estudiante nuevamente para verificar que la dirección se eliminó
            Estudiante estudianteSinDireccion = estudianteDAO.buscarPorId(estudiante.getId());
            System.out.println("Estudiante sin dirección: " + estudianteSinDireccion);

            // Eliminar el estudiante (ver efecto en la dirección)
            estudianteDAO.eliminar(estudiante.getId());
            System.out.println("Estudiante eliminado.");

            // Intentar buscar el estudiante eliminado
            Estudiante estudianteBorrado = estudianteDAO.buscarPorId(estudiante.getId());
            if (estudianteBorrado == null) {
                System.out.println("El estudiante fue eliminado correctamente.");
            } else {
                System.out.println("El estudiante aún existe: " + estudianteBorrado);
            }

        } catch (PersistenciaException e) {
            System.err.println("Error en la persistencia: " + e.getMessage());
        }
    }

}
