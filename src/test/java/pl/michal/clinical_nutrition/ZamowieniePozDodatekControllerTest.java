package pl.michal.clinical_nutrition;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.michal.clinical_nutrition.admin.entity.Jos;
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozDodatekDTO;
import pl.michal.clinical_nutrition.calculator.entity.*;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowieniePozDodatekMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowieniePozDodatekService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class ZamowieniePozDodatekControllerTest {
    private final String apiPath = "/api/user/zamowienie/pozycje/dodatek";
    private final String pathAuthenticate = "/api/authenticate";
    private final Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
    private final Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
    private final Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
    private final Zamowienie zamowienie = new Zamowienie(1, pacjent, pomiar,jos,jos,new Date(),new Date(),"Test", Zamowienie.Typ.DOJ, Zamowienie.Status.ZAP,null,new Date(), new Date());
    private final WorekPreparat worekPreparat = new WorekPreparat(1, "TestowyProducent", "TestowaNazwa",10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,"TestowySposob",25,26,27,28,29,30,31,32,33,34,35,36,37,38,true,new Date(), new Date());
    private final ZamowieniePozRtu zamowieniePozRtu = new ZamowieniePozRtu(1,zamowienie, ZamowieniePozRtu.TypZywienia.CAL,worekPreparat,4,"Uwagi",new Date(),new Date());
    private final Dodatek dodatek = new Dodatek(1, "TestowyDodatek", "TestowaKategoria",10,11,12,13,14,15,16,17,18,19,20,21,22,true,new Date(), new Date());

    @Autowired
    private ZamowieniePozDodatekMapper zamowieniePozDodatekMapper;

    @MockBean
    private ZamowieniePozDodatekService mockZamowieniePozDodatekService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<ZamowieniePozDodatekDTO> jacksonTester;// = new JacksonTester<ZamowieniePozDodatek>();



    String getToken() throws Exception {
        MvcResult result = (MvcResult) mockMvc.perform(post(pathAuthenticate)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"mbruzdowski\",\"password\":\"test\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        return result.getResponse().getContentAsString();
    }

    @Test
    public void getZamowienieDodatki() throws Exception {
        ZamowieniePozDodatek zamowieniePozDodatek = new ZamowieniePozDodatek(1,zamowieniePozRtu,dodatek,15,new Date(), new Date());
        when(mockZamowieniePozDodatekService.findByIdZamowieniaPozRtu(zamowieniePozRtu.getId())).thenReturn(Collections.singletonList(zamowieniePozDodatek));
        mockMvc.perform(get(apiPath + "/pozycja/{zamowieniePozDodatekId}", zamowieniePozRtu.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(zamowieniePozDodatek.getId()))
                .andExpect(jsonPath("$[0].zamowieniePozRtuId").value(zamowieniePozDodatek.getZamowieniePozRtu().getId()));
        verify(mockZamowieniePozDodatekService, times(1)).findByIdZamowieniaPozRtu(zamowieniePozRtu.getId());
        verifyNoMoreInteractions(mockZamowieniePozDodatekService);
    }

    @Test
    public void getZamowieniePozDodatek() throws Exception {
        ZamowieniePozDodatek zamowieniePozDodatek = new ZamowieniePozDodatek(1,zamowieniePozRtu,dodatek,15,new Date(), new Date());
        when(mockZamowieniePozDodatekService.findById(zamowieniePozDodatek.getId()))
                .thenReturn(Optional.of(zamowieniePozDodatek));
        mockMvc.perform(get(apiPath + "/{zamowieniePozDodatekId}", zamowieniePozDodatek.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(zamowieniePozDodatek.getId()))
                .andExpect(jsonPath("$.zamowieniePozRtuId").value(zamowieniePozDodatek.getZamowieniePozRtu().getId()));
        verify(mockZamowieniePozDodatekService, times(1)).findById(zamowieniePozDodatek.getId());
        verifyNoMoreInteractions(mockZamowieniePozDodatekService);
    }
    @Test
    public void createZamowieniePozDodatek() throws Exception {
        ZamowieniePozDodatek zamowieniePozDodatek = new ZamowieniePozDodatek(1,zamowieniePozRtu,dodatek,15,new Date(), new Date());
        String jsonZamowieniePozDodatek = jacksonTester.write(zamowieniePozDodatekMapper.toZamowieniePozDodatekDTO(zamowieniePozDodatek)).getJson();
        when(mockZamowieniePozDodatekService.save(zamowieniePozDodatek)).thenReturn(zamowieniePozDodatek);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonZamowieniePozDodatek).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(zamowieniePozDodatek.getId()))
                .andExpect(jsonPath("$.zamowieniePozRtuId").value(zamowieniePozDodatek.getZamowieniePozRtu().getId()));
        verify(mockZamowieniePozDodatekService, times(1)).save(any(ZamowieniePozDodatek.class));
        verifyNoMoreInteractions(mockZamowieniePozDodatekService);
    }

    @Test
    public void updateZamowieniePozDodatek() throws Exception {
        ZamowieniePozDodatek zamowieniePozDodatek = new ZamowieniePozDodatek(1,zamowieniePozRtu,dodatek,15,new Date(), new Date());
        String jsonZamowieniePozDodatek = jacksonTester.write(zamowieniePozDodatekMapper.toZamowieniePozDodatekDTO(zamowieniePozDodatek)).getJson();
        when(mockZamowieniePozDodatekService.findById(zamowieniePozDodatek.getId())).thenReturn(Optional.of(zamowieniePozDodatek));
        when(mockZamowieniePozDodatekService.save(any(ZamowieniePozDodatek.class))).thenReturn(zamowieniePozDodatek);
        mockMvc.perform(put(apiPath + "/{zamowieniePozDodatekId}", zamowieniePozDodatek.getId()).header("Authorization", "Bearer " + getToken()).content(jsonZamowieniePozDodatek)
                .content(jsonZamowieniePozDodatek)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockZamowieniePozDodatekService, times(1)).save(any(ZamowieniePozDodatek.class));
        verifyNoMoreInteractions(mockZamowieniePozDodatekService);
    }


    @BeforeEach
    public void before(TestInfo testInfo) {
        System.out.printf("-- METODA -> %s%n", testInfo.getTestMethod().get().getName());
        ObjectMapper mapper = new ObjectMapper(); // ustawienie formatu daty i czasu w komunikatach
        mapper.registerModule(new JavaTimeModule()); // JSON-a dla zmiennych typu LocalDate i LocalDateTime
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        JacksonTester.initFields(this, mapper);
    }
    @AfterEach
    public void after(TestInfo testInfo) {
        System.out.printf("<- KONIEC -- %s%n", testInfo.getTestMethod().get().getName());
    }
}