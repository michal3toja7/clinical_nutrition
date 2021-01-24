package pl.michal.clinical_nutrition.admin.entity;



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
@Table (name = "uprawnienia_uzytkownik", schema = "app")
public class Premissions {
    @EmbeddedId
    private PremissionsPK premissionsPK;
    @Column  (name = "czy_aktywny",nullable=false)
    @Type(type = "yes_no")
    private boolean czyAktywny;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;
}
