package pl.michal.clinical_nutrition.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.calculator.entity.Dodatek;

@Repository
public interface DodatekRepository  extends JpaRepository<Dodatek, Long> {
}
