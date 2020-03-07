package pl.michal.clinical_nutrition.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.auth.dto.PremissionsDTO;
import pl.michal.clinical_nutrition.auth.entity.Premissions;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PremissionsMapper {

    @Mapping(source = "premissionsPK.jos.id",target = "josID")
    @Mapping(source = "premissionsPK.premissionsDefinition.id",target = "uprawnienieID")
    @Mapping(source = "premissionsPK.user.id",target = "uzytkownikID")
    PremissionsDTO toPremissionsDTO(Premissions premissions);

    List<PremissionsDTO> toPremissionsDTOs(List<Premissions> premissions);

    @Mapping(source = "josID",target = "premissionsPK.jos.id")
    @Mapping(source = "uprawnienieID",target = "premissionsPK.premissionsDefinition.id")
    @Mapping(source = "uzytkownikID",target = "premissionsPK.user.id")
    Premissions toPremissions(PremissionsDTO premissionsDTO);
}
