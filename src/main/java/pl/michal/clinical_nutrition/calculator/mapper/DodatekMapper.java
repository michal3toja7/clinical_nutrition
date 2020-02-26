package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.calculator.dto.DodatekDTO;
import pl.michal.clinical_nutrition.calculator.entity.Dodatek;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
    public interface DodatekMapper {
        DodatekDTO toDodatekDTO(Dodatek dodatek);
        List<DodatekDTO> toDodatekDTOs(List<Dodatek> Dodatki);
        Dodatek toDodatek(DodatekDTO dodatekDTO);
    }

