package pl.michal.clinical_nutrition.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.admin.entity.PremissionsDefinition;


@Repository
public interface PremissionsDefinitionRepository extends JpaRepository<PremissionsDefinition, Long> {
}
