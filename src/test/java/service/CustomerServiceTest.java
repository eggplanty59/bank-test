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
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Customer;
import ru.greendata.exception.EntityHasDetailsException;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;
import ru.greendata.service.CustomerService;

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
        Assert.assertTrue(customerService.list(new Params()).size() >= 3);
    }
}
