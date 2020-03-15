package pl.michal.clinical_nutrition.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name = "uzytkownicy", schema = "app")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (unique = true, nullable = false)
    private String username;
    @Column (nullable = false)
    @JsonIgnore
    private String password;
    @Column
    @CreationTimestamp
    private Date created_on;
    @Column
    @UpdateTimestamp
    private Date updated_on;
    @Column (nullable = false)
    private String imiona;
    @Column (nullable = false)
    private String nazwisko;
    @Column (nullable = false)
    @Type(type = "yes_no")
    private boolean administrator;
    @Column
    private String rodzaj_personelu;
    @Column
    private long pesel;
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