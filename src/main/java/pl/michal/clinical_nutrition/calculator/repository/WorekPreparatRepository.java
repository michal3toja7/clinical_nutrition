package pl.michal.clinical_nutrition.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;

@Repository
public interface WorekPreparatRepository extends JpaRepository<WorekPreparat, Long> {
}
