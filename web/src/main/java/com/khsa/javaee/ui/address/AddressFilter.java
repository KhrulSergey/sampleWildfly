package com.khsa.javaee.ui.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AddressFilter {
    @Getter
    @Setter
    private String contactId = "";
    @Getter
    @Setter
    private String city = "";
}
