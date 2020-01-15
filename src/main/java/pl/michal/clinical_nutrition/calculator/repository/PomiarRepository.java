package pl.michal.clinical_nutrition.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;

import java.util.List;

@Repository
public interface PomiarRepository  extends JpaRepository<Pomiar, Long> {
    List<Pomiar> findByIdPacjenta(long idPacjenta);
}


