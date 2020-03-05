package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.PomiarDTO;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;
import pl.michal.clinical_nutrition.calculator.mapper.PomiarMapper;
import pl.michal.clinical_nutrition.calculator.service.PomiarService;


import java.util.List;
import java.util.Optional;



@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/pomiar")
public class PomiarController {
    private final PomiarService pomiarService;
    private final PomiarMapper pomiarMapper;

    //Według mojej koncepcji pomiar nie poinien istnieć bez pacjenta. Czyli nie intemlentuje pobierania
    //wszystkich pomiarów a pobranie wszystkich pomiarów dla pacjenta.
    @GetMapping("/pacjent/{idPacjenta}")
    public ResponseEntity<List<PomiarDTO>> findByIdPacjenta(@PathVariable Long idPacjenta) {
        return ResponseEntity.ok(pomiarMapper.toPomiarDTOs(pomiarService.findByIdPacjenta(idPacjenta)));
    }

    @PostMapping
    public ResponseEntity<PomiarDTO> create(@RequestBody PomiarDTO pomiarDTO) {
        pomiarService.save(pomiarMapper.toPomiar(pomiarDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(pomiarDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PomiarDTO> findById(@PathVariable Long id) {
        Optional<Pomiar> pomiar = pomiarService.findById(id);

        return ResponseEntity.ok(pomiarMapper.toPomiarDTO((pomiar.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PomiarDTO> update(@PathVariable Long id, @RequestBody PomiarDTO pomiarDTO) {
        Pomiar pomiar = pomiarMapper.toPomiar(pomiarDTO);
        pomiar.setId(id);

        pomiarService.save(pomiar);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pomiarDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        pomiarService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
