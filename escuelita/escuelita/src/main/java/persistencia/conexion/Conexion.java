/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase de utilidad para manejar la conexión con la base de datos mediante JPA.
 * 
 * Esta clase gestiona una única instancia de EntityManagerFactory,
 * la cual se reutiliza durante toda la ejecución de la aplicación. Sin embargo,
 * cada llamada a crearConexion() devuelve una nueva instancia de
 * EntityManager, permitiendo la gestión adecuada del ciclo de vida de las
 * entidades.
 *
 * No se implementa el patrón Singleton manualmente porque {@code EntityManagerFactory}
 * ya maneja internamente la creación y reutilización de instancias.
 * @author maria
 */
public class Conexion {

    /*
    OJO, esto NO es singleton:
        private static EntityManagerFactory emf; //opcional, la fabrica ya gestiona esto

    de hecho eso ya lo gestiona el EMF internamente, es parte de sus propiedades. Me refiero a la gestion de instancias.
    El patrón Singleton busca garantizar que solo haya una instancia de una clase en toda la aplicación. 
    Sin embargo, el EntityManagerFactory (EMF) ya gestiona internamente la creación y reutilización de instancias 
    de fábrica. En otras palabras, no necesitas implementar un Singleton manualmente porque el propio EMF se comporta 
    de manera similar.
    
    


     */
    // Vamos a manejarlo asi por lo pronto:
    /**
     * Instancia única de EntityManagerFactory, creada al inicio de la aplicación.
     * Se reutiliza en todas las conexiones.
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConexionPU");

     /**
     * Crea y devuelve una nueva instancia de EntityManager.
     * 
     * @return una nueva instancia de EntityManager para gestionar las operaciones con la base de datos.
     */
    public static EntityManager crearConexion() {    //Se obtiene un nuevo EntityManager en cada llamada a getEntityManager(), pero sin recrear la fábrica.
        return emf.createEntityManager(); // Se reutiliza la fábrica y se obtiene un nuevo EntityManager
    }

     /**
     * Cierra la instancia de EntityManagerFactory si está abierta,
     * liberando los recursos utilizados.
     */
    public static void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
        // no es necesario un else
    }

    // Recuerden, no siempre la mejor solucion es la primera que se nos ocurre o lo ultimo que aprendemos, siempre hay que adaptarnos a buscar las "mejores opciones"
    // Sin embargo, es un gusto que siempre lleguen con ideas nuevas(:
}
