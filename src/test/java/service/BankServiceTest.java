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
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Bank;
import ru.greendata.exception.EntityHasDetailsException;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;
import ru.greendata.service.BankService;

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
        bankService.delete(4);
        Bank bank= bankService.findById(4);
    }

    @Test
    public void listBankTest(){
        List<BankDto> list = bankService.list(new Params());
        Assert.assertTrue(bankService.list(new Params()).size() >= 3);
    }
}
