package pl.michal.clinical_nutrition.auth.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.auth.dto.JosDTO;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.auth.mapper.JosMapper;
import pl.michal.clinical_nutrition.auth.service.JosService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/admin/jos")
public class JosController {
    private final JosService josService;
    private final JosMapper josMapper;

    @GetMapping
    public ResponseEntity<List<JosDTO>> findAll() {
        return ResponseEntity.ok(josMapper.toJosDTOs(josService.findAll()));
    }

    @PostMapping
    public ResponseEntity<JosDTO> create(@RequestBody JosDTO josDTO) {
        josService.save(josMapper.toJos(josDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(josDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JosDTO> findById(@PathVariable Long id) {
        Optional<Jos> jos = josService.findById(id);

        return ResponseEntity.ok(josMapper.toJosDTO((jos.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JosDTO> update(@PathVariable Long id, @RequestBody JosDTO josDTO) {
        Jos jos = josMapper.toJos(josDTO);
        jos.setId(id);

        josService.save(jos);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(josDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        josService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}