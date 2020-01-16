package pl.michal.clinical_nutrition.calculator.entity;


import lombok.Data;

import javax.persistence.*;

@Data

@Entity
@Table(name = "zlecenie", schema = "app")
public class Zlecenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
