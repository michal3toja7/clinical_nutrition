package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.PacjentDTO;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.mapper.PacjentMapper;
import pl.michal.clinical_nutrition.calculator.service.PacjentService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/pacjent")
public class PacjentController {
    private final PacjentService pacjentService;
    private final PacjentMapper pacjentMapper;

    @GetMapping

    @PreAuthorize("hasAuthority('Premission1005') or hasRole('ADMIN')")

    public ResponseEntity<List<PacjentDTO>> findAll() {
        return ResponseEntity.ok(pacjentMapper.toPacjentDTOs(pacjentService.findAll()));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Premission1006') or hasRole('ADMIN')")
    public ResponseEntity<PacjentDTO> create(@RequestBody PacjentDTO pacjentDTO) {
        pacjentService.save(pacjentMapper.toPacjent(pacjentDTO));


        return ResponseEntity.status(HttpStatus.CREATED).body(pacjentDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1005') or hasRole('ADMIN')")
    public ResponseEntity<PacjentDTO> findById(@PathVariable Long id) {
        Optional<Pacjent> pacjent = pacjentService.findById(id);

        return ResponseEntity.ok(pacjentMapper.toPacjentDTO((pacjent.get())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1007') or hasRole('ADMIN')")
    public ResponseEntity<PacjentDTO> update(@PathVariable Long id, @RequestBody PacjentDTO pacjentDTO) {
        Pacjent pacjent = pacjentMapper.toPacjent(pacjentDTO);
        pacjent.setId(id);

        pacjentService.save(pacjent);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pacjentDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1007') or hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        pacjentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
