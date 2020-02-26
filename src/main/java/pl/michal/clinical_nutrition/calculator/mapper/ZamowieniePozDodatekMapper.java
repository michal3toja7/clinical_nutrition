package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozDodatekDTO;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozDodatek;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
    public interface ZamowieniePozDodatekMapper {
        @Mapping(source = "zamowieniePozRtu.id", target = "zamowieniePozRtuId")
        @Mapping(source = "dodatek", target = "dodatek")
        ZamowieniePozDodatekDTO toZamowieniePozDodatekDTO(ZamowieniePozDodatek zamowieniePozZamowieniePozDodatek);
        List<ZamowieniePozDodatekDTO> toZamowieniePozDodatekDTOs(List<ZamowieniePozDodatek> zamowieniePozDodateks);
        @Mapping(source = "zamowieniePozRtuId", target = "zamowieniePozRtu.id")
        @Mapping(source = "dodatek", target = "dodatek")
        ZamowieniePozDodatek toZamowieniePozDodatek(ZamowieniePozDodatekDTO zamowieniePozZamowieniePozDodatekDTO);
    }

