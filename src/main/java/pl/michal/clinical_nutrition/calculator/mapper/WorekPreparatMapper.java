package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.calculator.dto.WorekPreparatDTO;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface WorekPreparatMapper {
    WorekPreparatDTO toWorekPreparatDTO(WorekPreparat worekPreparat);
    List<WorekPreparatDTO> toWorekPreparatDTOs(List<WorekPreparat> worekPreparats);
    WorekPreparat toWorekPreparat(WorekPreparatDTO worekPreparatDTO);
}
