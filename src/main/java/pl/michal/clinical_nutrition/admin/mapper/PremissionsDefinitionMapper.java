package pl.michal.clinical_nutrition.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.admin.dto.PremissionsDefinitionDTO;
import pl.michal.clinical_nutrition.admin.entity.PremissionsDefinition;


import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PremissionsDefinitionMapper {
    PremissionsDefinitionDTO toPremissionsDefinitionDTO(PremissionsDefinition premissionsDefinition);
    List<PremissionsDefinitionDTO> toPremissionsDefinitionDTOs(List<PremissionsDefinition> premissionsDefinitions);
    PremissionsDefinition toPremissionsDefinition(PremissionsDefinitionDTO premissionsDefinitionDTO);
}