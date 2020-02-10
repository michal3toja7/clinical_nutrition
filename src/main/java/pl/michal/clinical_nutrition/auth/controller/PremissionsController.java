package pl.michal.clinical_nutrition.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.auth.dto.PremissionsDTO;
import pl.michal.clinical_nutrition.auth.dto.UserDTO;
import pl.michal.clinical_nutrition.auth.entity.Premissions;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.auth.mapper.PremissionsMapper;
import pl.michal.clinical_nutrition.auth.service.PremissionsService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/admin/premissions")
public class PremissionsController {
    private final PremissionsService premissionsService;
    private final PremissionsMapper premissionsMapper;

    @GetMapping("/{uzytkownikID}")
    public ResponseEntity<List<PremissionsDTO>> findByUzytkownikID(@PathVariable Long uzytkownikID){
        return ResponseEntity.ok(premissionsMapper.toPremissionsDTOs(premissionsService.findByUzytkownikID(uzytkownikID)));
    }

    @PostMapping
    public ResponseEntity<PremissionsDTO> create(@RequestBody PremissionsDTO premissionsDTO) {
        premissionsService.save(premissionsMapper.toPremissions(premissionsDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(premissionsDTO);
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<PremissionsDTO> findById(@PathVariable Long id) {
        Optional<Premissions> premissions = premissionsService.findById(id);

        return ResponseEntity.ok(premissionsMapper.toPremissionsDTO((premissions.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremissionsDTO> update(@PathVariable Long id, @RequestBody PremissionsDTO premissionsDTO) {
        Premissions premissions = premissionsMapper.toPremissions(premissionsDTO);
        premissions.setId(id);

        premissionsService.save(premissions);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(premissionsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        premissionsService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

 */
}
