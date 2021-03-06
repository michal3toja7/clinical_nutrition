package pl.michal.clinical_nutrition.calculator.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "zamowienie_poz_RTU", schema = "app")
public class ZamowieniePozRtu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Zamowienie zamowienie;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private TypZywienia typZywienia;
    @OneToOne
    private WorekPreparat worekPreparat;
    @Column(nullable = false)
    private double czasWlewu;
    @Column
    private String uwagi;


    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;

    public enum TypZywienia{CAL, CZE, IMM}





}
