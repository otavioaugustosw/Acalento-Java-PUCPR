package com.littlecats.acalentoong.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UUID id;
    private String name;
    private String cpf;
    private Date birthDay;
    private String cellphone;
    private String email;
    private String password;

    private String cep;
    private String street;
    private String neighborhood;
    private int number;
    private String complement;
    private String city;
    private String state;

    public User(String name, String cpf, Date birthDay, String cellphone, String email, String password,
                String cep, String street, String neighborhood, int number, String complement, String city, String state) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cpf = cpf;
        this.birthDay = birthDay;
        this.cellphone = cellphone;
        this.email = email;
        this.password = password;
        this.cep = cep;
        this.street = street;
        this.neighborhood = neighborhood;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    // Getters e Setters...

    public UUID getId() {
        return id;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Date getBirthDay() { return birthDay; }
    public void setBirthDay(Date birthDay) { this.birthDay = birthDay; }

    public String getCellphone() { return cellphone; }
    public void setCellphone(String cellphone) { this.cellphone = cellphone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
