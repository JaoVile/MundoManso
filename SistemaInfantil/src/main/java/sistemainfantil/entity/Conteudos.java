package sistemainfantil.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Conteudos")
public class Conteudos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conteudos", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "tempo", nullable = false)
    private LocalTime tempo;

    @Column(name = "sinopse", nullable = false, columnDefinition = "TEXT")
    private String sinopse;

    // Episódios pertencentes a este conteúdo
    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Episodios> episodios;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalTime getTempo() { return tempo; }
    public void setTempo(LocalTime tempo) { this.tempo = tempo; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

    public List<Episodios> getEpisodios() { return episodios; }
    public void setEpisodios(List<Episodios> episodios) {
        this.episodios = episodios;
        if (episodios != null) {
            for (Episodios ep : episodios) {
                ep.setConteudo(this);
            }
        }
    }
}