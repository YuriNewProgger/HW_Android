package com.example.autodrive;

import android.app.Application;

public class UserModel extends Application {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String pData;
    private String phone;
    private int idLogin;
    private String email;
    private int idRole;
    private String role;

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setpData(String pData) {
        this.pData = pData;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getpData() {
        return pData;
    }

    public String getPhone() {
        return phone;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public String getEmail() {
        return email;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getRole() {
        return role;
    }
}

/*
* "id": 2,
    "login": "Test",
    "password": "Test",
    "name": "Иван",
    "surname": "Иванов",
    "patronymic": "Иванович",
    "p_data": "1345 123456",
    "number_phone": "+79033331145",
    "id_login": 1,
    "email": "test@mail.ru",
    "id_role": 2,
    "role": "client"
*
* */
