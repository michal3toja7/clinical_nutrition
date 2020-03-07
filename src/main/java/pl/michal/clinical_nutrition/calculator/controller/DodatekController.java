package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.DodatekDTO;
import pl.michal.clinical_nutrition.calculator.entity.Dodatek;
import pl.michal.clinical_nutrition.calculator.mapper.DodatekMapper;
import pl.michal.clinical_nutrition.calculator.mapper.DodatekMapper;
import pl.michal.clinical_nutrition.calculator.service.DodatekService;
import pl.michal.clinical_nutrition.calculator.service.DodatekService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/dodatek")
public class DodatekController {
    private final DodatekService dodatekService;
    private final DodatekMapper dodatekMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('Premission1002') or hasRole('ADMIN')")
    public ResponseEntity<List<DodatekDTO>> findAll() {
        return ResponseEntity.ok(dodatekMapper.toDodatekDTOs(dodatekService.findAll()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Premission1003') or hasRole('ADMIN')")
    public ResponseEntity<List<DodatekDTO>> create(@RequestBody List<DodatekDTO> dodatekDTOS) {
        for(DodatekDTO dodatekDTO : dodatekDTOS) {
            dodatekService.save(dodatekMapper.toDodatek(dodatekDTO));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(dodatekDTOS);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission100') or hasRole('ADMIN')")
    public ResponseEntity<DodatekDTO> findById(@PathVariable Long id) {
        Optional<Dodatek> dodatek = dodatekService.findById(id);

        return ResponseEntity.ok(dodatekMapper.toDodatekDTO((dodatek.get())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1004') or hasRole('ADMIN')")
    public ResponseEntity<DodatekDTO> update(@PathVariable Long id, @RequestBody DodatekDTO dodatekDTO) {
        Dodatek dodatek = dodatekMapper.toDodatek(dodatekDTO);
        dodatek.setId(id);

        dodatekService.save(dodatek);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dodatekDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1004') or hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        dodatekService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
