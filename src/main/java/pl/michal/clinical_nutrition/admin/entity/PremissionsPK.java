package pl.michal.clinical_nutrition.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class PremissionsPK implements Serializable {
    @Id
    @ManyToOne
    private User user;
    @Id
    @ManyToOne
    private Jos jos;
    @Id
    @ManyToOne
    private PremissionsDefinition premissionsDefinition;

}