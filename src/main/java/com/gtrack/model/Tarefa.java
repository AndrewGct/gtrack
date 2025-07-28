package com.gtrack.model;

public class Tarefa {
    private int id;
    private String descricao;
     private String inicio;
    private String termino;
    private String diaSemana;
    private boolean concluida;
    private int usuarioId;

    // Construtor
    public Tarefa(int id, String descricao, String inicio, String termino, String diaSemana, boolean concluida, int usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.inicio = inicio;
        this.termino = termino;
        this.diaSemana = diaSemana;
        this.concluida = concluida;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) {
    this.id = id;
}
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getInicio(){ return inicio; }
    public void setInicio(String inicio){ this.inicio = inicio; } 
    
    public String getTermino(){ return termino; }
    public void setTermino(String termino){ this.termino = termino; } 

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
    
    

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}