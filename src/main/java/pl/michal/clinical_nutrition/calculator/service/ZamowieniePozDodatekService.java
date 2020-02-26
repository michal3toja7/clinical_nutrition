package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozDodatek;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozRtu;
import pl.michal.clinical_nutrition.calculator.repository.ZamowieniePozDodatekRepository;
import pl.michal.clinical_nutrition.calculator.repository.ZamowieniePozRtuRepository;
import pl.michal.clinical_nutrition.calculator.repository.ZamowienieRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@Service
public class ZamowieniePozDodatekService {
    @Autowired
    private final ZamowieniePozDodatekRepository zamowieniePozDodatekRepository;
    @Autowired
    private final ZamowieniePozRtuRepository zamowieniePozRtuRepository;

    public List<ZamowieniePozDodatek> findByIdZamowieniaPozRtu(long idZamowienia) {
        Optional<ZamowieniePozRtu> zamowieniePozRtu = zamowieniePozRtuRepository.findById(idZamowienia);
        return zamowieniePozDodatekRepository.findByZamowieniePozRtu(zamowieniePozRtu.get());
    }

    public Optional<ZamowieniePozDodatek> findById(Long id) {
        return zamowieniePozDodatekRepository.findById(id);
    }


    public ZamowieniePozDodatek save(ZamowieniePozDodatek zamowieniePozDodatek) {
        return zamowieniePozDodatekRepository.save(zamowieniePozDodatek);
    }

    public void deleteById(Long id) {
        zamowieniePozDodatekRepository.deleteById(id);
    }
}
