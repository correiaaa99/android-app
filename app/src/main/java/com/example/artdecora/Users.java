package com.example.artdecora;

public class Users {
    private int idUser;
    private String nome;
    private String apelido;
    private String username;
    private int foto;
    private String auth_ket;
    private String password_hash;
    private String password_reset_token;
    private String email;
    private String status;
    private String created_at;
    private String updated_at;
    private String verification_token;

    public Users(int idUser, String nome, String apelido, String username, int foto, String auth_ket, String password_hash, String password_reset_token, String email, String status, String created_at, String updated_at, String verification_token) {
        this.idUser = idUser;
        this.nome = nome;
        this.apelido = apelido;
        this.username = username;
        this.foto = foto;
        this.auth_ket = auth_ket;
        this.password_hash = password_hash;
        this.password_reset_token = password_reset_token;
        this.email = email;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.verification_token = verification_token;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getAuth_ket() {
        return auth_ket;
    }

    public void setAuth_ket(String auth_ket) {
        this.auth_ket = auth_ket;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getVerification_token() {
        return verification_token;
    }

    public void setVerification_token(String verification_token) {
        this.verification_token = verification_token;
    }
}
