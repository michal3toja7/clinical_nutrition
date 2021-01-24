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
import pl.michal.clinical_nutrition.calculator.dto.PreparatDTO;
import pl.michal.clinical_nutrition.calculator.entity.Adres;
import pl.michal.clinical_nutrition.calculator.entity.Preparat;
import pl.michal.clinical_nutrition.calculator.mapper.PreparatMapper;
import pl.michal.clinical_nutrition.calculator.service.PreparatService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class PreparatControllerTest {
    private final String apiPath = "/api/user/preparat";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private PreparatMapper preparatMapper;

    @MockBean
    private PreparatService mockPreparatService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<PreparatDTO> jacksonTester;// = new JacksonTester<Preparat>();



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
    public void getPreparaty() throws Exception {
        Preparat preparat = new Preparat(1, "TestowyPreparat", Preparat.Typ.DOJ, "TestowyOpis",10,11,12,13,14,15,16,17,18,19,20,21,22,23,true,new Date(), new Date());
        when(mockPreparatService.findAll()).thenReturn(Collections.singletonList(preparat));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(preparat.getId()))
                .andExpect(jsonPath("$[0].nazwa").value(preparat.getNazwa()));
        verify(mockPreparatService, times(1)).findAll();
        verifyNoMoreInteractions(mockPreparatService);
    }

    @Test
    public void getPreparat() throws Exception {
        Preparat preparat = new Preparat(1, "TestowyPreparat", Preparat.Typ.DOJ, "TestowyOpis",10,11,12,13,14,15,16,17,18,19,20,21,22,23,true,new Date(), new Date());
        when(mockPreparatService.findById(preparat.getId()))
                .thenReturn(Optional.of(preparat));
        mockMvc.perform(get(apiPath + "/{preparatId}", preparat.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(preparat.getId()))
                .andExpect(jsonPath("$.nazwa").value(preparat.getNazwa()));
        verify(mockPreparatService, times(1)).findById(preparat.getId());
        verifyNoMoreInteractions(mockPreparatService);
    }
    @Test
    public void createPreparat() throws Exception {
        Preparat preparat = new Preparat(1, "TestowyPreparat", Preparat.Typ.DOJ, "TestowyOpis",10,11,12,13,14,15,16,17,18,19,20,21,22,23,true,new Date(), new Date());
        String jsonPreparat = jacksonTester.write(preparatMapper.toPreparatDTO(preparat)).getJson();
        when(mockPreparatService.save(preparat)).thenReturn(preparat);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonPreparat).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(preparat.getId()))
                .andExpect(jsonPath("$.nazwa").value(preparat.getNazwa()));
        verify(mockPreparatService, times(1)).save(any(Preparat.class));
        verifyNoMoreInteractions(mockPreparatService);
    }

    @Test
    public void updatePreparat() throws Exception {
        Preparat preparat = new Preparat(1, "TestowyPreparat", Preparat.Typ.DOJ, "TestowyOpis",10,11,12,13,14,15,16,17,18,19,20,21,22,23,true,new Date(), new Date());
        String jsonPreparat = jacksonTester.write(preparatMapper.toPreparatDTO(preparat)).getJson();
        when(mockPreparatService.findById(preparat.getId())).thenReturn(Optional.of(preparat));
        when(mockPreparatService.save(any(Preparat.class))).thenReturn(preparat);
        mockMvc.perform(put(apiPath + "/{preparatId}", preparat.getId()).header("Authorization", "Bearer " + getToken()).content(jsonPreparat)
                .content(jsonPreparat)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockPreparatService, times(1)).save(any(Preparat.class));
        verifyNoMoreInteractions(mockPreparatService);
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