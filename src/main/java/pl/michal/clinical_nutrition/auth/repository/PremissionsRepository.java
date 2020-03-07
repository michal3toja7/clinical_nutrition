package pl.michal.clinical_nutrition.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.auth.entity.Premissions;
import pl.michal.clinical_nutrition.auth.entity.User;

import java.util.List;


@Repository
public interface PremissionsRepository extends JpaRepository<Premissions, Long> {
    List<Premissions> findByPremissionsPKUser(User user);
    List<Premissions> findByPremissionsPKUserAndPremissionsPKJosAndCzyAktywny(User user, Jos jos, boolean czyAktywny);
}
