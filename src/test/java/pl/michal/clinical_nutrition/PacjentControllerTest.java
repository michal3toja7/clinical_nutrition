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
import pl.michal.clinical_nutrition.calculator.entity.Adres;
import pl.michal.clinical_nutrition.calculator.entity.Pacjent;
import pl.michal.clinical_nutrition.calculator.mapper.PacjentMapper;
import pl.michal.clinical_nutrition.calculator.service.PacjentService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class PacjentControllerTest {
    private final String apiPath = "/api/user/pacjent";
    private final String pathAuthenticate = "/api/authenticate";
    private PacjentMapper pacjentMapper;

    @MockBean
    private PacjentService mockPacjentService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Pacjent> jacksonTester;// = new JacksonTester<Pacjent>();



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
    public void getPacjenci() throws Exception {
        Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        when(mockPacjentService.findAll()).thenReturn(Collections.singletonList(pacjent));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(pacjent.getId()))
                .andExpect(jsonPath("$[0].nazwisko").value(pacjent.getNazwisko()));
        verify(mockPacjentService, times(1)).findAll();
        verifyNoMoreInteractions(mockPacjentService);
    }

    @Test
    public void getPacjent() throws Exception {
        Pacjent pacjent = new Pacjent(2, "NazwiskoTest2", "ImionaTest2", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        when(mockPacjentService.findById(pacjent.getId()))
                .thenReturn(Optional.of(pacjent));
        mockMvc.perform(get(apiPath + "/{pacjentId}", pacjent.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pacjent.getId()))
                .andExpect(jsonPath("$.nazwisko").value(pacjent.getNazwisko()));
        verify(mockPacjentService, times(1)).findById(pacjent.getId());
        verifyNoMoreInteractions(mockPacjentService);
    }
    @Test
    public void createPacjent() throws Exception {
        Pacjent pacjent = new Pacjent( 3,"NazwiskoTest3", "ImionaTest3", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        String jsonPacjent = jacksonTester.write(pacjent).getJson();
        when(mockPacjentService.save(pacjent)).thenReturn(pacjent);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonPacjent).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(pacjent.getId()))
                .andExpect(jsonPath("$.nazwisko").value(pacjent.getNazwisko()));
          verify(mockPacjentService, times(1)).save(any(Pacjent.class));
          verifyNoMoreInteractions(mockPacjentService);
    }

    @Test
    public void updatePacjent() throws Exception {
        Pacjent pacjent = new Pacjent(4,"NazwiskoTest4", "ImionaTest4", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
        String jsonPacjent = jacksonTester.write(pacjent).getJson();
        when(mockPacjentService.findById(pacjent.getId())).thenReturn(Optional.of(pacjent));
        when(mockPacjentService.save(any(Pacjent.class))).thenReturn(pacjent);
        mockMvc.perform(put(apiPath + "/{pacjentId}", pacjent.getId()).header("Authorization", "Bearer " + getToken()).content(jsonPacjent)
                .content(jsonPacjent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockPacjentService, times(1)).save(any(Pacjent.class));
        verifyNoMoreInteractions(mockPacjentService);
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
