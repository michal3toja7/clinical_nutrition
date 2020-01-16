package pl.michal.clinical_nutrition.auth.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data

@Entity
@Table(name = "uprawnienia_definicja", schema = "app")
public class PremissionsDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
}
