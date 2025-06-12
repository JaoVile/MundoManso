package sistemainfantil.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Consomem")
public class Consomem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consomem")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_usuarios_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "fk_conteudos_id", nullable = false)
    private Conteudos conteudo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuarios getUsuario() { return usuario; }
    public void setUsuario(Usuarios usuario) { this.usuario = usuario; }

    public Conteudos getConteudo() { return conteudo; }
    public void setConteudo(Conteudos conteudo) { this.conteudo = conteudo; }
}