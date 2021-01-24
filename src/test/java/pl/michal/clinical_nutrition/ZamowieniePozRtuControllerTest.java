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
import pl.michal.clinical_nutrition.calculator.dto.ZamowieniePozRtuDTO;
import pl.michal.clinical_nutrition.calculator.entity.*;
import pl.michal.clinical_nutrition.calculator.mapper.ZamowieniePozRtuMapper;
import pl.michal.clinical_nutrition.calculator.service.ZamowieniePozRtuService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(username = "mbruzdowski", password = "test")
public class ZamowieniePozRtuControllerTest {
    private final String apiPath = "/api/user/zamowienie/pozycjeRTU";
    private final String pathAuthenticate = "/api/authenticate";
    private final Pacjent pacjent = new Pacjent(1, "NazwiskoTest1", "ImionaTest1", 95080302298L, Pacjent.Plec.M, new Date(), new Adres("TestoweMiasto", "00-000", "TestowaUlica", "666/999", " "), true, new Date(), new Date());
    private final Pomiar pomiar = new Pomiar(1L,pacjent,80.0,180.0,36.6,Pomiar.Aktywnosc.CHODZI_PRZY_LOZKU, Pomiar.StanChorego.CIEZKI,new Date(),new Date(),new Date());
    private final Jos jos = new Jos(1,"TEST","TestowaNazwa", Jos.Rodzaj.APT,true,"TEST","TEST","TEST","TEST","TEST","TEST","TEST");
    private final Zamowienie zamowienie = new Zamowienie(1, pacjent, pomiar,jos,jos,new Date(),new Date(),"Test", Zamowienie.Typ.DOJ, Zamowienie.Status.ZAP,null,new Date(), new Date());
    private final WorekPreparat worekPreparat = new WorekPreparat(1, "TestowyProducent", "TestowaNazwa",10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,"TestowySposob",25,26,27,28,29,30,31,32,33,34,35,36,37,38,true,new Date(), new Date());

    @Autowired
    private ZamowieniePozRtuMapper zamowieniePozRtuMapper;

    @MockBean
    private ZamowieniePozRtuService mockZamowieniePozRtuService;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<ZamowieniePozRtuDTO> jacksonTester;// = new JacksonTester<ZamowieniePozRtu>();



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
    public void getZamowieneiPozRtus() throws Exception {
        ZamowieniePozRtu zamowieniePozRtu = new ZamowieniePozRtu(1,zamowienie, ZamowieniePozRtu.TypZywienia.CAL,worekPreparat,4,"Uwagi",new Date(),new Date());
        when(mockZamowieniePozRtuService.findByIdZamowienia(zamowienie.getId())).thenReturn(Collections.singletonList(zamowieniePozRtu));
        mockMvc.perform(get(apiPath+ "/zamowienie/{idZamowienia}",zamowienie.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[*]").exists())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(zamowieniePozRtu.getId()))
                .andExpect(jsonPath("$[0].zamowienieId").value(zamowieniePozRtu.getZamowienie().getId()));
        verify(mockZamowieniePozRtuService, times(1)).findByIdZamowienia(zamowienie.getId());
        verifyNoMoreInteractions(mockZamowieniePozRtuService);
    }

    @Test
    public void getZamowieniePozRtu() throws Exception {
        ZamowieniePozRtu zamowieniePozRtu = new ZamowieniePozRtu(1,zamowienie, ZamowieniePozRtu.TypZywienia.CAL,worekPreparat,4,"Uwagi",new Date(),new Date());
        when(mockZamowieniePozRtuService.findById(zamowieniePozRtu.getId()))
                .thenReturn(Optional.of(zamowieniePozRtu));
        mockMvc.perform(get(apiPath + "/{zamowieniePozRtuId}", zamowieniePozRtu.getId()).header("Authorization", "Bearer " + getToken()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(zamowieniePozRtu.getId()))
                .andExpect(jsonPath("$.zamowienieId").value(zamowieniePozRtu.getZamowienie().getId()));
        verify(mockZamowieniePozRtuService, times(1)).findById(zamowieniePozRtu.getId());
        verifyNoMoreInteractions(mockZamowieniePozRtuService);
    }
    @Test
    public void createZamowieniePozRtu() throws Exception {
        ZamowieniePozRtu zamowieniePozRtu = new ZamowieniePozRtu(1,zamowienie, ZamowieniePozRtu.TypZywienia.CAL,worekPreparat,4,"Uwagi",new Date(),new Date());
        String jsonZamowieniePozRtu = jacksonTester.write(zamowieniePozRtuMapper.toZamowieniePozRtuDTO(zamowieniePozRtu)).getJson();
        when(mockZamowieniePozRtuService.save(zamowieniePozRtu)).thenReturn(zamowieniePozRtu);
        mockMvc.perform(post(apiPath).header("Authorization", "Bearer " + getToken()).content(jsonZamowieniePozRtu).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(zamowieniePozRtu.getId()))
                .andExpect(jsonPath("$.zamowienieId").value(zamowieniePozRtu.getZamowienie().getId()));
        verify(mockZamowieniePozRtuService, times(1)).save(any(ZamowieniePozRtu.class));
        verifyNoMoreInteractions(mockZamowieniePozRtuService);
    }

    @Test
    public void updateZamowieniePozRtu() throws Exception {
        ZamowieniePozRtu zamowieniePozRtu = new ZamowieniePozRtu(1,zamowienie, ZamowieniePozRtu.TypZywienia.CAL,worekPreparat,4,"Uwagi",new Date(),new Date());
        String jsonZamowieniePozRtu = jacksonTester.write(zamowieniePozRtuMapper.toZamowieniePozRtuDTO(zamowieniePozRtu)).getJson();
        when(mockZamowieniePozRtuService.findById(zamowieniePozRtu.getId())).thenReturn(Optional.of(zamowieniePozRtu));
        when(mockZamowieniePozRtuService.save(any(ZamowieniePozRtu.class))).thenReturn(zamowieniePozRtu);
        mockMvc.perform(put(apiPath + "/{zamowieniePozRtuId}", zamowieniePozRtu.getId()).header("Authorization", "Bearer " + getToken()).content(jsonZamowieniePozRtu)
                .content(jsonZamowieniePozRtu)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(mockZamowieniePozRtuService, times(1)).save(any(ZamowieniePozRtu.class));
        verifyNoMoreInteractions(mockZamowieniePozRtuService);
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