package pl.michal.clinical_nutrition.calculator.dto;


import lombok.Data;
import pl.michal.clinical_nutrition.auth.dto.JosDTO;
import pl.michal.clinical_nutrition.auth.entity.Jos;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;

import pl.michal.clinical_nutrition.calculator.entity.ZamowieniePoz;

import java.util.Date;
import java.util.List;

@Data
public class ZamowienieDTO {
        private long id;
        private PacjentDTO pacjent;
        private PomiarDTO pomiar;
        private JosDTO josZamawiajacy;
        private JosDTO josRealizujacy;
        private Date dataNa;
        private Date dataZlecenia;
        private String rozpoznanie;
        private Typ typ;
        private Status status;




        public enum Status{ZAP,WYS,REA,ZRE,ANU}
        public enum Typ{DOJ,DOU,WOR}
}