package pl.michal.clinical_nutrition.calculator.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data

@Entity
@Table(name = "dodatki", schema = "app")
public class Dodatek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nazwa;
    @Column(nullable = false)
    private String kategoria;
    @Column
    private double potas;
    @Column
    private double fosfor;
    @Column
    private double sod;
    @Column
    private double chlor;
    @Column
    private double wapn;
    @Column
    private double magnez;
    @Column
    private double tluszcz;
    @Column
    private double aminokwasy;
    @Column
    private double wartoscEnergetyczna;
    @Column
    private double azot;
    @Column
    private double glukoza;
    @Column
    private double witB12;
    @Column
    private double awitB1;
    @Column(name = "czy_aktywny", nullable = false)
    @Type(type = "yes_no")
    private boolean czyAktywny;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;

}