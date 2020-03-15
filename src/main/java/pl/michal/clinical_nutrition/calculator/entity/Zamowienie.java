package pl.michal.clinical_nutrition.calculator.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.michal.clinical_nutrition.admin.entity.Jos;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data

@Entity
@Table(name = "zamowienie", schema = "app")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Pacjent pacjent;
    @ManyToOne
    private Pomiar pomiar;
    @ManyToOne
    private Jos josZamawiajacy;
    @ManyToOne
    private Jos josRealizujacy;
    @Column(nullable = false)
    private Date dataNa;
    @Column(nullable = false)
    private Date dataZlecenia;
    @Column(nullable = false)
    private String rozpoznanie;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Typ typ;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany
    private List<ZamowieniePoz> zamowieniePozs;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;



    public enum Status{ZAP,WYS,REA,ZRE,ANU}
    public enum Typ{DOJ,DOU,WOR}


}
