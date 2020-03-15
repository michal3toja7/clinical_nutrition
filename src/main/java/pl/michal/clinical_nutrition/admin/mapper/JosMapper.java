package pl.michal.clinical_nutrition.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.admin.dto.JosDTO;
import pl.michal.clinical_nutrition.admin.entity.Jos;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface JosMapper {
    @ValueMapping(source = "JosDTO.Rodzaj.APT", target = "Jos.Rodzaj.APT")
    @ValueMapping(source = "JosDTO.Rodzaj.ODD", target = "Jos.Rodzaj.ODD")
    @ValueMapping(source = "JosDTO.Rodzaj.POR", target = "Jos.Rodzaj.POR")
    @ValueMapping(source = "JosDTO.Rodzaj.PRA", target = "Jos.Rodzaj.PRA")
    @ValueMapping(source = "JosDTO.Rodzaj.IZB", target = "Jos.Rodzaj.IZB")
    @ValueMapping(source = "JosDTO.Rodzaj.INN", target = "Jos.Rodzaj.INN")
    JosDTO toJosDTO(Jos jos);
    List<JosDTO> toJosDTOs(List<Jos> joss);
    @ValueMapping(source = "Jos.Rodzaj.APT", target = "JosDTO.Rodzaj.APT")
    @ValueMapping(source = "Jos.Rodzaj.ODD", target = "JosDTO.Rodzaj.ODD")
    @ValueMapping(source = "Jos.Rodzaj.POR", target = "JosDTO.Rodzaj.POR")
    @ValueMapping(source = "Jos.Rodzaj.PRA", target = "JosDTO.Rodzaj.PRA")
    @ValueMapping(source = "Jos.Rodzaj.IZB", target = "JosDTO.Rodzaj.IZB")
    @ValueMapping(source = "Jos.Rodzaj.INN", target = "JosDTO.Rodzaj.INN")
    Jos toJos(JosDTO josDTO);
}
