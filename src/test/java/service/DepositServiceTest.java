package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.greendata.dto.BankDto;
import ru.greendata.dto.CustomerDto;
import ru.greendata.dto.DepositDto;
import ru.greendata.dto.params.FilterCriteria;
import ru.greendata.dto.params.FilterParams;
import ru.greendata.dto.params.OrderParams;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Deposit;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;
import ru.greendata.service.DepositService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class DepositServiceTest {

    @Autowired
    private DepositService depositService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void findNullDepositException() {
        depositService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findIllegalArgumentDepositException() {
        depositService.findById("Идентификатор");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findNotExistsDepositException() {
        depositService.findById(10);
    }

    @Test
    public void findDepositTest() {
        Deposit deposit = depositService.findById(1);
        Assert.assertEquals(java.util.Optional.of(1), java.util.Optional.of(deposit.getId()));
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullDepositException() {
        depositService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullBankDepositException() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        depositService.create(new DepositDto(4, new CustomerDto(1, "name", "shortName", "address", "form")
                , null , formatter.parse("2020-05-20 19:00"), 7.5,10));
        Deposit deposit = depositService.findById(5);
        Assert.assertNotNull(deposit);
        Assert.assertEquals(java.util.Optional.of(5), java.util.Optional.of(deposit.getId()));
        depositService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCustomerDepositException() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        depositService.create(new DepositDto(4, null
                , new BankDto(1, "test", "123456789") , formatter.parse("2020-05-20 19:00"), 7.5,10));
        Deposit deposit = depositService.findById(5);
        Assert.assertNotNull(deposit);
        Assert.assertEquals(java.util.Optional.of(5), java.util.Optional.of(deposit.getId()));
        depositService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNotExistBankDepositException() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        depositService.create(new DepositDto(4, new CustomerDto(1, "name", "shortName", "address", "form")
                , new BankDto(10, "test", "123456789") , formatter.parse("2020-05-20 19:00"), 7.5,10));
        Deposit deposit = depositService.findById(5);
        Assert.assertNotNull(deposit);
        Assert.assertEquals(java.util.Optional.of(5), java.util.Optional.of(deposit.getId()));
        depositService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNotExistCustomerDepositException() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        depositService.create(new DepositDto(4, new CustomerDto(10, "name", "shortName", "address", "form")
                , new BankDto(1, "test", "123456789") , formatter.parse("2020-05-20 19:00"), 7.5,10));
        Deposit deposit = depositService.findById(5);
        Assert.assertNotNull(deposit);
        Assert.assertEquals(java.util.Optional.of(5), java.util.Optional.of(deposit.getId()));
        depositService.create(null);
    }

    @Test
    public void createDepositTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        depositService.create(new DepositDto(4, new CustomerDto(1, "name", "shortName", "address", "form")
                , new BankDto(1, "test", "123456789") , formatter.parse("2020-05-20 19:00"), 7.5,10));
        Deposit deposit = depositService.findById(5);
        Assert.assertNotNull(deposit);
        Assert.assertEquals(java.util.Optional.of(5), java.util.Optional.of(deposit.getId()));
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteDeposit() {
        depositService.delete(4);
        Deposit deposit = depositService.findById(4);
    }

    @Test
    public void listDepositTest(){
        List<DepositDto> list = depositService.list(new Params());
        Assert.assertTrue(list.size() >= 5);
    }

    @Test
    public void listOrderByDescDepositTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","desc"));
        Params params = new Params(null, orders);

        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 6);
    }

    @Test
    public void listOrderByAscDepositTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(null, orders);

        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listMultiOrderByDepositTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("term","asc"),new OrderParams("percent","desc"));
        Params params = new Params(null, orders);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 2);
    }

    @Test
    public void listFilterDepositEqualsTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","=","1")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listFilterDepositGreaterTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id",">","5")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 6);
    }

    @Test
    public void listFilterDepositLessTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","<","2")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listFilterDepositNotEqualsTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","!=","1")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.size() == 5);
    }

    @Test
    public void listFilterAndDepositTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("term","=","6"),new FilterCriteria("percent","=","5.4")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 2);
    }

    @Test
    public void listFilterOrDepositTest(){
        FilterParams filterCriteria = new FilterParams("or", null
                ,Arrays.asList(new FilterCriteria("term","=","6"),new FilterCriteria("term","=","9")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void listFilterAndOrDepositTest(){
        List<FilterParams> filterParamsOr = Arrays.asList(new FilterParams("or", null, Arrays.asList(
                new FilterCriteria("percent", "=", "5.9"), new FilterCriteria("percent", "=", "3.8"))));
        FilterParams filterCriteria = new FilterParams("and", filterParamsOr
                ,Arrays.asList(new FilterCriteria("term","=","8")));
        Params params = new Params(filterCriteria, null);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 6);
    }

    @Test
    public void listFilterAndOrOrderByDepositTest(){
        List<FilterParams> filterParamsOr = Arrays.asList(new FilterParams("or", null, Arrays.asList(
                new FilterCriteria("percent", "=", "5.4"), new FilterCriteria("percent", "=", "5.0"))));
        FilterParams filterCriteria = new FilterParams("and", filterParamsOr
                ,Arrays.asList(new FilterCriteria("term","=","6")));
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(filterCriteria, orders);
        List<DepositDto> list = depositService.list(params);
        Assert.assertTrue(list.get(0).getId() == 2);
    }


}