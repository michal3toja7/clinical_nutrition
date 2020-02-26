package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Dodatek;
import pl.michal.clinical_nutrition.calculator.repository.DodatekRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class DodatekService {
    @Autowired
    private final DodatekRepository dodatekRepository;

    public List<Dodatek> findAll() {
        return dodatekRepository.findAll();
    }

    public Optional<Dodatek> findById(Long id) {
        return dodatekRepository.findById(id);
    }


    public Dodatek save(Dodatek dodatek) {
        return dodatekRepository.save(dodatek);
    }

    public void deleteById(Long id) {
        dodatekRepository.deleteById(id);
    }
}