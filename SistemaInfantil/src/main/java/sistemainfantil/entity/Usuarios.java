package sistemainfantil.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    // 🔑 Relacionamento com Consomem (Conteudos)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Consomem> conteudosConsumidos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    
    public List<Consomem> getConteudosConsumidos() { return conteudosConsumidos; }
    public void setConteudosConsumidos(List<Consomem> conteudosConsumidos) { this.conteudosConsumidos = conteudosConsumidos; }
}
