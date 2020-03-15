package pl.michal.clinical_nutrition.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.admin.entity.Jos;
import pl.michal.clinical_nutrition.admin.entity.Premissions;
import pl.michal.clinical_nutrition.admin.entity.PremissionsDefinition;
import pl.michal.clinical_nutrition.admin.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface PremissionsRepository extends JpaRepository<Premissions, Long> {
    List<Premissions> findByPremissionsPKUser(User user);
    List<Premissions> findByPremissionsPKUserAndPremissionsPKJosAndCzyAktywny(User user, Jos jos, boolean czyAktywny);
    Optional<Premissions> findByPremissionsPKUserAndPremissionsPKJosAndPremissionsPKPremissionsDefinition(User user, Jos jos, PremissionsDefinition premissionsDefinition);
    List<Premissions> findByPremissionsPKUserAndPremissionsPKPremissionsDefinitionAndCzyAktywny(User user, PremissionsDefinition premissionsDefinition, boolean czyAktywny);
}
