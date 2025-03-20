/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import persistencia.conexion.Conexion;
import dominio.entidades.Direccion;
import exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author maria
 */
public class DireccionDAO {

    private static DireccionDAO instanceDireccionEstudianteDAO;

    private DireccionDAO() {
    }

    public static DireccionDAO getInstanceDAO() {
        if (instanceDireccionEstudianteDAO == null) {
            instanceDireccionEstudianteDAO = new DireccionDAO();
        }
        return instanceDireccionEstudianteDAO;
    }

    // Guardar una dirección en la base de datos (asegurando que tenga un estudiante)
    public Direccion guardar(Direccion direccion) throws PersistenciaException {
        if (direccion.getEstudiante() == null) {
            throw new PersistenciaException("Error: La dirección debe estar asociada a un estudiante.");
        }

        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(direccion);
            em.getTransaction().commit();

            if (direccion.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para la dirección.");
            }
            return direccion;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar la dirección: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Buscar dirección por ID
    public Direccion buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Direccion.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar la dirección: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Obtener todas las direcciones de un estudiante específico
    public List<Direccion> obtenerPorEstudiante(Long idEstudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT d FROM Direccion d WHERE d.estudiante.id = :idEstudiante", Direccion.class)
                    .setParameter("idEstudiante", idEstudiante)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener direcciones del estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Actualizar una dirección en la base de datos
    public Direccion actualizar(Direccion direccion) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Direccion direccionActualizada = em.merge(direccion);
            em.getTransaction().commit();
            return direccionActualizada; // Devolvemos la dirección actualizada
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar la dirección: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
