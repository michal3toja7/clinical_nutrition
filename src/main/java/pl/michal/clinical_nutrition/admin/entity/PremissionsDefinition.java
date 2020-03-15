package pl.michal.clinical_nutrition.admin.entity;

import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "uprawnienia_definicja", schema = "app")
public class PremissionsDefinition{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
}
