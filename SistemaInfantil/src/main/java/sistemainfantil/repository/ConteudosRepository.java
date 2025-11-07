package sistemainfantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemainfantil.entity.Conteudos;

public interface ConteudosRepository extends JpaRepository<Conteudos, Long> {
}
