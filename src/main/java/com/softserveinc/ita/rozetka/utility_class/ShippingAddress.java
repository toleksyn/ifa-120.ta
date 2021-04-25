package com.softserveinc.ita.rozetka.utility_class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ShippingAddress {
    private String surname;
    private String name;
    private String city;
    private String phone;
}
