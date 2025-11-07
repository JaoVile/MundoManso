package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Episodios")
public class Episodios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_episodios")
    private Long id;

    @Column(name = "numero", nullable = false)
    private String numero;

    @Column(name = "link", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_conteudos", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}