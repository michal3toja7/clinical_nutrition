package pl.michal.clinical_nutrition.calculator.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.calculator.dto.PomiarDTO;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PomiarMapper {
    @ValueMapping(source = "PomiarDTO.Aktywnosc.LEZY", target = "Pomiar.Aktywnosc.LEZY")
    @ValueMapping(source = "PomiarDTO.Aktywnosc.CHODZI_PRZY_LOZKU", target = "Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU")
    @ValueMapping(source = "PomiarDTO.Aktywnosc.PELNA_AKTYWNOSC", target = "Pomiar.Aktywnosc.PELNA_AKTYWNOSC")
    @ValueMapping(source = "PomiarDTO.StanChorego.NORMA", target = "Pomiar.StanChorego.NORMA")
    @ValueMapping(source = "PomiarDTO.StanChorego.SREDNIO_CIEZKI", target = "Pomiar.StanChorego.SREDNIO_CIEZKI")
    @ValueMapping(source = "PomiarDTO.StanChorego.CIEZKI", target = "Pomiar.StanChorego.CIEZKI")
    PomiarDTO toPomiarDTO(Pomiar pomiar);
    List<PomiarDTO> toPomiarDTOs(List<Pomiar> pomiars);
    @ValueMapping(source = "Pomiar.Aktywnosc.LEZY", target = "PomiarDTO.Aktywnosc.LEZY")
    @ValueMapping(source = "Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU", target = "PomiarDTO.Aktywnosc.CHODZI_PRZY_LOZKU")
    @ValueMapping(source = "Pomiar.Aktywnosc.PELNA_AKTYWNOSC", target = "PomiarDTO.Aktywnosc.PELNA_AKTYWNOSC")
    @ValueMapping(source = "Pomiar.StanChorego.NORMA", target = "PomiarDTO.StanChorego.NORMA")
    @ValueMapping(source = "Pomiar.StanChorego.SREDNIO_CIEZKI", target = "PomiarDTO.StanChorego.SREDNIO_CIEZKI")
    @ValueMapping(source = "Pomiar.StanChorego.CIEZKI", target = "PomiarDTO.StanChorego.CIEZKI")
    Pomiar toPomiar(PomiarDTO pomiarDTO);
}
