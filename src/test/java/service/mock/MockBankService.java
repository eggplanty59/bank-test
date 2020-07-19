package service.mock;

import org.springframework.stereotype.Service;
import ru.greendata.dto.BankDto;
import ru.greendata.dto.params.Params;
import ru.greendata.service.BankService;

import java.util.Arrays;
import java.util.List;

@Service
public class MockBankService extends BankService {

    public MockBankService( ) {
    }

    public BankDto create(BankDto bank){
        return bank;
    }

    public BankDto update(BankDto bank) {
        return bank;
    }


        public void delete(Object id){
    }


    public List<BankDto> list(Params params){
        return Arrays.asList(new BankDto[]{new BankDto(1, "name", "bic")});
    }
}

