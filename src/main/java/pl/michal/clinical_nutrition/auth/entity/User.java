package pl.michal.clinical_nutrition.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data

@Entity
@Table(name = "uzytkownicy", schema = "app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (unique = true)
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;
    @Column
    private String imiona;
    @Column
    private String nazwisko;
    @Column (nullable = false)
    @Type(type = "yes_no")
    private boolean administrator;
    @Column
    private String rodzaj_personelu;
    @Column
    private int pesel;
    @Column
    private String NPWZ;
    @Column
    private String stanowisko;
    @Column (nullable = false)
    @Type(type = "yes_no")
    private boolean zablokowany;
    @Column
    private Date wygasniecie_hasla;
    @Column
    private String komentarz;
}