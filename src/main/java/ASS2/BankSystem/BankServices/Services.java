package ASS2.BankSystem.BankServices;

import ASS2.BankSystem.BankController.transferDto;
import ASS2.BankSystem.BankModel.BankSystem;
import ASS2.BankSystem.BankModel.TransationStatment;
import ASS2.BankSystem.BankRepo.Repo;
import ASS2.BankSystem.BankRepo.TransationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class Services {
    @Autowired
    Repo repo;

    @Autowired
    TransationRepo transationRepo;

    public List<BankSystem> openAccounts(List<BankSystem> bankSystems) {
        return repo.saveAll(bankSystems);
    }

    public BankSystem openAccount(BankSystem bankSystem) {
        return repo.save(bankSystem);
    }


    public BankSystem depositeMoney(String acNumber, double deposit) {
        BankSystem bankSystem =new BankSystem();
        bankSystem = repo.findByAcNumber(acNumber);

        TransationStatment transationStatment = new TransationStatment();
        transationStatment.setTransactionId(Math.abs(genrateUniqeId()));
        transationStatment.setAcNumber(bankSystem.getAcNumber());
        transationStatment.setName(bankSystem.getName());
        transationStatment.setIfscCode(bankSystem.getIfscCode());
        transationStatment.setBranchName(bankSystem.getBranchName());
        transationStatment.setStatus("deposite");
        transationStatment.setAmount(deposit);
        transationStatment.setTotalbalance(bankSystem.getBalance() + deposit);

        transationRepo.save(transationStatment);

        bankSystem.setBalance(bankSystem.getBalance()+deposit);
        return repo.save(bankSystem);
    }

    private Long genrateUniqeId() {
        long timestamp = new Date().getTime();

        Random random = new Random();
        long randomNumber = random.nextLong();

        return timestamp + randomNumber;
    }

    public BankSystem withdrowMoney(String acNumber, double withdrow) {
        BankSystem bankSystem =new BankSystem();
        bankSystem = repo.findByAcNumber(acNumber);

        if(bankSystem.getBalance() < withdrow){
            return bankSystem;
        }

        TransationStatment transationStatment = new TransationStatment();
        transationStatment.setTransactionId(Math.abs(genrateUniqeId()));
        transationStatment.setAcNumber(bankSystem.getAcNumber());
        transationStatment.setName(bankSystem.getName());
        transationStatment.setIfscCode(bankSystem.getIfscCode());
        transationStatment.setBranchName(bankSystem.getBranchName());
        transationStatment.setStatus("withdrow");
        transationStatment.setAmount(withdrow);
        transationStatment.setTotalbalance(bankSystem.getBalance() - withdrow);

        transationRepo.save(transationStatment);

        bankSystem.setBalance(bankSystem.getBalance()-withdrow);
        return repo.save(bankSystem);
    }


    public transferDto transferMoney(String sender, String reciver, double amount) {
        transferDto transferDto = new transferDto();


        BankSystem senderaccount = repo.findByAcNumber(sender);
        BankSystem reciverAccount = repo.findByAcNumber(reciver);


        if(senderaccount.getBalance() < amount){
            transferDto.setSenderName(sender);
            transferDto.setReciverBalance(reciverAccount.getBalance());
            transferDto.setReciverName(reciverAccount.getName());
            transferDto.setSenderBalance(senderaccount.getBalance());
            return transferDto;
        }

        senderaccount.setBalance(senderaccount.getBalance()-amount);

        TransationStatment transationStatment = new TransationStatment();
        transationStatment.setTransactionId(Math.abs(genrateUniqeId()));
        transationStatment.setAcNumber(senderaccount.getAcNumber());
        transationStatment.setName(senderaccount.getName());
        transationStatment.setIfscCode(senderaccount.getIfscCode());
        transationStatment.setBranchName(senderaccount.getBranchName());
        transationStatment.setStatus("debited");
        transationStatment.setAmount(amount);

        transationRepo.save(transationStatment);
        repo.save(senderaccount);


        reciverAccount.setBalance(reciverAccount.getBalance() + amount);


        TransationStatment transationStatment1 = new TransationStatment();
        transationStatment1.setTransactionId(Math.abs(genrateUniqeId()));
        transationStatment1.setAcNumber(reciverAccount.getAcNumber());
        transationStatment1.setName(reciverAccount.getName());
        transationStatment1.setIfscCode(reciverAccount.getIfscCode());
        transationStatment1.setBranchName(reciverAccount.getBranchName());
        transationStatment1.setStatus("credit");
        transationStatment1.setAmount(amount);

        transationRepo.save(transationStatment1);
        repo.save(reciverAccount);

        transferDto.setSenderName(senderaccount.getName());
        transferDto.setReciverBalance(reciverAccount.getBalance());
        transferDto.setReciverName(reciverAccount.getName());
        transferDto.setSenderBalance(senderaccount.getBalance());
        return transferDto;
    }

}