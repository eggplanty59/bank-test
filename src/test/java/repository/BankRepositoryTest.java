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
import ru.greendata.repository.BankRepository;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
public class BankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @Test
    public void createBankTest(){
        Bank bank = new Bank(4, "bank_test", "123456789");
        Bank saveBank = bankRepository.save(bank);
        Bank createdBank = bankRepository.findById(saveBank.getId()).get();
        Assert.assertNotNull(createdBank);
    }

    @Test
    public void findByIdTest(){
        int bankId = bankRepository.findById(1).get().getId();
        Assert.assertEquals(bankId, 1);
    }

    @Test
    public void findAllTest(){
        List<Bank> banks = bankRepository.findAll();
        Assert.assertTrue(banks.size() >= 3);
    }

    @Test
    public void deleteBankTest(){
        Bank bank = new Bank(1, "bank_test", "123456789");
        bankRepository.delete(bank);
        Optional<Bank> deletedBank = bankRepository.findById(1);
        Assert.assertFalse(deletedBank.isPresent());
    }
}
