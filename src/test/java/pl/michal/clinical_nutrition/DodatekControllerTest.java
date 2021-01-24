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
import pl.michal.clinical_nutrition.calculator.dto.DodatekDTO;
import pl.michal.clinical_nutrition.calculator.entity.Adres;
import pl.michal.clinical_nutrition.calculator.entity.Dodatek;
import pl.michal.clinical_nutrition.calculator.mapper.DodatekMapper;
import pl.michal.clinical_nutrition.calculator.service.DodatekService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class DodatekControllerTest {
    private final String apiPath = "/api/user/dodatek";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private DodatekMapper dodatekMapper;

    @MockBean
    private DodatekService mockDodatekService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<DodatekDTO> jacksonTester;// = new JacksonTester<Dodatek>();



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
    public void getDodatki() throws Exception {
        Dodatek dodatek = new Dodatek(1, "TestowyDodatek", "TestowaKategoria",10,11,12,13,14,15,16,17,18,19,20,21,22,true,new Date(), new Date());
        when(mockDodatekService.findAll()).thenReturn(Collections.singletonList(dodatek));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(dodatek.getId()))
                .andExpect(jsonPath("$[0].nazwa").value(dodatek.getNazwa()));
        verify(mockDodatekService, times(1)).findAll();
        verifyNoMoreInteractions(mockDodatekService);
    }

    @Test
    public void getDodatek() throws Exception {
        Dodatek dodatek = new Dodatek(1, "TestowyDodatek", "TestowaKategoria",10,11,12,13,14,15,16,17,18,19,20,21,22,true,new Date(), new Date());
        when(mockDodatekService.findById(dodatek.getId()))
                .thenReturn(Optional.of(dodatek));
        mockMvc.perform(get(apiPath + "/{dodatekId}", dodatek.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dodatek.getId()))
                .andExpect(jsonPath("$.nazwa").value(dodatek.getNazwa()));
        verify(mockDodatekService, times(1)).findById(dodatek.getId());
        verifyNoMoreInteractions(mockDodatekService);
    }
    @Test
    public void createDodatek() throws Exception {
        Dodatek dodatek = new Dodatek(1, "TestowyDodatek", "TestowaKategoria",10,11,12,13,14,15,16,17,18,19,20,21,22,true,new Date(), new Date());
        String jsonDodatek = jacksonTester.write(dodatekMapper.toDodatekDTO(dodatek)).getJson();
        when(mockDodatekService.save(dodatek)).thenReturn(dodatek);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonDodatek).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(dodatek.getId()))
                .andExpect(jsonPath("$.nazwa").value(dodatek.getNazwa()));
        verify(mockDodatekService, times(1)).save(any(Dodatek.class));
        verifyNoMoreInteractions(mockDodatekService);
    }

    @Test
    public void updateDodatek() throws Exception {
        Dodatek dodatek = new Dodatek(1, "TestowyDodatek", "TestowaKategoria",10,11,12,13,14,15,16,17,18,19,20,21,22,true,new Date(), new Date());
        String jsonDodatek = jacksonTester.write(dodatekMapper.toDodatekDTO(dodatek)).getJson();
        when(mockDodatekService.findById(dodatek.getId())).thenReturn(Optional.of(dodatek));
        when(mockDodatekService.save(any(Dodatek.class))).thenReturn(dodatek);
        mockMvc.perform(put(apiPath + "/{dodatekId}", dodatek.getId()).header("Authorization", "Bearer " + getToken()).content(jsonDodatek)
                .content(jsonDodatek)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockDodatekService, times(1)).save(any(Dodatek.class));
        verifyNoMoreInteractions(mockDodatekService);
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