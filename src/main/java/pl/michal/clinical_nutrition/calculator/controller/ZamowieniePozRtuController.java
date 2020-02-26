package pl.michal.clinical_nutrition.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozRtuDTO;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozRtu;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowienieMapper;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowieniePozRtuMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowieniePozRtuService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/zamowienie/pozycjeRTU")
public class ZamowieniePozRtuController {
    private final ZamowieniePozRtuService zamowieniePozRtuService;
    private final ZamowieniePozRtuMapper zamowieniePozRtuMapper;


    @GetMapping("/zamowienie/{idZamowienia}")
    public ResponseEntity<List<ZamowieniePozRtuDTO>> findByIdZamowienia(@PathVariable Long idZamowienia) {
        return ResponseEntity.ok(zamowieniePozRtuMapper.toZamowieniePozRtuDTOs(zamowieniePozRtuService.findByIdZamowienia(idZamowienia)));
    }

    @PostMapping
    public ResponseEntity<ZamowieniePozRtuDTO> create(@RequestBody ZamowieniePozRtuDTO zamowieniePozRtuDTO) {
        ZamowieniePozRtu zamowieniePozRtu = zamowieniePozRtuMapper.toZamowieniePozRtu(zamowieniePozRtuDTO);
        zamowieniePozRtuService.save(zamowieniePozRtu);

        return ResponseEntity.status(HttpStatus.CREATED).body(zamowieniePozRtuMapper.toZamowieniePozRtuDTO(zamowieniePozRtu));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZamowieniePozRtuDTO> findById(@PathVariable Long id) {
        Optional<ZamowieniePozRtu> zamowieniePozRtu = zamowieniePozRtuService.findById(id);

        return ResponseEntity.ok(zamowieniePozRtuMapper.toZamowieniePozRtuDTO(zamowieniePozRtu.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZamowieniePozRtuDTO> update(@PathVariable Long id, @RequestBody ZamowieniePozRtuDTO zamowieniePozRtuDTO) {
        ZamowieniePozRtu zamowieniePozRtu = zamowieniePozRtuMapper.toZamowieniePozRtu(zamowieniePozRtuDTO);
        zamowieniePozRtu.setId(id);

        zamowieniePozRtuService.save(zamowieniePozRtu);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(zamowieniePozRtuDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        zamowieniePozRtuService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
