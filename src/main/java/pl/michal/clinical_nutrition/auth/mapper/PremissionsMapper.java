package pl.michal.clinical_nutrition.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.auth.dto.PremissionsDTO;
import pl.michal.clinical_nutrition.auth.entity.Premissions;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PremissionsMapper {

    @Mapping(source = "premissionsPK.josID",target = "josID")
    @Mapping(source = "premissionsPK.uprawnienieID",target = "uprawnienieID")
    @Mapping(source = "premissionsPK.urzytkownikID",target = "urzytkownikID")
    PremissionsDTO toPremissionsDTO(Premissions premissions);

    List<PremissionsDTO> toPremissionsDTOs(List<Premissions> premissions);

    @Mapping(source = "josID",target = "premissionsPK.josID")
    @Mapping(source = "uprawnienieID",target = "premissionsPK.uprawnienieID")
    @Mapping(source = "urzytkownikID",target = "premissionsPK.urzytkownikID")
    Premissions toPremissions(PremissionsDTO premissionsDTO);
}
