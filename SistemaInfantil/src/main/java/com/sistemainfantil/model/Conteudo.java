package com.sistemainfantil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Conteudo")
public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conteudo")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tempo")
    private String tempo;

    @Column(name = "sinopse")
    private String sinopse;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTempo() { return tempo; }
    public void setTempo(String tempo) { this.tempo = tempo; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }
}
