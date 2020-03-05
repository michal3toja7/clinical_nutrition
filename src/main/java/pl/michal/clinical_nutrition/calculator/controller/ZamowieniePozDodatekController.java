package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozDodatekDTO;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozDodatek;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowienieMapper;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowieniePozDodatekMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowieniePozDodatekService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/zamowienie/pozycje/dodatek")
public class ZamowieniePozDodatekController {
    private final ZamowieniePozDodatekService zamowieniePozDodatekService;
    private final ZamowieniePozDodatekMapper zamowieniePozDodatekMapper;


    @GetMapping("/pozycja/{idZamowieniePozRtu}")
    public ResponseEntity<List<ZamowieniePozDodatekDTO>> findByIdZamowieniaPozRtu(@PathVariable Long idZamowieniePozRtu) {
        return ResponseEntity.ok(zamowieniePozDodatekMapper.toZamowieniePozDodatekDTOs(zamowieniePozDodatekService.findByIdZamowieniaPozRtu(idZamowieniePozRtu)));
    }

    @PostMapping
    public ResponseEntity<ZamowieniePozDodatekDTO> create(@RequestBody ZamowieniePozDodatekDTO zamowieniePozDodatekDTO) {
        ZamowieniePozDodatek zamowieniePozDodatek = zamowieniePozDodatekMapper.toZamowieniePozDodatek(zamowieniePozDodatekDTO);
        zamowieniePozDodatekService.save(zamowieniePozDodatek);

        return ResponseEntity.status(HttpStatus.CREATED).body(zamowieniePozDodatekMapper.toZamowieniePozDodatekDTO(zamowieniePozDodatek));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZamowieniePozDodatekDTO> findById(@PathVariable Long id) {
        Optional<ZamowieniePozDodatek> zamowieniePozDodatek = zamowieniePozDodatekService.findById(id);

        return ResponseEntity.ok(zamowieniePozDodatekMapper.toZamowieniePozDodatekDTO(zamowieniePozDodatek.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZamowieniePozDodatekDTO> update(@PathVariable Long id, @RequestBody ZamowieniePozDodatekDTO zamowieniePozDodatekDTO) {
        ZamowieniePozDodatek zamowieniePozDodatek = zamowieniePozDodatekMapper.toZamowieniePozDodatek(zamowieniePozDodatekDTO);
        zamowieniePozDodatek.setId(id);

        zamowieniePozDodatekService.save(zamowieniePozDodatek);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(zamowieniePozDodatekDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        zamowieniePozDodatekService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
