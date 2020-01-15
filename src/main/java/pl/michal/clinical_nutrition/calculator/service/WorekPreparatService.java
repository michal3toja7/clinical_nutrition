package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;
import pl.michal.clinical_nutrition.calculator.repository.WorekPreparatRepository;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorekPreparatService {
    @Autowired
    private final WorekPreparatRepository worekPreparatRepository;

    public List<WorekPreparat> findAll() {
        return worekPreparatRepository.findAll();
    }

    public Optional<WorekPreparat> findById(Long id) {
        return worekPreparatRepository.findById(id);
    }


    public WorekPreparat save(WorekPreparat worekPreparat) {
        return worekPreparatRepository.save(worekPreparat);
    }

    public void deleteById(Long id) {
        worekPreparatRepository.deleteById(id);
    }
}


