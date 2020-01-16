package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.repository.PacjentRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class PacjentService {
    @Autowired
    private final PacjentRepository pacjentRepository;

    public List<Pacjent> findAll() {
        return pacjentRepository.findAll();
    }

    public Optional<Pacjent> findById(Long id) {
        return pacjentRepository.findById(id);
    }


    public Pacjent save(Pacjent pacjent) {
        return pacjentRepository.save(pacjent);
    }

    public void deleteById(Long id) {
        pacjentRepository.deleteById(id);
    }
}
