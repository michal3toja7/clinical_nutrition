package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;
import pl.michal.clinical_nutrition.calculator.repository.PomiarRepository;


import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class PomiarService {
    @Autowired
    private final PomiarRepository pomiarRepository;

    public List<Pomiar> findByIdPacjenta(long idPacjenta) {
        return pomiarRepository.findByIdPacjenta(idPacjenta);
    }

    public Optional<Pomiar> findById(Long id) {
        return pomiarRepository.findById(id);
    }


    public Pomiar save(Pomiar pomiar) {
        return pomiarRepository.save(pomiar);
    }

    public void deleteById(Long id) {
        pomiarRepository.deleteById(id);
    }
}

