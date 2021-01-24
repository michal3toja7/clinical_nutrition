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
import pl.michal.clinical_nutrition.admin.dto.PremissionsDTO;
import pl.michal.clinical_nutrition.admin.entity.*;
import pl.michal.clinical_nutrition.admin.mapper.PremissionsMapper;
import pl.michal.clinical_nutrition.admin.service.PremissionsService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class PremissionsControllerTest {
    private final String apiPath = "/api/admin/premissions";
    private final String pathAuthenticate = "/api/authenticate";
    private final User user = new User(1,"TEST","TEST",new Date(),new Date(),"TestoweImie","TestoweNazwisko",false,"Pielęgniarka",95080302298L
            ,null,null,false,null,"Test");
    private final PremissionsDefinition premissionsDefinition = new PremissionsDefinition(1,"TestNazwa");
    private final Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
    private final PremissionsPK premissionsPK = new PremissionsPK(user,jos,premissionsDefinition);


    @Autowired
    private PremissionsMapper premissionsMapper;

    @MockBean
    private PremissionsService mockPremissionsService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<PremissionsDTO> jacksonTester;// = new JacksonTester<Premissions>();



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
    public void getPremissions() throws Exception {
        Premissions premissions = new Premissions(premissionsPK,true,new Date(),new Date());
        when(mockPremissionsService.findByUzytkownikID(user.getId())).thenReturn(Collections.singletonList(premissions));
        mockMvc.perform(get(apiPath+"/{uzytkownikID}",user.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].uzytkownikID").value(premissions.getPremissionsPK().getUser().getId()))
                .andExpect(jsonPath("$[0].josID").value(premissions.getPremissionsPK().getJos().getId()))
                .andExpect(jsonPath("$[0].uprawnienieID").value(premissions.getPremissionsPK().getPremissionsDefinition().getId()));
        verify(mockPremissionsService, times(1)).findByUzytkownikID(user.getId());
        verifyNoMoreInteractions(mockPremissionsService);
    }

    @Test
    public void createPremissions() throws Exception {
        Premissions premissions = new Premissions(premissionsPK,true,new Date(),new Date());
        String jsonPremissions = jacksonTester.write(premissionsMapper.toPremissionsDTO(premissions)).getJson();
        when(mockPremissionsService.save(premissions)).thenReturn(premissions);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonPremissions).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uzytkownikID").value(premissions.getPremissionsPK().getUser().getId()))
                .andExpect(jsonPath("$.josID").value(premissions.getPremissionsPK().getJos().getId()))
                .andExpect(jsonPath("$.uprawnienieID").value(premissions.getPremissionsPK().getPremissionsDefinition().getId()));
        verify(mockPremissionsService, times(1)).save(any(Premissions.class));
        verifyNoMoreInteractions(mockPremissionsService);
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