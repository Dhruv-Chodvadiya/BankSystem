package ASS2.BankSystem.BankRepo;

import ASS2.BankSystem.BankModel.BankSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<BankSystem,Long> {

    BankSystem findByAcNumber(String acNumber);
}
