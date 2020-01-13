package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.calculator.dto.PreparatDTO;
import pl.michal.clinical_nutrition.calculator.entity.Preparat;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface PreparatMapper {
    @ValueMapping(source = "PreparatDTO.Typ.DOJ", target = "Preparat.Typ.DOJ")
    @ValueMapping(source = "PreparatDTO.Typ.DOU", target = "Preparat.Typ.DOU")
    PreparatDTO toPreparatDTO(Preparat preparat);
    List<PreparatDTO> toPreparatDTOs(List<Preparat> preparats);
    @ValueMapping(source = "Preparat.Typ.DOJ", target = "PreparatDTO.Typ.DOJ")
    @ValueMapping(source = "Preparat.Typ.DOU", target = "PreparatDTO.Typ.DOU")
    Preparat toPreparat(PreparatDTO preparatDTO);
}

