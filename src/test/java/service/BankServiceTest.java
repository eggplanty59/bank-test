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
import ru.greendata.dto.params.FilterCriteria;
import ru.greendata.dto.params.FilterParams;
import ru.greendata.dto.params.OrderParams;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Bank;
import ru.greendata.exception.EntityHasDetailsException;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;
import ru.greendata.service.BankService;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class BankServiceTest {

    @Autowired
    private BankService bankService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void findNullBankException(){
        bankService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findIllegalArgumentBankException(){
        bankService.findById("Идентифкикатор");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findNotExistsBankException(){
        bankService.findById(10);
    }

    @Test
    public void findBankTest(){
        Bank bank= bankService.findById(1);
        Assert.assertEquals(bank.getName(), "VTB");
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullBankException(){
        bankService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullBicBankException(){
        bankService.create(new BankDto(1,"bank", null));
    }

    @Test
    public void createBankTest(){
        bankService.create(new BankDto(5,"test","123456789"));
        Bank bank= bankService.findById(5);
        Assert.assertNotNull(bank);
        Assert.assertEquals("test", bank.getName());
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteHasDetailsBankException(){
        bankService.delete(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteBank(){
        bankService.delete(5);
        Bank bank= bankService.findById(5);
    }

    @Test
    public void listBankTest(){
        List<BankDto> list = bankService.list(new Params());
        Assert.assertTrue(list.size() >= 3);
    }

    @Test
    public void listOrderByDescBankTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","desc"));
        Params params = new Params(null, orders);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listOrderByAscBankTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(null, orders);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listMultiOrderByBankTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("name","desc"),new OrderParams("bic","asc"));
        Params params = new Params(null, orders);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 3);
    }

    @Test
    public void listFilterBankEqualsTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","=","1")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listFilterBankGreaterTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id",">","3")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listFilterBankLessTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","<","2")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listFilterBankNotEqualsTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","!=","1")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void listFilterAndBankTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("name","=","VTB"),new FilterCriteria("bic","=","147852369")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 3);
    }

    @Test
    public void listFilterOrBankTest(){
        FilterParams filterCriteria = new FilterParams("or", null
                ,Arrays.asList(new FilterCriteria("name","=","Sber"),new FilterCriteria("name","=","Alfa")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void listFilterAndOrBankTest(){
        List<FilterParams> filterParamsOr = Arrays.asList(new FilterParams("or", null, Arrays.asList(
                new FilterCriteria("bic", "=", "987654321"), new FilterCriteria("bic", "=", "247852369"))));
        FilterParams filterCriteria = new FilterParams("and", filterParamsOr
                ,Arrays.asList(new FilterCriteria("name","=","Alfa")));
        Params params = new Params(filterCriteria, null);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listFilterAndOrOrderByBankTest(){
        List<FilterParams> filterParamsOr = Arrays.asList(new FilterParams("or", null, Arrays.asList(
                new FilterCriteria("bic", "=", "193456789"), new FilterCriteria("bic", "=", "147852369"))));
        FilterParams filterCriteria = new FilterParams("and", filterParamsOr
                ,Arrays.asList(new FilterCriteria("name","=","VTB")));
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(filterCriteria, orders);
        List<BankDto> list = bankService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }
}
