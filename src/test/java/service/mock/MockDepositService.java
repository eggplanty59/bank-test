package service.mock;

import org.springframework.stereotype.Service;
import ru.greendata.dto.BankDto;
import ru.greendata.dto.CustomerDto;
import ru.greendata.dto.DepositDto;
import ru.greendata.dto.params.Params;
import ru.greendata.repository.BankRepository;
import ru.greendata.repository.CustomerRepository;
import ru.greendata.repository.DepositRepository;
import ru.greendata.service.DepositService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class MockDepositService extends DepositService {


    public MockDepositService() {
    }

    @Override
    public DepositDto create(DepositDto deposit){
        return deposit;
    }

    public DepositDto update(DepositDto deposit) {
        return deposit;
    }

    @Override
    public void delete(Object id){
    }

    @Override
    public List<DepositDto> list(Params params) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DepositDto depositDto = null;
        try{
            depositDto = new DepositDto(4, new CustomerDto(), new BankDto() , formatter.parse("2020-05-20 19:00"), 7.5,10);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return Arrays.asList(new DepositDto[]{depositDto});
    }
}
