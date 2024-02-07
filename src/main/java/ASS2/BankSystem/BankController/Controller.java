package ASS2.BankSystem.BankController;

import ASS2.BankSystem.BankModel.BankSystem;
import ASS2.BankSystem.BankServices.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bank")
public class Controller {

    @Autowired
    Services services;

    @PostMapping("/openAc")
    public List<BankSystem> openAccounts (@RequestBody List<BankSystem> bankSystems)
    {
        return services.openAccounts(bankSystems);
    }

    @PostMapping("/open")
    public BankSystem openAccount (@RequestBody BankSystem bankSystem) {
        return services.openAccount(bankSystem);
    }

    @PostMapping("/deposit")
    public BankSystem depositMoney(@RequestParam String acNumber,double deposit){
        return services.depositeMoney(acNumber,deposit);
    }

    @PostMapping("/withdrow")
    public BankSystem withdrowMoney(@RequestParam String acNumber,double withdrow){
        return services.withdrowMoney(acNumber,withdrow);
    }

    @PostMapping("/transfer")
    public transferDto transferMoney(@RequestParam String sender,String reciver,double amount){
        return services.transferMoney(sender,reciver,amount);
    }


}
