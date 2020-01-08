package pl.michal.clinical_nutrition.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.auth.dto.JosDTO;
import pl.michal.clinical_nutrition.auth.entity.Jos;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface JosMapper {
    JosDTO toJosDTO(Jos jos);
    List<JosDTO> toJosDTOs(List<Jos> joss);
    Jos toJos(JosDTO josDTO);
}
