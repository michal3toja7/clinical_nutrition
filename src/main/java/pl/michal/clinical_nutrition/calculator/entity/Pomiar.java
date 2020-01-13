package pl.michal.clinical_nutrition.calculator.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "pomiar", schema = "app")
public class Pomiar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long idPacjenta;
    @Column(nullable = false)
    private double waga;
    @Column(nullable = false)
    private double wzrost;
    @Column(nullable = false)
    private double temperatura;
    @Enumerated(EnumType.ORDINAL)
    private Aktywnosc aktywnosc;
    @Enumerated(EnumType.ORDINAL)
    private StanChorego stanChorego;
    @Column
    private Date dataPomiaru;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;



    public enum Aktywnosc{
        LEZY(1.0),
        CHODZI_PRZY_LOZKU(1.1),
        PELNA_AKTYWNOSC(1.2);
        private Double aktywnosc;

        private Aktywnosc (Double aktywnosc){
            this.aktywnosc=aktywnosc;
        }
        public Double getAktywnosc() {
            return aktywnosc;
        }
    }

    public enum StanChorego{
        NORMA(1.0),
        SREDNIO_CIEZKI(1.1),
        CIEZKI(1.2);;
        private Double stanChorego;

        private StanChorego (Double stanChorego){
            this.stanChorego =stanChorego;
        }
        public Double getStanChorego() {
            return stanChorego;
        }
    }

}
