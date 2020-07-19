package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;
import ru.greendata.controllers.BankController;
import ru.greendata.dto.BankDto;
import ru.greendata.dto.params.FilterCriteria;
import ru.greendata.dto.params.FilterParams;
import ru.greendata.dto.params.OrderParams;
import ru.greendata.dto.params.Params;
import ru.greendata.service.BankService;
import service.mock.MockBankService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BankController.class, MockBankService.class})
@AutoConfigureMockMvc
public class BankControllerTest {

    @Autowired
    private BankController bankController;

    private MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/bank";

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(bankController).build();
    }


    //Создание
    @Test
    public void statusCreateTest() throws Exception {
        BankDto bankDto = new BankDto();
        String requestJson = mapper.writeValueAsString(bankDto);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    //редактирование
    @Test
    public void statusUpdateBankTest() throws Exception {
        BankDto bankDto = new BankDto();
        String requestJson = mapper.writeValueAsString(bankDto);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //выборка
    @Test
    public void statusListBankTest() throws Exception {
        Params params = new Params();
        String requestJson = mapper.writeValueAsString(params);
        mockMvc.perform(post(URL+"/list").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //удаление
    @Test
    public void statusDeleteBankTest() throws Exception {
        mockMvc.perform(delete(URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void SerializeBankCreateTest() throws Exception {
        BankDto bankDto = new BankDto(1, "name", "bic");
        String requestJson = mapper.writeValueAsString(bankDto);
        MvcResult mvcResult = mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(actualResponseBody,requestJson);
    }

    @Test
    public void SerializeBankCUpdateTest() throws Exception {
        BankDto bankDto = new BankDto(1, "name", "bic");
        String requestJson = mapper.writeValueAsString(bankDto);
        MvcResult mvcResult = mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(actualResponseBody,requestJson);
    }

    @Test
    public void SerializeBankListTest() throws Exception {
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("key","=","value")));

        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(filterCriteria, orders);

        String requestJson = mapper.writeValueAsString(params);

        MvcResult mvcResult = mockMvc.perform(post(URL+"/list").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        BankDto bankDto = new BankDto(1, "name", "bic");
        String expectedResponseBody = mapper.writeValueAsString(Arrays.asList(bankDto));

        Assert.assertEquals(actualResponseBody,expectedResponseBody);
    }
}
