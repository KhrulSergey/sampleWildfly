package com.khsa.javaee.dao;

import com.khsa.javaee.model.Contact;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ContactDao {
    List<Contact> findAll();

    void create(final Contact contact);

    Contact findById(String id);
}

