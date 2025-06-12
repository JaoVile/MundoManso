package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tem_Generos")
public class TemGenero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_generos_id", nullable = false)
    private Generos genero;

    @ManyToOne
    @JoinColumn(name = "fk_conteudos_id", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Generos getGenero() { return genero; }
    public void setGenero(Generos genero) { this.genero = genero; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}