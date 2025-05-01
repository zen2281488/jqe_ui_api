package Util;
import io.qameta.allure.Allure;
import pojo.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class WriteUtils {
    public static void writeTransactionsToCSV(List<Transaction> transactions) {
        String filePath = null;
        try {
            File directory = new File("output");
            if (!directory.exists()) {
                directory.mkdir();
            }
            filePath = directory.getAbsolutePath() + File.separator + "transactions.csv";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.append("Date,Amount,Transaction Type\n");
                transactions.forEach(transaction -> {
                    try {
                        writer.append(transaction.getDate()).append(',').append(String.valueOf(transaction.getAmount())).append(',').append(transaction.getTransactionType()).append('\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (InputStream is = new FileInputStream(filePath)) {
            Allure.addAttachment("Transactions CSV", "text/csv", is, ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
