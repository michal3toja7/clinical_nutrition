package pl.michal.clinical_nutrition.calculator.dto;

import lombok.Data;
import pl.michal.clinical_nutrition.calculator.entity.Preparat;
@Data
public class PreparatDTO {
    private String nazwa;
    private Typ typ; //Typy: DOJELITOWY 'DOJ', DOUSTNIE 'DOU'
    private String opis;
    private double wartoscEnergetyczna; //kcal/ 1ml
    private double bialko; //"białko [g/100ml]"
    private double weglowodany; //"węglowodany [g/100ml]")
    private double tluszcz; //"Ttłuszcze [g/100ml]"
    private double blonnik; //błonnik [g/100ml]"
    private double osmolarnosc; //osmolarność [mOsm/l]
    private double sod; //Na [mg/100ml]
    private double potas; //K [mg/100ml]
    private double chlor; //Cl [mg/100ml]
    private double wapn; //Ca [mg/100ml]
    private double magnez; //Mg [mg/100ml]
    private double fosfor; //P [mg/100ml]
    private double zelazo; //Fe [mg/100ml]
    private double cynk; //Zn [mg/100ml]
    private boolean czyAktywny;


    public enum Typ{DOJ,DOU}
}
