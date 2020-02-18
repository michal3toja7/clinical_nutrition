package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;
import pl.michal.clinical_nutrition.calculator.repository.PacjentRepository;
import pl.michal.clinical_nutrition.calculator.repository.PomiarRepository;
import pl.michal.clinical_nutrition.calculator.repository.ZamowieniePozRepository;
import pl.michal.clinical_nutrition.calculator.repository.ZamowienieRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class ZamowieniePozService {
    @Autowired
    private final ZamowieniePozRepository zamowieniePozRepository;
    @Autowired
    private final ZamowienieRepository zamowienieRepository;

    public List<ZamowieniePoz> findByIdZamowienia(long idZamowienia) {
        Optional<Zamowienie> zamowienie = zamowienieRepository.findById(idZamowienia);
        return zamowieniePozRepository.findByZamowienie(zamowienie.get());
    }

    public Optional<ZamowieniePoz> findById(Long id) {
        return zamowieniePozRepository.findById(id);
    }


    public ZamowieniePoz save(ZamowieniePoz zamowieniePoz) {
        return zamowieniePozRepository.save(zamowieniePoz);
    }

    public void deleteById(Long id) {
        zamowieniePozRepository.deleteById(id);
    }
}
