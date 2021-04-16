package com.softserveinc.ita.rozetka.page_objects;

public class ShippingAddress {
    private String surname ;
    private String name;
    private String city;
    private String phone;

    public ShippingAddress(String surname, String name, String city, String phone) {
        this.surname = surname;
        this.name = name;
        this.city = city;
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
