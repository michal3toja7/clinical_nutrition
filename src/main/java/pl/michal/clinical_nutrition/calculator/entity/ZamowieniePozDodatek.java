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
@Table(name = "zamowienie_poz_dodatek", schema = "app")
public class ZamowieniePozDodatek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private ZamowieniePozRtu zamowieniePozRtu;
    @OneToOne
    private Dodatek dodatek;
    @Column(nullable = false)
    private double ilosc;


    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;






}
