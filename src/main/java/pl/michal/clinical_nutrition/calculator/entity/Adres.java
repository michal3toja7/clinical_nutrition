package pl.michal.clinical_nutrition.calculator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class Adres {
    private String miasto;
    private String kodPocztowy;
    private String ulica;
    private String nrDomu;
    private String nrMieszkania;
}
