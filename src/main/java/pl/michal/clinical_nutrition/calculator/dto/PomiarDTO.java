package pl.michal.clinical_nutrition.calculator.dto;

import lombok.Data;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;

import java.util.Date;

@Data
public class PomiarDTO {
    private long id;
    private long idPacjenta;
    private double waga;
    private double wzrost;
    private double temperatura;
    private Aktywnosc aktywnosc;
    private StanChorego stanChorego;
    private Date dataPomiaru;



    public enum Aktywnosc{LEZY, CHODZI_PRZY_LOZKU, PELNA_AKTYWNOSC}
    public enum StanChorego{NORMA, SREDNIO_CIEZKI, CIEZKI}
}
