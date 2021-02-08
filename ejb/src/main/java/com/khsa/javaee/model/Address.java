package com.khsa.javaee.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = Address.Query.FIND_ALL, query = "SELECT a FROM Address a ORDER BY a.city, a.street"),
})
public class Address extends BaseEntity {
    private static final long serialVersionUID = -4221152467323610512L;

    public static class Query {
        public static final String FIND_ALL = "Address.findAll";
    }

    private Integer postIndex;

    @NotNull
    @Size(min = 1, max = 64)
    private String city;

    @NotNull
    @Size(min = 1, max = 64)
    private String street;

    @NotNull
    @Size(min = 1, max = 8)
    private String house;

    private String apartment;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses", fetch = FetchType.EAGER)
    private Set<Contact> contacts = new HashSet<>();
}
