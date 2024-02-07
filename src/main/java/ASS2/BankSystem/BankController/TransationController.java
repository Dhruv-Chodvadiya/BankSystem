package ASS2.BankSystem.BankController;

import ASS2.BankSystem.BankModel.TransationStatment;
import ASS2.BankSystem.BankServices.EmailServices;
import ASS2.BankSystem.BankServices.TransationSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("bankpassbook")
public class TransationController {

    @Autowired
    TransationSrevice transationSrevice;
    @Autowired
    EmailServices emailServices;

    @GetMapping("/statement")
    public List<TransationStatment> statment(@RequestParam String acNumber){
        return transationSrevice.statment(acNumber);
    }

    @GetMapping("/email/statement")
    public List<TransationStatment> getStatmentEmail(@RequestParam String acNumber,String email){
        List<TransationStatment> list = new ArrayList<>();
        list = transationSrevice.statment(acNumber);
        emailServices.sendTransactionHistoryEmail(email,list);
        return list;
    }

}
