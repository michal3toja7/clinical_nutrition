package pl.michal.clinical_nutrition.calculator.dto;


import lombok.Data;
import pl.michal.clinical_nutrition.admin.dto.JosDTO;


import java.util.Date;

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
