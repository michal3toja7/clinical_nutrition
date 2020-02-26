package pl.michal.clinical_nutrition.calculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozRtu;
import pl.michal.clinical_nutrition.calculator.repository.ZamowieniePozRtuRepository;
import pl.michal.clinical_nutrition.calculator.repository.ZamowienieRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ZamowieniePozRtuService {
    @Autowired
    private final ZamowieniePozRtuRepository zamowieniePozRtuRepository;
    @Autowired
    private final ZamowienieRepository zamowienieRepository;

    public List<ZamowieniePozRtu> findByIdZamowienia(long idZamowienia) {
        Optional<Zamowienie> zamowienie = zamowienieRepository.findById(idZamowienia);
        return zamowieniePozRtuRepository.findByZamowienie(zamowienie.get());
    }

    public Optional<ZamowieniePozRtu> findById(Long id) {
        return zamowieniePozRtuRepository.findById(id);
    }


    public ZamowieniePozRtu save(ZamowieniePozRtu zamowieniePozRtu) {
        return zamowieniePozRtuRepository.save(zamowieniePozRtu);
    }

    public void deleteById(Long id) {
        zamowieniePozRtuRepository.deleteById(id);
    }
}