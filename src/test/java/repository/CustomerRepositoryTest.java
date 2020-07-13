package repository;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.greendata.entity.Customer;
import ru.greendata.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void createCustomerTest(){
        Customer customer = new Customer(4, "name", "shortName" , "address", "form");
        Customer saveCustomer = customerRepository.save(customer);
        Customer createdCustomer = customerRepository.findById(saveCustomer.getId()).get();
        Assert.assertNotNull(createdCustomer);
    }

    @Test
    public void findByIdTest(){
        int customerId = customerRepository.findById(1).get().getId();
        Assert.assertEquals(customerId, 1);
    }

    @Test
    public void findAllTest(){
        List<Customer> customers = customerRepository.findAll();
        Assert.assertTrue(customers.size() >= 3);
    }

    @Test
    public void deleteCustomerTest(){
        Customer customer = new Customer(1, "name", "shortName" , "address", "form");
        customerRepository.delete(customer);
        Optional<Customer> deletedCustomer = customerRepository.findById(1);
        Assert.assertFalse(deletedCustomer.isPresent());
    }
}
