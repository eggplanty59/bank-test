package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.greendata.dto.CustomerDto;
import ru.greendata.dto.CustomerDto;
import ru.greendata.dto.CustomerDto;
import ru.greendata.dto.params.FilterCriteria;
import ru.greendata.dto.params.FilterParams;
import ru.greendata.dto.params.OrderParams;
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Customer;
import ru.greendata.exception.EntityHasDetailsException;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;
import ru.greendata.service.CustomerService;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test(expected = EntityIllegalArgumentException.class)
    public void findNullCustomerException(){
        customerService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findIllegalArgumentCustomerException(){
        customerService.findById("Идентифкикатор");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findNotExistsCustomerException(){
        customerService.findById(10);
    }

    @Test
    public void findCustomerTest(){
        Customer customer= customerService.findById(1);
        Assert.assertEquals("ODK", customer.getName());
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void createNullCustomerException(){
        customerService.create(null);
    }

    @Test
    public void createCustomerTest(){
        customerService.create(new CustomerDto(5,"name","shortName","address","form"));
        Customer customer= customerService.findById(5);
        Assert.assertNotNull(customer);
        Assert.assertEquals("name", customer.getName());
    }

    @Test(expected = EntityHasDetailsException.class)
    public void deleteHasDetailsCustomerException(){
        customerService.delete(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteCustomer(){
        customerService.delete(4);
        Customer customer= customerService.findById(4);
    }

    @Test
    public void listCustomerTest(){
        List<CustomerDto> list = customerService.list(new Params());
        Assert.assertTrue(list.size() >= 3);
    }

    @Test
    public void listOrderByDescCustomerTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","desc"));
        Params params = new Params(null, orders);

        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listOrderByAscCustomerTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(null, orders);

        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listMultiOrderByCustomerTest(){
        List<OrderParams> orders = Arrays.asList(new OrderParams("name","desc"),new OrderParams("address","asc"));
        Params params = new Params(null, orders);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listFilterCustomerEqualsTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","=","1")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listFilterCustomerGreaterTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id",">","3")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listFilterCustomerLessTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","<","2")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }

    @Test
    public void listFilterCustomerNotEqualsTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("id","!=","1")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.size() == 3);
    }

    @Test
    public void listFilterAndCustomerTest(){
        FilterParams filterCriteria = new FilterParams("and", null
                ,Arrays.asList(new FilterCriteria("name","=","ODK"),new FilterCriteria("address","=","Omsk")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 3);
    }

    @Test
    public void listFilterOrCustomerTest(){
        FilterParams filterCriteria = new FilterParams("or", null
                ,Arrays.asList(new FilterCriteria("name","=","OAK"),new FilterCriteria("name","=","S7")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.size() == 2);
    }

    @Test
    public void listFilterAndOrCustomerTest(){
        List<FilterParams> filterParamsOr = Arrays.asList(new FilterParams("or", null, Arrays.asList(
                new FilterCriteria("address", "=", "Perm"), new FilterCriteria("address", "=", "St. Petersburg"))));
        FilterParams filterCriteria = new FilterParams("and", filterParamsOr
                ,Arrays.asList(new FilterCriteria("name","=","S7")));
        Params params = new Params(filterCriteria, null);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 4);
    }

    @Test
    public void listFilterAndOrOrderByCustomerTest(){
        List<FilterParams> filterParamsOr = Arrays.asList(new FilterParams("or", null, Arrays.asList(
                new FilterCriteria("address", "=", "Moscow"), new FilterCriteria("address", "=", "Omsk"))));
        FilterParams filterCriteria = new FilterParams("and", filterParamsOr
                ,Arrays.asList(new FilterCriteria("name","=","ODK")));
        List<OrderParams> orders = Arrays.asList(new OrderParams("id","asc"));
        Params params = new Params(filterCriteria, orders);
        List<CustomerDto> list = customerService.list(params);
        Assert.assertTrue(list.get(0).getId() == 1);
    }
}
