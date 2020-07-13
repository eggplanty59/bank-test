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
import ru.greendata.dto.params.Params;
import ru.greendata.entity.Deposit;
import ru.greendata.exception.EntityIllegalArgumentException;
import ru.greendata.exception.EntityNotFoundException;
import ru.greendata.service.DepositService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void listDepositTest() {
        List<DepositDto> list = depositService.list(new Params());
        Assert.assertTrue(depositService.list(new Params()).size() >= 3);
    }
}