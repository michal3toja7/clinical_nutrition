package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.WorekPreparatDTO;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;
import pl.michal.clinical_nutrition.calculator.mapper.WorekPreparatMapper;
import pl.michal.clinical_nutrition.calculator.service.WorekPreparatService;


import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/worekPreparat")
public class WorekPreparatController {
    private final WorekPreparatService worekPreparatService;
    private final WorekPreparatMapper worekPreparatMapper;

    @GetMapping
    public ResponseEntity<List<WorekPreparatDTO>> findAll() {
        return ResponseEntity.ok(worekPreparatMapper.toWorekPreparatDTOs(worekPreparatService.findAll()));
    }

    @PostMapping
    public ResponseEntity<WorekPreparatDTO> create(@RequestBody WorekPreparatDTO worekPreparatDTO) {
        worekPreparatService.save(worekPreparatMapper.toWorekPreparat(worekPreparatDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(worekPreparatDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorekPreparatDTO> findById(@PathVariable Long id) {
        Optional<WorekPreparat> worekPreparat = worekPreparatService.findById(id);

        return ResponseEntity.ok(worekPreparatMapper.toWorekPreparatDTO((worekPreparat.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorekPreparatDTO> update(@PathVariable Long id, @RequestBody WorekPreparatDTO worekPreparatDTO) {
        WorekPreparat worekPreparat = worekPreparatMapper.toWorekPreparat(worekPreparatDTO);
        worekPreparat.setId(id);

        worekPreparatService.save(worekPreparat);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(worekPreparatDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        worekPreparatService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
