package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Contem")
public class Contem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_episodios_id", nullable = false)
    private Episodios episodio;

    @ManyToOne
    @JoinColumn(name = "fk_conteudos_id", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Episodios getEpisodio() { return episodio; }
    public void setEpisodio(Episodios episodio) { this.episodio = episodio; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}
