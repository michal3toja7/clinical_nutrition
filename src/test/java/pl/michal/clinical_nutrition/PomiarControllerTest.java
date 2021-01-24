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
import java.util.List;
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
import pl.michal.clinical_nutrition.calculator.dto.PomiarDTO;
import pl.michal.clinical_nutrition.calculator.entity.Adres;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.entity.Pomiar;
import pl.michal.clinical_nutrition.calculator.mapper.PomiarMapper;
import pl.michal.clinical_nutrition.calculator.service.PomiarService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class PomiarControllerTest {
    private final String apiPath = "/api/user/pomiar";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private PomiarMapper pomiarMapper;

    @MockBean
    private PomiarService mockPomiarService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<PomiarDTO> jacksonTester;// = new JacksonTester<Pomiar>();



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
    public void getPomiary() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        when(mockPomiarService.findByIdPacjenta(pacjent.getId())).thenReturn(Collections.singletonList(pomiar));
        mockMvc.perform(get(apiPath+"/pacjent/{idPacjenta}", pacjent.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(pomiar.getId()))
                .andExpect(jsonPath("$[0].idPacjenta").value(pomiar.getPacjent().getId()));
        verify(mockPomiarService, times(1)).findByIdPacjenta(pacjent.getId());
        verifyNoMoreInteractions(mockPomiarService);
    }
    @Test
    public void getPomiar() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        when(mockPomiarService.findById(pomiar.getId()))
                .thenReturn(Optional.of(pomiar));
        mockMvc.perform(get(apiPath + "/{pomiarId}", pomiar.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pomiar.getId()))
                .andExpect(jsonPath("$.idPacjenta").value(pomiar.getPacjent().getId()));
        verify(mockPomiarService, times(1)).findById(pomiar.getId());
        verifyNoMoreInteractions(mockPomiarService);
    }
    @Test
    public void createPomiar() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        String jsonPomiar = jacksonTester.write(pomiarMapper.toPomiarDTO(pomiar)).getJson();

        when(mockPomiarService.save(pomiar)).thenReturn(pomiar);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken())
                .content(jsonPomiar)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pomiar.getId()))
                .andExpect(jsonPath("$.idPacjenta").value(pomiar.getPacjent().getId()))
                .andExpect(jsonPath("$.waga").value(pomiar.getWaga()));

        verify(mockPomiarService, times(1)).save(any(Pomiar.class));
        verifyNoMoreInteractions(mockPomiarService);
    }
    @Test
    public void updatePomiar() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
        String jsonPomiar = jacksonTester.write(pomiarMapper.toPomiarDTO(pomiar)).getJson();
        when(mockPomiarService.findById(pomiar.getId())).thenReturn(Optional.of(pomiar));
        when(mockPomiarService.save(any(Pomiar.class))).thenReturn(pomiar);
        mockMvc.perform(put(apiPath + "/{pomiarId}", pomiar.getId()).header("Authorization", "Bearer " + getToken()).content(jsonPomiar)
                .content(jsonPomiar)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockPomiarService, times(1)).save(any(Pomiar.class));
        verifyNoMoreInteractions(mockPomiarService);
    }



    @BeforeEach
    public void before(TestInfo testInfo) {
        System.out.printf("-- METODA -> %s%n", testInfo.getTestMethod().get().getName());
        ObjectMapper mapper = new ObjectMapper(); // ustawienie formatu daty i czasu w komunikatach
     //   mapper.registerModule(new JavaTimeModule()); // JSON-a dla zmiennych typu LocalDate i LocalDateTime
      //  mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JacksonTester.initFields(this, mapper);
    }
    @AfterEach
    public void after(TestInfo testInfo) {
        System.out.printf("<- KONIEC -- %s%n", testInfo.getTestMethod().get().getName());
    }
}

