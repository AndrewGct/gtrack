package com.gtrack.model;

public class Usuario {
    private int id;
    private String usuario;
    private String email;
    private String senha;

    // Construtor vazio necessário para setar campos manualmente
    public Usuario() {
    }

    // Construtor completo (você já tinha)
    public Usuario(int id, String usuario, String email, String senha) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }

    // Getters
    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
}