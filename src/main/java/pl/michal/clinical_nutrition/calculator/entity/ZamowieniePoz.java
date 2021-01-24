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
@Table(name = "zamowienie_poz", schema = "app")
public class ZamowieniePoz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Zamowienie zamowienie;
    @OneToOne
    private Preparat preparat;
    @Column(nullable = false)
    private double objetosc;
    @Column(nullable = false)
    private double coIleH;
    @Column
    private double czasWlewu;
    @Column
    @Enumerated(EnumType.STRING)
    private SposobPodania sposobPodania;
    @Column
    private String uwagi;


    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;

    public enum SposobPodania{WLC, BOL, WLK}





}
