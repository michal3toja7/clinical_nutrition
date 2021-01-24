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
import pl.michal.clinical_nutrition.admin.dto.JosDTO;
import pl.michal.clinical_nutrition.admin.entity.Jos;
import pl.michal.clinical_nutrition.admin.mapper.JosMapper;
import pl.michal.clinical_nutrition.admin.service.JosService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class JosControllerTest {
    private final String apiPath = "/api/user/jos";
    private final String pathAuthenticate = "/api/authenticate";
    @Autowired
    private JosMapper josMapper;

    @MockBean
    private JosService mockJosService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<JosDTO> jacksonTester;// = new JacksonTester<Jos>();



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
    public void getJoss() throws Exception {
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        when(mockJosService.findAll()).thenReturn(Collections.singletonList(jos));
        mockMvc.perform(get(apiPath).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(jos.getId()))
                .andExpect(jsonPath("$[0].nazwa").value(jos.getNazwa()));
        verify(mockJosService, times(1)).findAll();
        verifyNoMoreInteractions(mockJosService);
    }

    @Test
    public void getJos() throws Exception {
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        when(mockJosService.findById(jos.getId()))
                .thenReturn(Optional.of(jos));
        mockMvc.perform(get(apiPath + "/{josId}", jos.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(jos.getId()))
                .andExpect(jsonPath("$.nazwa").value(jos.getNazwa()));
        verify(mockJosService, times(1)).findById(jos.getId());
        verifyNoMoreInteractions(mockJosService);
    }
    @Test
    public void createJos() throws Exception {
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        String jsonJos = jacksonTester.write(josMapper.toJosDTO(jos)).getJson();
        when(mockJosService.save(jos)).thenReturn(jos);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonJos).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(jos.getId()))
                .andExpect(jsonPath("$.nazwa").value(jos.getNazwa()));
        verify(mockJosService, times(1)).save(any(Jos.class));
        verifyNoMoreInteractions(mockJosService);
    }

    @Test
    public void updateJos() throws Exception {
        Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
        String jsonJos = jacksonTester.write(josMapper.toJosDTO(jos)).getJson();
        when(mockJosService.findById(jos.getId())).thenReturn(Optional.of(jos));
        when(mockJosService.save(any(Jos.class))).thenReturn(jos);
        mockMvc.perform(put(apiPath + "/{josId}", jos.getId()).header("Authorization", "Bearer " + getToken()).content(jsonJos)
                .content(jsonJos)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockJosService, times(1)).save(any(Jos.class));
        verifyNoMoreInteractions(mockJosService);
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