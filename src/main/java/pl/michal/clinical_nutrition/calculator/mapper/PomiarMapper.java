package pl.michal.clinical_nutrition.calculator.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.calculator.dto.PomiarDTO;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PomiarMapper {
    PomiarDTO toPomiarDTO(Pomiar pomiar);
    List<PomiarDTO> toPomiarDTOs(List<Pomiar> pomiars);
    Pomiar toPomiar(PomiarDTO pomiarDTO);
}
