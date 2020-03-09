package pl.michal.clinical_nutrition.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.auth.entity.Premissions;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;

import java.util.List;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
    List<Zamowienie> findByJosRealizujacyOrJosZamawiajacy(Jos josRealizujacy, Jos josZamawiajacy);
}
