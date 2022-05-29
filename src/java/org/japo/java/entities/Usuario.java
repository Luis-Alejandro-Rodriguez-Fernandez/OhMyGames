/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.japo.java.entities;

import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author Luis Alejandro Rodríguez Fernández -
 * luisalejandro.rodriguez.alum@iescamp.es
 */
public class Usuario {

    public static final String ER_USER = "[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ0-9\\-\\. ]{3,20}";
    public static final String ER_PASS = "[\\w]{3,30}";

    private int id;
    private String user;
    private String password;
    private String email;
    private String avatar;
    private int grupo;

    public Usuario(int id, String user, String password, String email, String avatar, int grupo) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.grupo = grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public static final boolean validarUsername(String dato) {
        return UtilesValidacion.validarDato(dato, ER_USER);
    }

    public static final boolean validarPassword(String dato) {
        return UtilesValidacion.validarDato(dato, ER_PASS);
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ",\"usuario\":\"" + user + "\",\"pass\":\"" + password + "\",\"email\":\"" + email + "\",\"grupo\":" + grupo + "}";
    }

}
