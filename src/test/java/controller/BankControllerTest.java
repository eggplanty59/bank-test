package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.greendata.controllers.BankController;
import ru.greendata.dto.BankDto;
import ru.greendata.dto.params.Params;
import ru.greendata.service.BankService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankController.class})
@AutoConfigureMockMvc
public class BankControllerTest {

    @Autowired
    private BankController bankController;

    @MockBean
    BankService bankService;

    private MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/bank";

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(bankController).build();
    }


    //Создание
    @Test
    public void createTest() throws Exception {
        BankDto bankDto = new BankDto();
        String requestJson = mapper.writeValueAsString(bankDto);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    //редактирование
    @Test
    public void updateBankTest() throws Exception {
        BankDto bankDto = new BankDto();
        String requestJson = mapper.writeValueAsString(bankDto);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //выборка
    @Test
    public void listBankTest() throws Exception {
        Params params = new Params();
        String requestJson = mapper.writeValueAsString(params);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //удаление
    @Test
    public void deleteBankTest() throws Exception {
        mockMvc.perform(delete(URL + "/1"))
                .andExpect(status().isNoContent());
    }
}
