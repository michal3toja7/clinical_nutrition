package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozDTO;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;

import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface ZamowieniePozMapper {


        @Mapping(source = "zamowienie.id", target = "zamowienieId")
        @Mapping(source = "preparat", target = "preparat")
        @ValueMapping(source = "ZamowieniePoz.SposobPodania.BOL", target = "ZamowieniePozDTO.SposobPodania.BOL")
        @ValueMapping(source = "ZamowieniePoz.SposobPodania.WLC", target = "ZamowieniePozDTO.SposobPodania.WLC")
        @ValueMapping(source = "ZamowieniePoz.SposobPodania.WLK", target = "ZamowieniePozDTO.SposobPodania.WLK")
        ZamowieniePozDTO toZamowieniePozDTO(ZamowieniePoz zamowieniePoz);
        List<ZamowieniePozDTO> toZamowieniePozDTOs(List<ZamowieniePoz> zamowieniePozs);
        @Mapping(source = "zamowienieId", target = "zamowienie.id")
        @Mapping(source = "preparat", target = "preparat")
        @ValueMapping(source = "ZamowieniePozDTO.SposobPodania.BOL", target = "ZamowieniePoz.SposobPodania.BOL")
        @ValueMapping(source = "ZamowieniePozDTO.SposobPodania.WLC", target = "ZamowieniePoz.SposobPodania.WLC")
        @ValueMapping(source = "ZamowieniePozDTO.SposobPodania.WLK", target = "ZamowieniePoz.SposobPodania.WLK")
        ZamowieniePoz toZamowieniePoz(ZamowieniePozDTO zamowieniePozDTO);

}
