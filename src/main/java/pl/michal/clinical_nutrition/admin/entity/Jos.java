package pl.michal.clinical_nutrition.admin.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data

@Entity
@Table (name = "JOS", schema = "app")
public class Jos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String kod;
    @Column(nullable = false)
    private String nazwa;
    @Column(nullable = false)
    private Rodzaj rodzaj;
    @Column (name = "czy_aktywny", nullable = false)
    @Type(type = "yes_no")
    private boolean czyAktywny;
    @Column (name = "nazwa_pieczatka")
    private String nazwaPieczatka;
    @Column (name = "adr_miasto")
    private String adresMiasto;
    @Column (name = "adr_ulica")
    private String adresUlica;
    @Column (name = "adr_ulica_numer")
    private String adresUlicaNumer;
    @Column (name = "adr_kod_pocztowy")
    private String adresKodPocztowy;
    @Column
    private String email;
    @Column
    private String telefon;


    public enum Rodzaj{APT,ODD,POR,PRA,IZB,INN}
}
