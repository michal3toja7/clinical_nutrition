package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.ZamowienieDTO;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowienieMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowienieService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/zamowienie")
public class ZamowienieController {
    private final ZamowienieService zamowienieService;
    private final ZamowienieMapper zamowienieMapper;

    @GetMapping
    public ResponseEntity<List<ZamowienieDTO>> findAll() {
        return ResponseEntity.ok(zamowienieMapper.toZamowienieDTOs(zamowienieService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ZamowienieDTO> create(@RequestBody ZamowienieDTO zamowienieDTO) {
        Zamowienie zamowienie = zamowienieMapper.toZamowienie(zamowienieDTO);
        zamowienieService.save(zamowienie);

        return ResponseEntity.status(HttpStatus.CREATED).body(zamowienieMapper.toZamowienieDTO(zamowienie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZamowienieDTO> findById(@PathVariable Long id) {
        Optional<Zamowienie> zamowienie = zamowienieService.findById(id);

        return ResponseEntity.ok(zamowienieMapper.toZamowienieDTO((zamowienie.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZamowienieDTO> update(@PathVariable Long id, @RequestBody ZamowienieDTO zamowienieDTO) {
        Zamowienie zamowienie = zamowienieMapper.toZamowienie(zamowienieDTO);
        zamowienie.setId(id);

        zamowienieService.save(zamowienie);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(zamowienieDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        zamowienieService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
