/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import dominio.entidades.Clase;
import dominio.entidades.Estudiante;
import dominio.entidades.Profesor;
import exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import persistencia.conexion.Conexion;

/**
 *
 * @author maria
 */
public class ClaseDAO {

    private static ClaseDAO instanceClaseDAO;

    private ClaseDAO() {
    }

    public static ClaseDAO getInstanceDAO() {
        if (instanceClaseDAO == null) {
            instanceClaseDAO = new ClaseDAO();
        }
        return instanceClaseDAO;
    }

    public Clase guardar(Clase clase) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(clase);
            em.getTransaction().commit();
            return clase;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Clase buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Clase.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Clase> obtenerTodas() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT c FROM Clase c", Clase.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todas las clases: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Clase actualizar(Clase clase) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Clase claseActualizada = em.merge(clase); // Guardamos la entidad actualizada
            em.getTransaction().commit();
            return claseActualizada; // Devolvemos la clase actualizada
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public boolean eliminar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Clase clase = em.find(Clase.class, id);
            if (clase != null) {
                em.getTransaction().begin();
                em.remove(clase);
                em.getTransaction().commit();
                return true;
            } else {
                throw new PersistenciaException("La clase con ID " + id + " no existe.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    // Obtener los estudiantes de una clase específica

    public List<Estudiante> obtenerEstudiantesPorClase(Long idClase) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Clase clase = em.find(Clase.class, idClase);
            if (clase == null) {
                throw new PersistenciaException("Clase no encontrada.");
            }
            return clase.getEstudiantes(); // Devuelve la lista de estudiantes de la clase
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener estudiantes de la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Método para asignar un profesor a una clase
    public boolean asignarProfesor(Long idClase, Long idProfesor) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Clase clase = em.find(Clase.class, idClase); // buscamos la clase
            Profesor profesor = em.find(Profesor.class, idProfesor); // buscamos al maeto

            if (clase == null || profesor == null) {
                throw new PersistenciaException("Clase o Profesor no encontrados.");
            }

            // Si la clase ya tiene un profesor, quitarla de su lista de clases antes de asignar uno nuevo
            if (clase.getProfesor() != null) {
                Profesor profesorAnterior = clase.getProfesor();
                profesorAnterior.getClases().remove(clase);
                em.merge(profesorAnterior); // Actualizamos el profesor anterior
            }

            // Asignar el nuevo profesor
            clase.setProfesor(profesor);
            profesor.getClases().add(clase); // Agregar la clase en la lista del profesor

            em.merge(clase);
            em.merge(profesor); // Actualizar el nuevo profesor en la BD

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al asignar profesor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Método para desasignar o quitar un profesor de una clase
    public boolean desasignarProfesor(Long idClase) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            Clase clase = em.find(Clase.class, idClase);

            if (clase == null) {
                throw new PersistenciaException("Clase no encontrada.");
            }

            if (clase.getProfesor() != null) {
                Profesor profesorAnterior = clase.getProfesor();
                profesorAnterior.getClases().remove(clase); // Quitar la clase del profesor
                em.merge(profesorAnterior); // Guardar cambios en el profesor
            }

            clase.setProfesor(null);
            em.merge(clase);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al desasignar profesor: " + e.getMessage());
        } finally {
            em.close();
        }
    }
// metodo para cambiar profe

    public boolean cambiarProfesor(Long idClase, Long idNuevoProfesor) throws PersistenciaException {
        return asignarProfesor(idClase, idNuevoProfesor); // Ya maneja la reasignación correctamente
    }

}
