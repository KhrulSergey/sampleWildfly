package com.khsa.javaee.dao;


import com.khsa.javaee.model.Contact;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ContactDaoBean implements ContactDao {
    @PersistenceContext(unitName = "wildfly-jpa")
    private EntityManager em;

    //Use CriteriBuilder
    @Override
    public List<Contact> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
        Root<Contact> rootEntry = cq.from(Contact.class);
        CriteriaQuery<Contact> all = cq.select(rootEntry);
        TypedQuery<Contact> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
    @Override
    public Contact findById(String id) {
        return em.find(Contact.class, id);
    }

    @Override
    public void create(final Contact contact) {
        em.persist(contact);
    }

}

