package com.khsa.javaee.dao;

import com.khsa.javaee.model.Address;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AddressDao {
    List<Address> findAll();

    void create(Address address);
}
