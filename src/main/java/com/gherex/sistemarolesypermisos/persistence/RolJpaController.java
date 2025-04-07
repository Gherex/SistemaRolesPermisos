package com.gherex.sistemarolesypermisos.persistence;

import com.gherex.sistemarolesypermisos.logic.Rol;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

import static com.gherex.sistemarolesypermisos.persistence.JpaUtil.getEntityManager;

public class RolJpaController {

    public void create(Rol rol) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void update(Rol rol) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Rol findRol(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public List<Rol> findAllRoles() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Rol e");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol rol = em.find(Rol.class, id);
            if (rol != null) {
                em.remove(rol);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
