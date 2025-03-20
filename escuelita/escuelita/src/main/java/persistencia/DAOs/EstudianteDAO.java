/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.DAOs;

import dominio.DTOs.EstudianteCalificaciones;
import dominio.DTOs.EstudianteCantidadClasesDTO;
import dominio.DTOs.EstudianteClasesDTO;
import persistencia.conexion.Conexion;
import dominio.entidades.Clase;
import dominio.entidades.Estudiante;
import dominio.enums.Estatus;
import exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author maria
 */
public class EstudianteDAO {

    private static EstudianteDAO instanceEstudianteDAO;

    private EstudianteDAO() {
    }

    public static EstudianteDAO getInstanceDAO() {
        if (instanceEstudianteDAO == null) {
            instanceEstudianteDAO = new EstudianteDAO();
        }
        return instanceEstudianteDAO;
    }
// Guardar un estudiante en la base de datos

    public Estudiante guardar(Estudiante estudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(estudiante);
            em.getTransaction().commit();

            if (estudiante.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el estudiante.");
            }
            return estudiante;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    // Buscar estudiante por ID

    public Estudiante buscarPorId(Long id) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.find(Estudiante.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar el estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Obtener estudiantes por estatus (activos o inactivos)
    public List<Estudiante> obtenerEstudiantesPorEstatus(Estatus estatus) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.estatus = :estatus", Estudiante.class)
                    .setParameter("estatus", estatus)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener estudiantes por estatus: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodos() throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            return em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los estudiantes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Actualizar un estudiante en la base de datos
    public Estudiante actualizar(Estudiante estudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Estudiante estudianteActualizado = em.merge(estudiante);
            em.getTransaction().commit();
            return estudianteActualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo actualizar el estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Eliminar un estudiante de la base de datos (se eliminan sus direcciones automáticamente si `orphanRemoval = true`)
    public boolean eliminar(Long idEstudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
            if (estudiante == null) {
                throw new PersistenciaException("El estudiante con ID " + idEstudiante + " no existe.");
            }

            em.getTransaction().begin();
            em.remove(estudiante);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo eliminar el estudiante: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // método para inscribir un estudiante en una clase particular
    public boolean inscribirEnClase(Long idEstudiante, Long idClase) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            // Buscar el estudiante y la clase
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
            Clase clase = em.find(Clase.class, idClase);

            if (estudiante == null || clase == null) {
                throw new PersistenciaException("Estudiante o Clase no encontrados.");
            }

            // Agregar la clase al estudiante
            estudiante.getClases().add(clase);
            // Agregar al estudiante a la clase
            clase.getEstudiantes().add(estudiante);

            em.merge(estudiante);  // Actualizar el estudiante para que se refleje el cambio de la tabla

            // Funciona porque estudiante es el dueño de la relación en la anotación @ManyToMany, y al hacer merge(estudiante), se sincroniza con la base de datos correctamente.
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al inscribir al estudiante en la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // método para desinscribir un estudiante en una clase particular
    public boolean eliminarDeClase(Long idEstudiante, Long idClase) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            em.getTransaction().begin();

            // Cargar el estudiante con sus clases
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
            Clase clase = em.find(Clase.class, idClase);

            if (estudiante == null || clase == null) {
                throw new PersistenciaException("Estudiante o Clase no encontrados.");
            }

            if (!estudiante.getClases().contains(clase)) {
                throw new PersistenciaException("El estudiante no está inscrito en esta clase.");
            }

            // Eliminar la relación (es bidireccional por lo que se borra de ambos lados SI O SI)
            estudiante.getClases().remove(clase);
            clase.getEstudiantes().remove(estudiante);

            // Persistir los cambios
            estudiante = em.merge(estudiante); // Actualizamos al estudiante
            clase = em.merge(clase); // Importante!! también actualizar la clase

            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar estudiante de la clase: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public EstudianteClasesDTO obtenerEstudianteConClases(Long idEstudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);

            if (estudiante == null) {
                throw new PersistenciaException("Estudiante no encontrado.");
            }

            List<String> nombresClases = new ArrayList<>();
            for (Clase clase : estudiante.getClases()) {
                nombresClases.add(clase.getNombre()); // Agregar el nombre de cada clase a la lista
            }

            return new EstudianteClasesDTO(estudiante.getNombre(), nombresClases);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener estudiante con sus clases: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public EstudianteCantidadClasesDTO obtenerCantidadClases(Long idEstudiante) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
            if (estudiante == null) {
                throw new PersistenciaException("Estudiante no encontrado.");
            }

            int cantidadClases = estudiante.getClases().size(); // Contamos las clases inscritas
            return new EstudianteCantidadClasesDTO(estudiante.getNombre(), cantidadClases);
        } finally {
            em.close();
        }
    }
    
    public List<EstudianteCalificaciones> obtenerPromedioMayor(Double promedio) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e WHERE e.promedio > :promedio")
                    .setParameter("promedio", promedio)
                    .getResultList();
            List<EstudianteCalificaciones> estudiantesCalif = new ArrayList<>();
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = estudiantes.get(i);
                estudiantesCalif.add(new EstudianteCalificaciones(estudiante.getNombre(), estudiante.getPromedio()));
            }
            return estudiantesCalif;
        } catch (Exception e) {
            throw new PersistenciaException("Hubo un error al consultar los estudiante con un promedio mayor a " + 
                    promedio + ":" + e.getMessage());
        } finally {
            em.close();
        }
    }

}
