package sistemainfantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemainfantil.entity.Contem;

public interface ContemRepository extends JpaRepository<Contem, Long> {
}
