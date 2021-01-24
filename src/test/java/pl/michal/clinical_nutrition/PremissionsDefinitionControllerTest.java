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
import pl.michal.clinical_nutrition.admin.dto.PremissionsDefinitionDTO;
import pl.michal.clinical_nutrition.admin.entity.PremissionsDefinition;
import pl.michal.clinical_nutrition.admin.mapper.PremissionsDefinitionMapper;
import pl.michal.clinical_nutrition.admin.service.PremissionsDefinitionService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class PremissionsDefinitionControllerTest {
    private final String apiPath = "/api/admin/premissionsdefinition";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private PremissionsDefinitionMapper premissionsDefinitionMapper;

    @MockBean
    private PremissionsDefinitionService mockPremissionsDefinitionService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<PremissionsDefinitionDTO> jacksonTester;// = new JacksonTester<PremissionsDefinition>();



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
    public void getPremissionsDefinitions() throws Exception {
        PremissionsDefinition premissionsDefinition = new PremissionsDefinition(1,"TestNazwa");
        when(mockPremissionsDefinitionService.findAll()).thenReturn(Collections.singletonList(premissionsDefinition));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(premissionsDefinition.getId()))
                .andExpect(jsonPath("$[0].name").value(premissionsDefinition.getName()));
        verify(mockPremissionsDefinitionService, times(1)).findAll();
        verifyNoMoreInteractions(mockPremissionsDefinitionService);
    }

    @Test
    public void getPremissionsDefinition() throws Exception {
        PremissionsDefinition premissionsDefinition = new PremissionsDefinition(1,"TestNazwa");
        when(mockPremissionsDefinitionService.findById(premissionsDefinition.getId()))
                .thenReturn(Optional.of(premissionsDefinition));
        mockMvc.perform(get(apiPath + "/{premissionsDefinitionId}", premissionsDefinition.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(premissionsDefinition.getId()))
                .andExpect(jsonPath("$.name").value(premissionsDefinition.getName()));
        verify(mockPremissionsDefinitionService, times(1)).findById(premissionsDefinition.getId());
        verifyNoMoreInteractions(mockPremissionsDefinitionService);
    }
    @Test
    public void createPremissionsDefinition() throws Exception {
        PremissionsDefinition premissionsDefinition = new PremissionsDefinition(1,"TestNazwa");
        String jsonPremissionsDefinition = jacksonTester.write(premissionsDefinitionMapper.toPremissionsDefinitionDTO(premissionsDefinition)).getJson();
        when(mockPremissionsDefinitionService.save(premissionsDefinition)).thenReturn(premissionsDefinition);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonPremissionsDefinition).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(premissionsDefinition.getId()))
                .andExpect(jsonPath("$.name").value(premissionsDefinition.getName()));
        verify(mockPremissionsDefinitionService, times(1)).save(any(PremissionsDefinition.class));
        verifyNoMoreInteractions(mockPremissionsDefinitionService);
    }

    @Test
    public void updatePremissionsDefinition() throws Exception {
        PremissionsDefinition premissionsDefinition = new PremissionsDefinition(1,"TestNazwa");
        String jsonPremissionsDefinition = jacksonTester.write(premissionsDefinitionMapper.toPremissionsDefinitionDTO(premissionsDefinition)).getJson();
        when(mockPremissionsDefinitionService.findById(premissionsDefinition.getId())).thenReturn(Optional.of(premissionsDefinition));
        when(mockPremissionsDefinitionService.save(any(PremissionsDefinition.class))).thenReturn(premissionsDefinition);
        mockMvc.perform(put(apiPath + "/{premissionsDefinitionId}", premissionsDefinition.getId()).header("Authorization", "Bearer " + getToken()).content(jsonPremissionsDefinition)
                .content(jsonPremissionsDefinition)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockPremissionsDefinitionService, times(1)).save(any(PremissionsDefinition.class));
        verifyNoMoreInteractions(mockPremissionsDefinitionService);
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