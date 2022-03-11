package com.infinitivus.project.spring_boot_security_base.entity.person;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    @Pattern(regexp = "^([А-ЯЁа-яё]|[A-Za-z]){3,15}$", message = "Error! Enter the sample data")
    private String surname;

    @Column(name = "name")
    @Pattern(regexp = "^[А-ЯЁа-яё]|[A-Za-z]{3,15}\\s?([А-ЯЁа-яё]|[A-Za-z]{3,15})?$", message = "Error! Enter the sample data")
    private String name;

    @Column(name = "phone_number")
    @Pattern(regexp = "^((8|\\+7)[\\-]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "Error! Enter the sample data")
    private String phoneNumber;

    @Column(name = "email")
    @Pattern(regexp = "^(\\w+@\\w+\\.(com|ru))?$", message = "Error! Enter the sample data")
    private String email;

    @Column(name = "birthday")
    @Pattern(regexp = "^((0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.((19|20)\\d\\d))?$", message = "Error! Enter the sample data")
    private String birthday;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "mobile_home_id")
    private MobileHome mobileHome;

    public Person() {
    }

    public Person(String surname, String name, String phoneNumber, String email, String birthday, MobileHome mobileHome) {
        this.surname = surname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.mobileHome = mobileHome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public MobileHome getMobileHome() {
        return mobileHome;
    }

    public void setMobileHome(MobileHome mobileHome) {
        this.mobileHome = mobileHome;
    }
}

