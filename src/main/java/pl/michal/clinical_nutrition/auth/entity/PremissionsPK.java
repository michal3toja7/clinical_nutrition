package pl.michal.clinical_nutrition.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data

@Embeddable
public class PremissionsPK implements Serializable {
    @Id
    @Column(name = "uzytkownik_id", nullable=false)
    private long uzytkownikID;
    @Id
    @Column  (name = "jos_id", nullable=false)
    private long josID;
    @Id
    @Column  (name = "uprawnienie_id", nullable=false)
    private long uprawnienieID;

}