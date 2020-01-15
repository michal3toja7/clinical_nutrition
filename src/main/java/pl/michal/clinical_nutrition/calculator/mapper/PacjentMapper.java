package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.calculator.dto.PacjentDTO;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PacjentMapper {
    PacjentDTO toPacjentDTO(Pacjent pacjent);
    List<PacjentDTO> toPacjentDTOs(List<Pacjent> pacjents);
    Pacjent toPacjent(PacjentDTO pacjentDTO);

}
