package pl.michal.clinical_nutrition.calculator.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;

import java.util.List;

@Repository
public interface ZamowieniePozRepository extends JpaRepository<ZamowieniePoz, Long> {
    List<ZamowieniePoz> findByZamowienie(Zamowienie zamowienie);
}
