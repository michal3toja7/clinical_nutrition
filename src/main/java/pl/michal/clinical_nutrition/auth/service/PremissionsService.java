package pl.michal.clinical_nutrition.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.auth.entity.Premissions;
import pl.michal.clinical_nutrition.auth.repository.PremissionsRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PremissionsService {

    @Autowired
    private final PremissionsRepository premissionsRepository;

    public List<Premissions> findAll() {
        return premissionsRepository.findAll();
    }

    public Optional<Premissions> findById(Long id) {
        return premissionsRepository.findById(id);
    }


    public Premissions save(Premissions premissions) {
        return premissionsRepository.save(premissions);
    }

    public void deleteById(Long id) {
        premissionsRepository.deleteById(id);
    }
}