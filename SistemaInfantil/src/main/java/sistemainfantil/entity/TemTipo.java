package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TemTipo")
public class TemTipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tem_tipo")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_tipos_id", nullable = false)
    private Tipos tipo;

    @ManyToOne
    @JoinColumn(name = "fk_conteudos_id", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Tipos getTipo() { return tipo; }
    public void setTipo(Tipos tipo) { this.tipo = tipo; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}