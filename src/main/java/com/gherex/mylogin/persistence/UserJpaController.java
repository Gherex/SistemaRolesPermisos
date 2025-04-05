package com.gherex.mylogin.persistence;

import com.gherex.mylogin.logic.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class UserJpaController {
    private final EntityManagerFactory emf;

    public UserJpaController() {
        this.emf = Persistence.createEntityManagerFactory("myloginPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // =========== MÉTODOS CRUD =========== //

    // CREATE (Guardar un nuevo usuario)
    public void create(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // READ (Obtener un usuario por ID)
    public User findUser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    // UPDATE (Editar un usuario existente)
    public void update(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // DELETE (Eliminar un usuario por ID)
    public void delete(Long id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Obtener todos los usuarios (SELECT * FROM User)
    public List<User> findAllUsers() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /* Buscar usuarios por un campo específico (ej: email)

    public List<User> findUsersByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", email);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    */

}