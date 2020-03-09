package pl.michal.clinical_nutrition.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.auth.config.JwtTokenUtil;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.auth.entity.Premissions;
import pl.michal.clinical_nutrition.auth.entity.PremissionsDefinition;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.auth.repository.JosRepository;
import pl.michal.clinical_nutrition.auth.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class JosService {
    @Autowired
    private final JosRepository josRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final UserService userService;
    @Autowired
    private final PremissionsDefinitionService premissionsDefinitionService;
    @Autowired
    private final PremissionsService premissionsService;

    public List<Jos> findAll() {
        return josRepository.findAll();
    }

    public Optional<Jos> findById(Long id) {
        return josRepository.findById(id);
    }

    public List<Jos> findByPremission(String token){
        User user = userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if(user.isAdministrator())
            return findAll();
        Optional <PremissionsDefinition> premissionsDefinition = premissionsDefinitionService.findById((long)1001);
        List<Premissions> premissions = premissionsService.findByUserAndPremission(user,premissionsDefinition.get());
        List<Jos> joss = new ArrayList<>();
            for(Premissions premission: premissions){
                joss.add(premission.getPremissionsPK().getJos());
            }
        return joss;
    }

    public Jos save(Jos jos) {
        return josRepository.save(jos);
    }

    public void deleteById(Long id) {
        josRepository.deleteById(id);
    }
}
