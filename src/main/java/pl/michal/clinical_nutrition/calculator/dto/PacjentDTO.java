package pl.michal.clinical_nutrition.calculator.dto;

import lombok.Data;
import pl.michal.clinical_nutrition.calculator.entity.Adres;

import java.util.Date;


@Data
public class PacjentDTO {
    private long id;
    private String nazwisko;
    private String imiona;
    private long pesel;
    private String plec;
    private Date dataUrodzenia;
    private Adres adresZamieszkania;
    private boolean czyZyje;
}
