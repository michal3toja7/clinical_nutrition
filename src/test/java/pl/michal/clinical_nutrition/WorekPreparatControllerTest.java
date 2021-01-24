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
import pl.michal.clinical_nutrition.calculator.dto.WorekPreparatDTO;
import pl.michal.clinical_nutrition.calculator.entity.WorekPreparat;
import pl.michal.clinical_nutrition.calculator.mapper.WorekPreparatMapper;
import pl.michal.clinical_nutrition.calculator.service.WorekPreparatService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class WorekPreparatControllerTest {
    private final String apiPath = "/api/user/worekPreparat";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private WorekPreparatMapper worekPreparatMapper;

    @MockBean
    private WorekPreparatService mockWorekPreparatService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<WorekPreparatDTO> jacksonTester;// = new JacksonTester<WorekPreparat>();



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
    public void getWorki() throws Exception {
        WorekPreparat worekPreparat = new WorekPreparat(1, "TestowyProducent", "TestowaNazwa",10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,"TestowySposob",25,26,27,28,29,30,31,32,33,34,35,36,37,38,true,new Date(), new Date());
        when(mockWorekPreparatService.findAll()).thenReturn(Collections.singletonList(worekPreparat));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(worekPreparat.getId()))
                .andExpect(jsonPath("$[0].nazwa").value(worekPreparat.getNazwa()));
        verify(mockWorekPreparatService, times(1)).findAll();
        verifyNoMoreInteractions(mockWorekPreparatService);
    }

    @Test
    public void getWorekPreparat() throws Exception {
        WorekPreparat worekPreparat = new WorekPreparat(1, "TestowyProducent", "TestowaNazwa",10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,"TestowySposob",25,26,27,28,29,30,31,32,33,34,35,36,37,38,true,new Date(), new Date());
        when(mockWorekPreparatService.findById(worekPreparat.getId()))
                .thenReturn(Optional.of(worekPreparat));
        mockMvc.perform(get(apiPath + "/{worekPreparatId}", worekPreparat.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(worekPreparat.getId()))
                .andExpect(jsonPath("$.nazwa").value(worekPreparat.getNazwa()));
        verify(mockWorekPreparatService, times(1)).findById(worekPreparat.getId());
        verifyNoMoreInteractions(mockWorekPreparatService);
    }
    @Test
    public void createWorekPreparat() throws Exception {
        WorekPreparat worekPreparat = new WorekPreparat(1, "TestowyProducent", "TestowaNazwa",10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,"TestowySposob",25,26,27,28,29,30,31,32,33,34,35,36,37,38,true,new Date(), new Date());
        String jsonWorekPreparat = jacksonTester.write(worekPreparatMapper.toWorekPreparatDTO(worekPreparat)).getJson();
        when(mockWorekPreparatService.save(worekPreparat)).thenReturn(worekPreparat);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonWorekPreparat).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(worekPreparat.getId()))
                .andExpect(jsonPath("$.nazwa").value(worekPreparat.getNazwa()));
        verify(mockWorekPreparatService, times(1)).save(any(WorekPreparat.class));
        verifyNoMoreInteractions(mockWorekPreparatService);
    }

    @Test
    public void updateWorekPreparat() throws Exception {
        WorekPreparat worekPreparat = new WorekPreparat(1, "TestowyProducent", "TestowaNazwa",10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,"TestowySposob",25,26,27,28,29,30,31,32,33,34,35,36,37,38,true,new Date(), new Date());
        String jsonWorekPreparat = jacksonTester.write(worekPreparatMapper.toWorekPreparatDTO(worekPreparat)).getJson();
        when(mockWorekPreparatService.findById(worekPreparat.getId())).thenReturn(Optional.of(worekPreparat));
        when(mockWorekPreparatService.save(any(WorekPreparat.class))).thenReturn(worekPreparat);
        mockMvc.perform(put(apiPath + "/{worekPreparatId}", worekPreparat.getId()).header("Authorization", "Bearer " + getToken()).content(jsonWorekPreparat)
                .content(jsonWorekPreparat)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockWorekPreparatService, times(1)).save(any(WorekPreparat.class));
        verifyNoMoreInteractions(mockWorekPreparatService);
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