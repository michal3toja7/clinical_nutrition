package pl.michal.clinical_nutrition.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {

}
