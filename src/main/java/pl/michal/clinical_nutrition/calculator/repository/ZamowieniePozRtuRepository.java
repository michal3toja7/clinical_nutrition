package pl.michal.clinical_nutrition.calculator.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozRtu;

import java.util.List;

@Repository
public interface ZamowieniePozRtuRepository extends JpaRepository<ZamowieniePozRtu, Long> {
    List<ZamowieniePozRtu> findByZamowienie(Zamowienie zamowienie);
}
