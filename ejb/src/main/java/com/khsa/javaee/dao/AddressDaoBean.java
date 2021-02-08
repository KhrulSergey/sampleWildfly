package com.khsa.javaee.dao;

import com.khsa.javaee.model.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AddressDaoBean implements AddressDao {
    @PersistenceContext(unitName = "wildfly-jpa")
    private EntityManager em;

    //Use NamedQuery from model
    @Override
    public List<Address> findAll() {
        return em.createNamedQuery(Address.Query.FIND_ALL, Address.class).getResultList();
    }

    @Override
    public void create(final Address address) {
        em.persist(address);
    }
}
