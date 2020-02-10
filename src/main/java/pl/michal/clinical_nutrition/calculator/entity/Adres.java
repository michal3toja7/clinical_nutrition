package pl.michal.clinical_nutrition.calculator.entity;

import lombok.Data;

import javax.persistence.Embeddable;


@Data

@Embeddable
public class Adres {
    private String miasto;
    private String kodPocztowy;
    private String ulica;
    private String nrDomu;
    private String nrMieszkania;
}
