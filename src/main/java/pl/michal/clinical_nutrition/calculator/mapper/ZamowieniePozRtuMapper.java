package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozRtuDTO;
import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePozRtu;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
    public interface ZamowieniePozRtuMapper {
        @Mapping(source = "zamowienie.id", target = "zamowienieId")
        @Mapping(source = "worekPreparat", target = "worekPreparat")
        @ValueMapping(source = "ZamowieniePozRtu.TypZywienia.CAL", target = "ZamowieniePozRtuDTO.TypZywienia.CAL")
        @ValueMapping(source = "ZamowieniePozRtu.TypZywienia.CZE", target = "ZamowieniePozRtuDTO.TypZywienia.CZE")
        @ValueMapping(source = "ZamowieniePozRtu.TypZywienia.IMM", target = "ZamowieniePozRtuDTO.TypZywienia.IMM")
        ZamowieniePozRtuDTO toZamowieniePozRtuDTO(ZamowieniePozRtu zamowieniePozRtu);
        List<ZamowieniePozRtuDTO> toZamowieniePozRtuDTOs(List<ZamowieniePozRtu> zamowieniePozRtus);
        @Mapping(source = "zamowienieId", target = "zamowienie.id")
        @Mapping(source = "worekPreparat", target = "worekPreparat")
        @ValueMapping(source = "ZamowieniePozRtuDTO.TypZywienia.CAL", target = "ZamowieniePozRtu.TypZywienia.CAL")
        @ValueMapping(source = "ZamowieniePozRtuDTO.TypZywienia.CZE", target = "ZamowieniePozRtu.TypZywienia.CZE")
        @ValueMapping(source = "ZamowieniePozRtuDTO.TypZywienia.IMM", target = "ZamowieniePozRtu.TypZywienia.IMM")
        ZamowieniePozRtu toZamowieniePozRtu(ZamowieniePozRtuDTO zamowieniePozRtuDTO);
    }

