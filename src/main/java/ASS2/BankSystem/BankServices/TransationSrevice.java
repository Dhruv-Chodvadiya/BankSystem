package ASS2.BankSystem.BankServices;

import ASS2.BankSystem.BankController.TransationController;
import ASS2.BankSystem.BankModel.TransationStatment;
import ASS2.BankSystem.BankRepo.TransationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransationSrevice {

    @Autowired
    TransationRepo transationRepo;

    public List<TransationStatment> statment(String acNumber) {
        return transationRepo.findByAcNumber(acNumber);
    }
}
