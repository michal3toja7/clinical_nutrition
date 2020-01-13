package pl.michal.clinical_nutrition.calculator.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "pacjent", schema = "app")
public class Pacjent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nazwisko;
    @Column
    private String imiona;
    @Column
    private long pesel;
    @Column
    private String plec;
    @Column (name = "data_urodzenia")
    private Date dataUrodzenia;
    @Embedded
    private Adres adresZamieszkania;
    @Column (name = "czy_zyje", nullable = false)
    @Type(type = "yes_no")
    private boolean czyZyje;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;



}
