package sistemainfantil.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Conteudos")
public class Conteudos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conteudos")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tempo")
    private String tempo;

    @Column(name = "sinopse")
    private String sinopse;

    // Relacionamentos

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Fazem> fazem;

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Publicam> publicacoes;

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Consomem> consumo;

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TemGenero> generos;

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TemTipo> tipos;

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contem> episodios;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTempo() { return tempo; }
    public void setTempo(String tempo) { this.tempo = tempo; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

    public List<Fazem> getFazem() { return fazem; }
    public void setFazem(List<Fazem> fazem) { this.fazem = fazem; }

    public List<Publicam> getPublicacoes() { return publicacoes; }
    public void setPublicacoes(List<Publicam> publicacoes) { this.publicacoes = publicacoes; }

    public List<Consomem> getConsumo() { return consumo; }
    public void setConsumo(List<Consomem> consumo) { this.consumo = consumo; }

    public List<TemGenero> getGeneros() { return generos; }
    public void setGeneros(List<TemGenero> generos) { this.generos = generos; }

    public List<TemTipo> getTipos() { return tipos; }
    public void setTipos(List<TemTipo> tipos) { this.tipos = tipos; }

    public List<Contem> getEpisodios() { return episodios; }
    public void setEpisodios(List<Contem> episodios) { this.episodios = episodios; }
}
