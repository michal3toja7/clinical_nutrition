package pl.michal.clinical_nutrition.calculator.dto;


import lombok.Data;
import pl.michal.clinical_nutrition.calculator.entity.Dodatek;

@Data

public class ZamowieniePozDodatekDTO {

    private long id;
    private long zamowieniePozRtuId;
    private DodatekDTO dodatek;
    private double ilosc;


}
