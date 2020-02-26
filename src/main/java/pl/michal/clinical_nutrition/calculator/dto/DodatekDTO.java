package pl.michal.clinical_nutrition.calculator.dto;

import lombok.Data;

@Data

public class DodatekDTO {
    private long id;
    private String nazwa;
    private String kategoria;
    private double potas;
    private double fosfor;
    private double sod;
    private double chlor;
    private double wapn;
    private double magnez;
    private double tluszcz;
    private double aminokwasy;
    private double wartoscEnergetyczna;
    private double azot;
    private double glukoza;
    private double witB12;
    private double awitB1;
    private boolean czyAktywny;
}
