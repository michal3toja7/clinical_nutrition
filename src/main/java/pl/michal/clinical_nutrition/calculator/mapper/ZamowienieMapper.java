package pl.michal.clinical_nutrition.calculator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.calculator.dto.WorekPreparatDTO;
import pl.michal.clinical_nutrition.calculator.dto.ZamowienieDTO;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface ZamowienieMapper {

        @Mapping(source = "pacjent", target = "pacjent")
        @Mapping(source = "pomiar", target = "pomiar")
        @Mapping(source = "josZamawiajacy", target = "josZamawiajacy")
        @Mapping(source = "josRealizujacy", target = "josRealizujacy")
        ZamowienieDTO toZamowienieDTO(Zamowienie zamowienie);
        List<ZamowienieDTO> toZamowienieDTOs(List<Zamowienie> zamowienies);
        @Mapping(source = "pacjent", target = "pacjent")
        @Mapping(source = "pomiar", target = "pomiar")
        @Mapping(source = "josZamawiajacy", target = "josZamawiajacy")
        @Mapping(source = "josRealizujacy", target = "josRealizujacy")
        Zamowienie toZamowienie(ZamowienieDTO zamowienieDTO);

}
