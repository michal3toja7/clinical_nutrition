package pl.michal.clinical_nutrition.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.auth.entity.PremissionsDefinition;
import pl.michal.clinical_nutrition.auth.repository.PremissionsDefinitionRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PremissionsDefinitionService {
    @Autowired
    private final PremissionsDefinitionRepository premissionsDefinitionRepository;

    public List<PremissionsDefinition> findAll() {
        return premissionsDefinitionRepository.findAll();
    }

    public Optional<PremissionsDefinition> findById(Long id) {
        return premissionsDefinitionRepository.findById(id);
    }


    public PremissionsDefinition save(PremissionsDefinition premissionsDefinition) {
        return premissionsDefinitionRepository.save(premissionsDefinition);
    }

    public void deleteById(Long id) {
        premissionsDefinitionRepository.deleteById(id);
    }
}
