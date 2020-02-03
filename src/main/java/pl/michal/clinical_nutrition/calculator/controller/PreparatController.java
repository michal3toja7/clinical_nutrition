package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.mapper.PreparatMapper;
import pl.michal.clinical_nutrition.calculator.service.PreparatService;
import pl.michal.clinical_nutrition.calculator.dto.PreparatDTO;
import pl.michal.clinical_nutrition.calculator.entity.Preparat;


import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/preparat")
public class PreparatController {
    private final PreparatService preparatService;
    private final PreparatMapper preparatMapper;

    @GetMapping
    public ResponseEntity<List<PreparatDTO>> findAll() {
        return ResponseEntity.ok(preparatMapper.toPreparatDTOs(preparatService.findAll()));
    }

    @PostMapping
    public ResponseEntity<PreparatDTO> create(@RequestBody PreparatDTO preparatDTO) {
        preparatService.save(preparatMapper.toPreparat(preparatDTO));



        return ResponseEntity.status(HttpStatus.CREATED).body(preparatDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreparatDTO> findById(@PathVariable Long id) {
        Optional<Preparat> preparat = preparatService.findById(id);

        return ResponseEntity.ok(preparatMapper.toPreparatDTO((preparat.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreparatDTO> update(@PathVariable Long id, @RequestBody PreparatDTO preparatDTO) {
        Preparat preparat = preparatMapper.toPreparat(preparatDTO);
        preparat.setId(id);

        preparatService.save(preparat);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(preparatDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        preparatService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}