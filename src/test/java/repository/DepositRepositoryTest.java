package repository;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.greendata.entity.Bank;
import ru.greendata.entity.Customer;
import ru.greendata.entity.Deposit;
import ru.greendata.repository.DepositRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class DepositRepositoryTest {

    @Autowired
    private DepositRepository depositRepository;

    @Test
    public void createDepositTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Deposit deposit = new Deposit(4, new Customer(), new Bank() , formatter.parse("2020-05-20 19:00"), 7.5,10);
        Deposit saveDeposit = depositRepository.save(deposit);
        Deposit createdDeposit = depositRepository.findById(saveDeposit.getId()).get();
        Assert.assertNotNull(createdDeposit);
    }

    @Test
    public void findByIdTest(){
        int depositId = depositRepository.findById(1).get().getId();
        Assert.assertEquals(depositId, 1);
    }

    @Test
    public void findAllTest(){
        List<Deposit> deposits = depositRepository.findAll();
        Assert.assertTrue(deposits.size() >= 3);
    }

    @Test
    public void findByBankTest(){
        List<Deposit> deposits = depositRepository.findByBank(new Bank(1, "bank","bic"));
        Assert.assertEquals(deposits.size(), 3);
    }

    @Test
    public void findByCustomerTest(){
        List<Deposit> deposits = depositRepository.findByCustomer(
                new Customer(1, "name", "shortName", "address", "form"));
        Assert.assertEquals(deposits.size(), 3);
    }

    @Test
    public void deleteDepositTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Deposit deposit = new Deposit(1, new Customer(), new Bank() , formatter.parse("2020-05-20 19:00"), 7.5,10);
        depositRepository.delete(deposit);
        Optional<Deposit> deletedDeposit = depositRepository.findById(1);
        Assert.assertFalse(deletedDeposit.isPresent());
    }
}
