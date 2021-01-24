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
import pl.michal.clinical_nutrition.calculator.dto.ZamowienieDTO;
import pl.michal.clinical_nutrition.calculator.entity.Adres;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;
import pl.michal.clinical_nutrition.calculator.entity.Zamowienie;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowienieMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowienieService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class ZamowienieControllerTest {
    private final String apiPath = "/api/user/zamowienie";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private ZamowienieMapper zamowienieMapper;

    @MockBean
    private ZamowienieService mockZamowienieService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<ZamowienieDTO> jacksonTester;// = new JacksonTester<Zamowienie>();



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
    public void getZamowienia() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        Zamowienie zamowienie = new Zamowienie(1, pacjent, pomiar,jos,jos,new Date(),new Date(),"Test", Zamowienie.Typ.DOJ, Zamowienie.Status.ZAP,null,new Date(), new Date());
        when(mockZamowienieService.findAll()).thenReturn(Collections.singletonList(zamowienie));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(zamowienie.getId()))
                .andExpect(jsonPath("$[0].rozpoznanie").value(zamowienie.getRozpoznanie()));
        verify(mockZamowienieService, times(1)).findAll();
        verifyNoMoreInteractions(mockZamowienieService);
    }

    @Test
    public void getZamowienie() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        Zamowienie zamowienie = new Zamowienie(1, pacjent, pomiar,jos,jos,new Date(),new Date(),"Test", Zamowienie.Typ.DOJ, Zamowienie.Status.ZAP,null,new Date(), new Date());
        when(mockZamowienieService.findById(zamowienie.getId()))
                .thenReturn(Optional.of(zamowienie));
        mockMvc.perform(get(apiPath + "/{zamowienieId}", zamowienie.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(zamowienie.getId()))
                .andExpect(jsonPath("$.rozpoznanie").value(zamowienie.getRozpoznanie()));
        verify(mockZamowienieService, times(1)).findById(zamowienie.getId());
        verifyNoMoreInteractions(mockZamowienieService);
    }
    @Test
    public void createZamowienie() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        Zamowienie zamowienie = new Zamowienie(1, pacjent, pomiar,jos,jos,new Date(),new Date(),"Test", Zamowienie.Typ.DOJ, Zamowienie.Status.ZAP,null,new Date(), new Date());
        String jsonZamowienie = jacksonTester.write(zamowienieMapper.toZamowienieDTO(zamowienie)).getJson();
        when(mockZamowienieService.save(zamowienie)).thenReturn(zamowienie);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonZamowienie).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(zamowienie.getId()))
                .andExpect(jsonPath("$.rozpoznanie").value(zamowienie.getRozpoznanie()));
        verify(mockZamowienieService, times(1)).save(any(Zamowienie.class));
        verifyNoMoreInteractions(mockZamowienieService);
    }

    @Test
    public void updateZamowienie() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        Zamowienie zamowienie = new Zamowienie(1, pacjent, pomiar,jos,jos,new Date(),new Date(),"Test", Zamowienie.Typ.DOJ, Zamowienie.Status.ZAP,null,new Date(), new Date());
        String jsonZamowienie = jacksonTester.write(zamowienieMapper.toZamowienieDTO(zamowienie)).getJson();
        when(mockZamowienieService.findById(zamowienie.getId())).thenReturn(Optional.of(zamowienie));
        when(mockZamowienieService.save(any(Zamowienie.class))).thenReturn(zamowienie);
        mockMvc.perform(put(apiPath + "/{zamowienieId}", zamowienie.getId()).header("Authorization", "Bearer " + getToken()).content(jsonZamowienie)
                .content(jsonZamowienie)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockZamowienieService, times(1)).save(any(Zamowienie.class));
        verifyNoMoreInteractions(mockZamowienieService);
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