package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.repository.ZamowienieRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ZamowienieService {

    @Autowired
    private final ZamowienieRepository zamowienieRepository;

    public List<Zamowienie> findAll() {
        return zamowienieRepository.findAll();
    }

    public Optional<Zamowienie> findById(Long id) {
        return zamowienieRepository.findById(id);
    }


    public Zamowienie save(Zamowienie zamowienie) {
        return zamowienieRepository.save(zamowienie);
    }

    public void deleteById(Long id) {
        zamowienieRepository.deleteById(id);
    }
}
