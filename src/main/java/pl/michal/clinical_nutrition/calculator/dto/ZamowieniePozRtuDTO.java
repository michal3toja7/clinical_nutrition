package pl.michal.clinical_nutrition.calculator.dto;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;

import javax.persistence.*;
import java.util.Date;

@Data

public class ZamowieniePozRtuDTO {
    private long id;
    private long zamowienieId;
    private TypZywienia typZywienia;
    private WorekPreparat worekPreparat;
    private double czasWlewu;
    private String uwagi;

    public enum TypZywienia{CAL, CZE, IMM}


}
