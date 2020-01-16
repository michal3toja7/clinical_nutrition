package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Preparat;
import pl.michal.clinical_nutrition.calculator.repository.PreparatRepository;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PreparatService {
    @Autowired
    private final PreparatRepository preparatRepository;

    public List<Preparat> findAll() {
        return preparatRepository.findAll();
    }

    public Optional<Preparat> findById(Long id) {
        return preparatRepository.findById(id);
    }


    public Preparat save(Preparat preparat) {
        return preparatRepository.save(preparat);
    }

    public void deleteById(Long id) {
        preparatRepository.deleteById(id);
    }
}
