package pl.michal.clinical_nutrition.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.admin.dto.PremissionsDefinitionDTO;
import pl.michal.clinical_nutrition.admin.entity.PremissionsDefinition;
import pl.michal.clinical_nutrition.admin.mapper.PremissionsDefinitionMapper;
import pl.michal.clinical_nutrition.admin.service.PremissionsDefinitionService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/admin/premissionsdefinition")
public class PremissionsDefinitionController {

    private final PremissionsDefinitionService premissionsDefinitionService;
    private final PremissionsDefinitionMapper premissionsDefinitionMapper;

    @GetMapping
    public ResponseEntity<List<PremissionsDefinitionDTO>> findAll() {
        return ResponseEntity.ok(premissionsDefinitionMapper.toPremissionsDefinitionDTOs(premissionsDefinitionService.findAll()));
    }

    @PostMapping
    public ResponseEntity<PremissionsDefinitionDTO> create(@RequestBody PremissionsDefinitionDTO premissionsDefinitionDTO) {
        premissionsDefinitionService.save(premissionsDefinitionMapper.toPremissionsDefinition(premissionsDefinitionDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(premissionsDefinitionDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PremissionsDefinitionDTO> findById(@PathVariable Long id) {
        Optional<PremissionsDefinition> premissionsDefinition = premissionsDefinitionService.findById(id);

        return ResponseEntity.ok(premissionsDefinitionMapper.toPremissionsDefinitionDTO((premissionsDefinition.get())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremissionsDefinitionDTO> update(@PathVariable Long id, @RequestBody PremissionsDefinitionDTO premissionsDefinitionDTO) {
        PremissionsDefinition premissionsDefinition = premissionsDefinitionMapper.toPremissionsDefinition(premissionsDefinitionDTO);
        premissionsDefinition.setId(id);

        premissionsDefinitionService.save(premissionsDefinition);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(premissionsDefinitionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        premissionsDefinitionService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
