package com.khsa.javaee.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Contact extends BaseEntity {
    private static final long serialVersionUID = 9053144412997611591L;

    @NotNull
    @Size(min = 1, max = 32)
    private String firstName;

    private String middleName;

    @NotNull
    @Size(min = 1, max = 32)
    private String lastName;

    private String phoneNumber;

    @ToString.Exclude
    @ManyToMany(targetEntity = Address.class)
    @JoinTable(name = "Contact_Addresses",
            joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "id"))
    private Set<Address> addresses = new HashSet<>();
}
