package pl.michal.clinical_nutrition.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.auth.repository.JosRepository;
import pl.michal.clinical_nutrition.auth.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class JosService {
    @Autowired
    private final JosRepository josRepository;

    public List<Jos> findAll() {
        return josRepository.findAll();
    }

    public Optional<Jos> findById(Long id) {
        return josRepository.findById(id);
    }


    public Jos save(Jos jos) {
        return josRepository.save(jos);
    }

    public void deleteById(Long id) {
        josRepository.deleteById(id);
    }
}
