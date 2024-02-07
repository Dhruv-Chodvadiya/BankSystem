package ASS2.BankSystem.BankRepo;

import ASS2.BankSystem.BankController.TransationController;
import ASS2.BankSystem.BankModel.TransationStatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransationRepo extends JpaRepository<TransationStatment,Long> {
    List<TransationStatment> findByAcNumber(String acNumber);
}
