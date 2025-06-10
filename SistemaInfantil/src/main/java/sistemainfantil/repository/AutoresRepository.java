package sistemainfantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemainfantil.entity.Autores;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
	
}
