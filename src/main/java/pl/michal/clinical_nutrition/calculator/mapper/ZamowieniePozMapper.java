package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozDTO;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;

import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface ZamowieniePozMapper {
        ZamowieniePozDTO toZamowieniePozDTO(ZamowieniePoz zamowieniePoz);
        List<ZamowieniePozDTO> toZamowieniePozDTOs(List<ZamowieniePoz> zamowieniePozs);
        ZamowieniePoz toZamowieniePoz(ZamowieniePozDTO zamowieniePozDTO);

}
