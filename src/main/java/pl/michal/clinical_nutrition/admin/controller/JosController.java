package pl.michal.clinical_nutrition.admin.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.admin.dto.JosDTO;
import pl.michal.clinical_nutrition.admin.entity.Jos;
import pl.michal.clinical_nutrition.admin.mapper.JosMapper;
import pl.michal.clinical_nutrition.admin.service.JosService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/jos")
public class JosController {
    private final JosService josService;
    private final JosMapper josMapper;

    @GetMapping
    public ResponseEntity<List<JosDTO>> findAll() {
        return ResponseEntity.ok(josMapper.toJosDTOs(josService.findAll()));
    }

    @GetMapping("/premission")
    public ResponseEntity<List<JosDTO>> findPremission (@RequestHeader("Authorization") String token) throws Exception {
        token=token.substring(7); //ucinam Bearer
        //josService.test(token);


        return ResponseEntity.ok(josMapper.toJosDTOs(josService.findByPremission(token)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JosDTO> update(@PathVariable Long id, @RequestBody JosDTO josDTO) {
        Jos jos = josMapper.toJos(josDTO);
        jos.setId(id);

        josService.save(jos);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(josDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        josService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}