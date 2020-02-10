package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.calculator.dto.PacjentDTO;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PacjentMapper {
    @ValueMapping(source = "PacjentDTO.Plec.K", target = "Pacjent.Plec.K")
    @ValueMapping(source = "PacjentDTO.Plec.M", target = "Pacjent.Plec.M")
    PacjentDTO toPacjentDTO(Pacjent pacjent);
    List<PacjentDTO> toPacjentDTOs(List<Pacjent> pacjents);
    @ValueMapping(source = "Pacjent.Plec.K", target = "PacjentDTO.Plec.K")
    @ValueMapping(source = "Pacjent.Plec.M", target = "PacjentDTO.Plec.M")
    Pacjent toPacjent(PacjentDTO pacjentDTO);

}
