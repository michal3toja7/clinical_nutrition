package pl.michal.clinical_nutrition.calculator.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "preparat", schema = "app")
public class Preparat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nazwa;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Typ typ; //Typy: DOJELITOWY 'DOJ', DOUSTNIE 'DOU'
    @Column
    private String opis;
    @Column (name = "wartosc_energetyczna")
    private double wartoscEnergetyczna; //kcal/ 1ml
    @Column(nullable = false)
    private double bialko; //"białko [g/100ml]"
    @Column(nullable = false)
    private double weglowodany; //"węglowodany [g/100ml]")
    @Column(nullable = false)
    private double tluszcz; //"Ttłuszcze [g/100ml]"
    @Column(nullable = false)
    private double blonnik; //błonnik [g/100ml]"
    @Column(nullable = false)
    private double osmolarnosc; //osmolarność [mOsm/l]
    @Column(nullable = false)
    private double sod; //Na [mg/100ml]
    @Column(nullable = false)
    private double potas; //K [mg/100ml]
    @Column(nullable = false)
    private double chlor; //Cl [mg/100ml]
    @Column(nullable = false)
    private double wapn; //Ca [mg/100ml]
    @Column(nullable = false)
    private double magnez; //Mg [mg/100ml]
    @Column(nullable = false)
    private double fosfor; //P [mg/100ml]
    @Column(nullable = false)
    private double zelazo; //Fe [mg/100ml]
    @Column(nullable = false)
    private double cynk; //Zn [mg/100ml]
    @Column (name = "czy_aktywny", nullable = false)
    @Type(type = "yes_no")
    private boolean czyAktywny;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;

    public enum Typ{DOJ,DOU}
}
