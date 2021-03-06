package pl.michal.clinical_nutrition.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.admin.entity.Premissions;
import pl.michal.clinical_nutrition.admin.entity.PremissionsDefinition;
import pl.michal.clinical_nutrition.admin.entity.User;
import pl.michal.clinical_nutrition.admin.repository.PremissionsRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class PremissionsService {

    @Autowired
    private final PremissionsRepository premissionsRepository;
    @Autowired
    private final UserService userService;

    public List<Premissions> findAll() {
        return premissionsRepository.findAll();
    }

    public List<Premissions> findByUzytkownikID(Long uzytkownikID) {
        Optional <User> user = userService.findById(uzytkownikID);
        return premissionsRepository.findByPremissionsPKUser(user.get());
    }

    public List<Premissions> findByUser(User user) {
        return premissionsRepository.findByPremissionsPKUser(user);
    }

    public List<Premissions> findByUserAndPremission(User user, PremissionsDefinition premissionsDefinition) {
        return premissionsRepository.findByPremissionsPKUserAndPremissionsPKPremissionsDefinitionAndCzyAktywny(user, premissionsDefinition, true);
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