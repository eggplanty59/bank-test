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
import ru.greendata.controllers.DepositController;
import ru.greendata.dto.*;
import ru.greendata.dto.DepositDto;
import ru.greendata.dto.DepositDto;
import ru.greendata.dto.params.FilterCriteria;
import ru.greendata.dto.params.FilterParams;
import ru.greendata.dto.params.OrderParams;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.*;
import ru.greendata.entity.Deposit;
import ru.greendata.entity.Deposit;
import ru.greendata.service.DepositService;
import service.mock.MockCustomerService;
import service.mock.MockDepositService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepositController.class, MockDepositService.class})
@AutoConfigureMockMvc
public class DepositControllerTest {

    @Autowired
    private DepositController depositController;

    private MockMvc mockMvc;

    private final static String URL = "http://localhost:8080/api/v1/deposit";

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(depositController).build();
    }


    //Создание
    @Test
    public void createTest() throws Exception {
        DepositDto depositDto = new DepositDto();
        String requestJson = mapper.writeValueAsString(depositDto);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    //редактирование
    @Test
    public void updateDepositTest() throws Exception {
        DepositDto depositDto = new DepositDto();
        String requestJson = mapper.writeValueAsString(depositDto);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //выборка
    @Test
    public void listDepositTest() throws Exception {
        Params params = new Params();
        String requestJson = mapper.writeValueAsString(params);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    //удаление
    @Test
    public void deleteDepositTest() throws Exception {
        mockMvc.perform(delete(URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void SerializeDepositCreateTest() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DepositDto depositDto = new DepositDto(4, new CustomerDto(), new BankDto() , formatter.parse("2020-05-20 19:00"), 7.5,10);
        String requestJson = mapper.writeValueAsString(depositDto);
        MvcResult mvcResult = mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(actualResponseBody,requestJson);
    }

    @Test
    public void SerializeDepositCUpdateTest() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DepositDto depositDto = new DepositDto(4, new CustomerDto(), new BankDto() , formatter.parse("2020-05-20 19:00"), 7.5,10);
        String requestJson = mapper.writeValueAsString(depositDto);
        MvcResult mvcResult = mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(actualResponseBody,requestJson);
    }

    @Test
    public void SerializeDepositListTest() throws Exception {
        FilterParams filterCriteria = new FilterParams("and", null
                , Arrays.asList(new FilterCriteria("key","=","value")));

        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(filterCriteria, orders);

        String requestJson = mapper.writeValueAsString(params);

        MvcResult mvcResult = mockMvc.perform(post(URL+"/list").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DepositDto depositDto = new DepositDto(4, new CustomerDto(), new BankDto() , formatter.parse("2020-05-20 19:00"), 7.5,10);
        String expectedResponseBody = mapper.writeValueAsString(Arrays.asList(depositDto));

        Assert.assertEquals(actualResponseBody,expectedResponseBody);
    }
}
