package ASS2.BankSystem.BankModel;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class TransationStatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long transactionId;
    private String name;
    private String acNumber;
    private String branchName;
    private String ifscCode;
    private Double amount;
    private Double Totalbalance;

    private String status;

    private String time;
    @PrePersist
    public void prePersist(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }
}
