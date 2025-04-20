package pojo;

import lombok.Data;

@Data
public class Transaction {
    public String date;
    public int amount;
    public String transactionType;
}