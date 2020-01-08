package pl.michal.clinical_nutrition.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.clinical_nutrition.auth.entity.Jos;


@Repository
public interface JosRepository extends JpaRepository<Jos, Long> {
}
