/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import dominio.DTOs.ProfesorCantidadClasesDTO;
import dominio.DTOs.nombreProfeListaDTO;
import persistencia.conexion.Conexion;
import dominio.entidades.Clase;
import dominio.entidades.Profesor;
import exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author maria
 */
public class ProfesorDAO {

    private static ProfesorDAO instanceProfesorDAO;

    private ProfesorDAO() {
    }

    public static ProfesorDAO getInstanceDAO() {
        if (instanceProfesorDAO == null) {
            instanceProfesorDAO = new ProfesorDAO();
        }
        return instanceProfesorDAO;
    }

    public Profesor guardar(Profesor profesor) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(profesor);
            em.getTransaction().commit();
            return profesor;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al guardar el profesor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Profesor buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Profesor.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el profesor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Profesor> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todos los profesores: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Profesor actualizar(Profesor profesor) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Profesor profesorActualizado = em.merge(profesor);
            em.getTransaction().commit();
            return profesorActualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al actualizar el profesor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public boolean eliminar(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Profesor profesor = em.find(Profesor.class, id);
            if (profesor == null) {
                throw new PersistenciaException("El profesor con ID " + id + " no existe.");
            }
            em.getTransaction().begin();
            em.remove(profesor);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar el profesor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public ProfesorCantidadClasesDTO obtenerClasesProfesor(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Profesor profesor = em.find(Profesor.class, id);
            if (profesor == null) {
                throw new PersistenciaException("El profesor con ID " + id + " no existe.");
            }

            int clasesProfesor = profesor.getClases().size();
            return new ProfesorCantidadClasesDTO(profesor.getNombre(), clasesProfesor);
        } finally {
            em.close();
        }
    }

    public nombreProfeListaDTO obtenerClasesImpartidas(Long id) throws PersistenciaException {

        EntityManager em = Conexion.crearConexion();
        try {
            Profesor profe = em.find(Profesor.class, id);
            if (profe == null) {
                throw new PersistenciaException("El profesor con id" + id + " no existe");
            }

            List<String> clasesImpartidas = new ArrayList<>();
            for (Clase clase : profe.getClases()) {
                clasesImpartidas.add(clase.getNombre());// Agregar el nombre de cada clase a la lista
            }

            return new nombreProfeListaDTO(profe.getNombre(), clasesImpartidas);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener estudiante con sus clases: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}