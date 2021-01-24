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
@Table(name = "pacjent", schema = "app")
public class Pacjent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nazwisko;
    @Column(nullable = false)
    private String imiona;
    @Column(nullable = false)
    private long pesel;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Plec plec;
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

    public enum Plec{K,M}

}
