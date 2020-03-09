package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.auth.dto.JosDTO;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.auth.mapper.JosMapper;
import pl.michal.clinical_nutrition.calculator.dto.ZamowienieDTO;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowienieMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowienieService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user/zamowienie")
public class ZamowienieController {
    private final ZamowienieService zamowienieService;
    private final ZamowienieMapper zamowienieMapper;
    private final JosMapper josMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('Premission1017') or hasRole('ADMIN')")
    public ResponseEntity<List<ZamowienieDTO>> findAll() {
        return ResponseEntity.ok(zamowienieMapper.toZamowienieDTOs(zamowienieService.findAll()));
    }

    @PostMapping("/jos")
    @PreAuthorize("hasAuthority('Premission1017') or hasRole('ADMIN')")
    public ResponseEntity<List<ZamowienieDTO>> findByJos(@RequestBody JosDTO josDTO) {
        Jos jos = josMapper.toJos(josDTO);

        return ResponseEntity.ok(zamowienieMapper.toZamowienieDTOs(zamowienieService.findByJos(jos)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Premission1018') or hasRole('ADMIN')")
    public ResponseEntity<ZamowienieDTO> create(@RequestBody ZamowienieDTO zamowienieDTO) {
        Zamowienie zamowienie = zamowienieMapper.toZamowienie(zamowienieDTO);
        zamowienieService.save(zamowienie);

        return ResponseEntity.status(HttpStatus.CREATED).body(zamowienieMapper.toZamowienieDTO(zamowienie));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1017') or hasRole('ADMIN')")
    public ResponseEntity<ZamowienieDTO> findById(@PathVariable Long id) {
        Optional<Zamowienie> zamowienie = zamowienieService.findById(id);

        return ResponseEntity.ok(zamowienieMapper.toZamowienieDTO((zamowienie.get())));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1019') or hasRole('ADMIN')")
    public ResponseEntity<ZamowienieDTO> update(@PathVariable Long id, @RequestBody ZamowienieDTO zamowienieDTO) {
        Zamowienie zamowienie = zamowienieMapper.toZamowienie(zamowienieDTO);
        zamowienie.setId(id);

        zamowienieService.save(zamowienie);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(zamowienieDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Premission1019') or hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        zamowienieService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
