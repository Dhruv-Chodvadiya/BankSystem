package ASS2.BankSystem.BankController;

import lombok.Data;

@Data
public class transferDto {
    public String senderName;
    public double senderBalance;
    public String reciverName;
    public double reciverBalance;
}
