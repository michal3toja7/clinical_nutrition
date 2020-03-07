package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.ZamowienieDTO;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozDTO;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowienieMapper;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowieniePozMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowieniePozService;
import pl.michal.clinical_nutrition.calculator.service.ZamowienieService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/zamowienie/pozycje")
public class ZamowieniePozController {
    private final ZamowieniePozService zamowieniePozService;
    private final ZamowieniePozMapper zamowieniePozMapper;
    private final ZamowienieMapper zamowienieMapper;


    @GetMapping("/zamowienie/{idZamowienia}")
    @PreAuthorize("hasAuthority('Premission1020') or hasRole('ADMIN')")
    public ResponseEntity<List<ZamowieniePozDTO>> findByIdZamowienia(@PathVariable Long idZamowienia) {
        return ResponseEntity.ok(zamowieniePozMapper.toZamowieniePozDTOs(zamowieniePozService.findByIdZamowienia(idZamowienia)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Premission1021') or hasRole('ADMIN')")
    public ResponseEntity<ZamowieniePozDTO> create(@RequestBody ZamowieniePozDTO zamowieniePozDTO) {
        ZamowieniePoz zamowieniePoz = zamowieniePozMapper.toZamowieniePoz(zamowieniePozDTO);
        zamowieniePozService.save(zamowieniePoz);

        return ResponseEntity.status(HttpStatus.CREATED).body(zamowieniePozMapper.toZamowieniePozDTO(zamowieniePoz));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1020') or hasRole('ADMIN')")
    public ResponseEntity<ZamowieniePozDTO> findById(@PathVariable Long id) {
        Optional<ZamowieniePoz> zamowieniePoz = zamowieniePozService.findById(id);

        return ResponseEntity.ok(zamowieniePozMapper.toZamowieniePozDTO(zamowieniePoz.get()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1022') or hasRole('ADMIN')")
    public ResponseEntity<ZamowieniePozDTO> update(@PathVariable Long id, @RequestBody ZamowieniePozDTO zamowieniePozDTO) {
        ZamowieniePoz zamowieniePoz = zamowieniePozMapper.toZamowieniePoz(zamowieniePozDTO);
        zamowieniePoz.setId(id);

        zamowieniePozService.save(zamowieniePoz);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(zamowieniePozDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1022') or hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        zamowieniePozService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
